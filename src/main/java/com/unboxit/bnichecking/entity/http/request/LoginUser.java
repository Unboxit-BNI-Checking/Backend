package com.unboxit.bnichecking.entity.http.request;

public class LoginUser {
    private String username;
    private String password;
    private String mpin;
    private String customerName;

    public String getUsername () {
        return username;
    }

    public void setUsername (String username){
        this.username = username;
    }

    public String getPassword () {
        return password;
    }

    public void setPassword (String password){
        this.password = password;
    }

    public String getMpin () {
        return mpin;
    }

    public void setMpin (String mpin){
        this.mpin = mpin;
    }

    public String getCustomerName () {
        return customerName;
    }

    public void setCustomerName (String customerName){
        this.customerName = customerName;
    }

    public LoginUser() {

    }
}