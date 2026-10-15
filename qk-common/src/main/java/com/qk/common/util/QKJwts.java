package com.qk.common.util;

import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author MrZhao
 * JWT 令牌工具类：生成、解析、校验。
 * 密钥在此统一定义，登录签发与拦截器校验使用同一把密钥，避免不一致。
 *
 */
@SuppressWarnings("all")
public class QKJwts {

    /**
     * 构造器私有化
     */
    private QKJwts() {
    }

    /**
     * 过期时间：7 天（毫秒）
     */
    private static final long EXPIRE_MILLIS = 7L * 24 * 60 * 60 * 1000;

    /**
     * 密钥（HS256 要求至少 32 字节）
     */
    private static final byte[] KEY = "qk-secret-key-for-jwt-token-1234567890".getBytes(StandardCharsets.UTF_8);

    /**
     * 生成令牌
     *
     * @param payload 载荷
     * @return 令牌
     */
    public static String createJWTToken(Map<String, Object> payload) {
        JWT jwt = JWT.create().setKey(KEY);
        payload.forEach((k, v) -> jwt.setPayload(k, v));
        jwt.setExpiresAt(new Date(System.currentTimeMillis() + EXPIRE_MILLIS));
        return jwt.sign();
    }

    /**
     * 解析令牌
     *
     * @param token 令牌
     * @return JSONObject
     */
    public static JSONObject parseJWTToken(String token) {
        return JWTUtil.parseToken(token).getPayloads();
    }

    /**
     * 校验 token
     *
     * @param token 令牌
     * @return Boolean
     */
    public static boolean verifyJWTToken(String token) {
        boolean flag = false;
        try {
            flag = JWTUtil.verify(token, KEY);
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
}
