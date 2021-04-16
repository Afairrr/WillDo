package com.afair.auth.commons.constants;

/**
 * spring-security的相关配置
 *
 * @author WangBing
 * @date 2021/3/20
 */
public final class SecurityConstants {
    /**
     * 角色的key
     */
    public final static String ROLE_CLAIMS = "rol";

    /**
     * 如果rememberMe为false,过期时间为1小时
     */
    public final static long EXPIRATION = 60 * 60 * 1000L;

    /**
     * rememberMe为true，过期时间为7天
     */
    public final static long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7 * 1000L;

    /**
     * JWT的签名秘钥，存放在.properties文件里
     */
    public final static String JWT_SECRET_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDKks6qAROuRHQpYbJ7xtuwuKFa";
    /**
     * 请求头的token参数名
     */
    public final static String TOKEN_HEADER = "Authorization";
    /**
     * token的前缀
     */
    public final static String TOKEN_PREFIX = "Bearer ";
    /**
     * token的类型
     */
    public final static String TOKEN_TYPE = "JWT";
    /**
     * swagger白名单
     */
    public final static String[] SWAGGER_WHITELIST = {
            "/swagger-ui.html",
            "/swagger-ui/*",
            "/doc.html",
            "/doc/*",
            "/swagger-resources/**",
            "/v2/api-docs",
            "/v3/api-docs",
            "/webjars/**"
    };

    /**
     * ADMIN的白名单
     */
    public final static String[] ADMIN_WHITELIST = {
            "/actuator/**",
            "/instances/**"
    };

    /**
     * 系统内白名单
     */
    public final static String[] SYSTEM_WHITELIST = {
            "/authentication/login",
            "/authentication/logout",
            "/user/register"
    };
}
