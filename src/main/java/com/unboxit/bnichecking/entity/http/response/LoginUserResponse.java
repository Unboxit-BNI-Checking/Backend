package com.unboxit.bnichecking.entity.http.response;

public class LoginUserResponse {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LoginUserResponse(String token) {
        this.token = token;
    }
}
