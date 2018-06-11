package com.dreams.cloud.base.common.auth;

/**
 * 存储当前线程的token
 */
public class TokenContext {
    private ThreadLocal<String> token = new ThreadLocal<>();

    public String getToken() {
        return token.get();
    }

    void setToken(String token) {
        if (token == null) {
            System.clearProperty("auth.token");
        } else {
            System.setProperty("auth.token", token);
        }
        this.token.set(token);
    }

}
