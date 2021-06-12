package com.lishuai.shiro.jwt;

import com.lishuai.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义JWTToken
 * @author lishuai
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return JwtUtils.getUsername(token);
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}