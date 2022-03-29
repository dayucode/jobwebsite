package com.pearadmin.pro.common.secure.uutoken;

import com.pearadmin.pro.common.constant.CacheNameConstant;
import com.pearadmin.pro.common.constant.SecurityConstant;
import com.pearadmin.pro.common.secure.services.SecureUser;
import com.pearadmin.pro.common.tools.core.TokenUtil;
import com.pearadmin.pro.common.web.exception.auth.token.TokenException;
import com.pearadmin.pro.common.web.exception.auth.token.TokenExpiredException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Security User Token 操作服务
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/10/23
 * */
@Component
public class SecureUserTokenService {

    @Resource
    private RedisTemplate<String, SecureUserToken> redisTemplate;

    /**
     * 创建 Token
     * */
    public SecureUserToken createToken(SecureUser secureUser){
        SecureUserToken userToken = new SecureUserToken();
        userToken.setToken(TokenUtil.create(secureUser.getId(),secureUser.getUsername()));
        userToken.setSecureUser(secureUser);
        return userToken;
    }

    /**
     * 存储 Token
     * */
    public String saveToken(SecureUserToken userToken){
        String key = String.valueOf(UUID.randomUUID());
        redisTemplate.opsForValue().set(CacheNameConstant.TOKEN_NAME_PREFIX + key, userToken,  SecurityConstant.TOKEN_EXPIRATION, TimeUnit.SECONDS);
        return key;
    }

    /**
     * 验证 Token
     * */
    public SecureUserToken verifyToken(String key, String token) throws TokenException{
        SecureUserToken secureUserToken = taskToken(key);
        if(secureUserToken == null) throw new TokenExpiredException("token expired");
        TokenUtil.parse(secureUserToken.getToken());
        return secureUserToken;
    }

    /**
     * 获取 Token
     * */
    public SecureUserToken taskToken(String key){
        return redisTemplate.opsForValue().get(CacheNameConstant.TOKEN_NAME_PREFIX + key);
    }

    /**
     * 销毁 Token
     * */
    public void destroyToken(String key){
        redisTemplate.delete(CacheNameConstant.TOKEN_NAME_PREFIX + key);
    }

}
