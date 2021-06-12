package com.lishuai.shiro;

import com.lishuai.entity.User;
import com.lishuai.mapper.UserMapper;
import com.lishuai.shiro.jwt.JWTToken;
import com.lishuai.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 自定义 Realm
 * @author lishuai
 */
@Slf4j
public class CustomerRealm extends AuthorizingRealm {

    @Autowired
    UserMapper userMapper;

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //获取用户
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        //获取用户权限
        log.info("获取用户权限成功！");
        //遍历权限，传入simpleAuthorizationInfo
        simpleAuthorizationInfo.addRole(user.getRole().getRoleName());

        return simpleAuthorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取token，这里的返回值由jwtToken决定
        String token = (String) authenticationToken.getCredentials();
        String username = JwtUtils.getUsername(token);
        User user = userMapper.findRolesByUserName(username);

        log.info("----------用户校验----------");

        if (!JwtUtils.verify(token,username,user.getPassword())){
            throw new AuthenticationException("用户名或密码错误!");
        }else if(user.getStatus()==0){
            throw new AuthenticationException("用户已被封号");
        } else if(JwtUtils.isExpire(token)){
            throw new AuthenticationException("登录过期，请重新登入！");
        } else {
            //这里的SimpleAuthenticationInfo的参数决定Subject里的的Principal和Credentials;
            return new SimpleAuthenticationInfo(user,token,this.getName());
        }
    }

    /**
     * 建议重写此方法，提供唯一的缓存Key
     */
    @Override
    protected Object getAuthorizationCacheKey(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        return user.getUsername();
//        return super.getAuthorizationCacheKey(principals);
    }

    /**
     * 建议重写此方法，提供唯一的缓存Key
     */
    @SuppressWarnings("unchecked")
    @Override
    protected Object getAuthenticationCacheKey(PrincipalCollection principals) {
        User user = (User) principals.getPrimaryPrincipal();
        return user.getUsername();
//        return super.getAuthenticationCacheKey(principals);
    }
}
