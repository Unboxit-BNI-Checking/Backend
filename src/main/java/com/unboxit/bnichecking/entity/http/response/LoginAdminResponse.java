package com.unboxit.bnichecking.entity.http.response;

public class LoginAdminResponse {

    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginAdminResponse(String token) {
        this.token = token;
    }
}