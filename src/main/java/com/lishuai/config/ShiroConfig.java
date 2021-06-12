package com.lishuai.config;

import com.lishuai.shiro.CustomerRealm;
import com.lishuai.shiro.cache.RedisCacheManager;
import com.lishuai.shiro.jwt.JWTFilter;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置文件
 * @author lishuai
 */
@Configuration
public class ShiroConfig {

    /**
     * 创建 ShiroFilter 拦截所有请求
     * 需要 SecurityManager
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager){

        ShiroFilterFactoryBean shiroFilterFactoryBean= new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        //设置我们自定义的JWT过滤器
        filterMap.put("jwt", new JWTFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        //配置系统受限资源
        Map<String,String> map =  new HashMap<>();
        //这里表示除了/user下的请求是自由的,其他请求都走我们的jwt拦截器
        map.put("/user/login","anon");
        map.put("/user/logout","anon");
        map.put("/user/register","anon");
        map.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        //设置无权限时跳转的 url;
        shiroFilterFactoryBean.setUnauthorizedUrl("/user/unauthorized");
        shiroFilterFactoryBean.setLoginUrl("/user/goLogin");

        return shiroFilterFactoryBean;
    }

    /**
     * 创建 SecurityManager 并注入
     * 需要 Realm
     * @param realm
     * @return
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("getRealm") Realm realm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(realm);

        /*
         * 关闭shiro自带的session
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultWebSecurityManager.setSubjectDAO(subjectDAO);

        return defaultWebSecurityManager;
    }

    /**
     * 注入自定义 Realm
     * @return
     */
    @Bean
    public Realm getRealm(){

        CustomerRealm customerRealm = new CustomerRealm();

//        //开启全局缓存
//        customerRealm.setCachingEnabled(true);
//        //开启认证缓存
//        customerRealm.setAuthenticationCachingEnabled(true);
//        customerRealm.setAuthenticationCacheName("authenticationCache");
//        //开启授权缓存
//        customerRealm.setAuthorizationCachingEnabled(true);
//        customerRealm.setAuthorizationCacheName("authorizationCache");
//        //传入缓存管理器
//        customerRealm.setCacheManager(new EhCacheManager());

        return customerRealm;
    }

    /**
     * 添加注解支持
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题
        // https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }
}
