package com.lishuai.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * JWT生成器
 * @author lishuai
 */
@Slf4j
public class JwtUtils {
    /**
     * 过期时间30分钟
     */
    public static final long EXPIRE_TIME = 30 * 60 * 1000;
    // 密钥
//    private static final String SECRET = "SHIRO+JWT";

    /**
     * 校验token是否正确
     * 参数是用户名和从数据库返回的密码
     * @param token  密钥
     * @return 是否正确
     */
    public static boolean verify(String token, String username,String password) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).withClaim("username", username).build();
            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            log.info(jwt + ":-token is valid");
            return true;
        } catch (Exception e) {
            log.info("The token is invalid{}", e.getMessage());
            return false;
        }
    }

    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 生成签名,5min(分钟)后过期
     * 参数用户名和加密后的密码
     * @return 加密的token
     */
    public static String sign(String username,String secret) {
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withClaim("username", username)
                .withExpiresAt(date)
                .sign(algorithm);
    }

    /**
     * 判断过期
     *
     * @param token
     * @return
     */
    public static boolean isExpire(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return System.currentTimeMillis() > jwt.getExpiresAt().getTime();
    }
}
