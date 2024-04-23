package com.unboxit.bnichecking.entity.http.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateAdmin {
    private String username;
    @JsonProperty("hashed_password")
    private String hashedPassword;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
