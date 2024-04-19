package com.li.lrms.model.logintoken;


public class LoginToken {
    private String token;

    @Override
    public String toString() {
        return "LoginToken{" +
                "token='" + token + '\'' +
                '}';
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


}
