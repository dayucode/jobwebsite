package com.pearadmin.pro.common.secure.captcha;

import com.pearadmin.pro.common.constant.CacheNameConstant;
import com.pearadmin.pro.common.constant.SecurityConstant;
import com.pearadmin.pro.common.web.exception.auth.captcha.CaptchaExpiredException;
import com.pearadmin.pro.common.web.exception.auth.captcha.CaptchaValidationException;
import com.wf.captcha.SpecCaptcha;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * Captcha 验证码服务
 *
 * Author: 就 眠 仪 式
 * CreateTime: 2021/04/10
 * */
@Slf4j
@Service
public class SecureCaptchaService {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 创 建 Captcha
     * */
    public SecureCaptcha createCaptcha(){
        SpecCaptcha captcha = new SpecCaptcha(142,38);
        String key = UUID.randomUUID().toString();
        String code = captcha.text().toLowerCase();
        redisTemplate.opsForValue().set(CacheNameConstant.CAPTCHA_NAME_PREFIX + key, code, SecurityConstant.CAPTCHA_EXPIRATION, TimeUnit.SECONDS);
        //影藏code  new SecureCaptcha(key,"",captcha.toBase64());
        return new SecureCaptcha(key,code,captcha.toBase64());
    }

    /**
     * 验 证 Captcha
     * */
    public void verifyCaptcha(String key, String code) {
        String redisCode = taskCaptcha(key);
        if(redisCode == null) throw new CaptchaExpiredException("captcha expired");
        //大小写都可
        if(!redisCode.equalsIgnoreCase(code)) throw new CaptchaValidationException("captcha invalid");
    }

    /**
     * 获 取 Captcha
     * */
    public String taskCaptcha(String key) {
        return redisTemplate.opsForValue().get(CacheNameConstant.CAPTCHA_NAME_PREFIX + key);
    }

    /**
     * 销 毁 Captcha
     * */
    public void destroyCaptcha(String key){
        redisTemplate.delete(CacheNameConstant.CAPTCHA_NAME_PREFIX + key);
    }
}
