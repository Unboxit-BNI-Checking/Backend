package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unboxit.bnichecking.model.Account;

import java.util.List;

public class GetAccountNumberByUserId {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("account")
    private List<GetMyAccount> account;

    public GetAccountNumberByUserId(long userId, List<GetMyAccount> accounts) {
        this.userId = userId;
        this.account = accounts;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<GetMyAccount> getAccount() {
        return account;
    }

    public void setAccount(List<GetMyAccount> account) {
        this.account = account;
    }
}
