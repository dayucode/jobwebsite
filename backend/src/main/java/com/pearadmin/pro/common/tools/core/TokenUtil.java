package com.pearadmin.pro.common.tools.core;

import com.pearadmin.pro.common.constant.TokenConstant;
import com.pearadmin.pro.common.web.exception.auth.token.TokenValidationException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;

/**
 * Token 工具类
 * */
public class TokenUtil {

    /**
     * 创建 Token
     *
     * @param userId 用户编号
     * @param username 用户账号
     * */
    public static String create(String userId,String username){
        return  Jwts.builder()
                .setId(userId)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setIssuer(TokenConstant.ISSUER)
                .signWith(SignatureAlgorithm.HS512, TokenConstant.SECRET)
                .compact();
    }

    /**
     * 解析 Token
     *
     * @param token Token
     * */
    public static void parse(String token) {
        try {
            Jwts.parser().setSigningKey(TokenConstant.SECRET).parseClaimsJws(token).getBody();
        }catch (Exception e){
            throw new TokenValidationException("token validation failure");
        }
    }
}
