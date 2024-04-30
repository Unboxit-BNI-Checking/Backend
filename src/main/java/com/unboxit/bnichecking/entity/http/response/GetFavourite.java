package com.unboxit.bnichecking.entity.http.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unboxit.bnichecking.entity.db.FavouriteDB;

import java.time.LocalDateTime;

public class GetFavourite {

    @JsonProperty("favourite_id")
    private long favouriteId;

    @JsonProperty("favourite_name")
    private String favouriteName;

    @JsonProperty("favourite_account_number")
    private String favouriteAccountNumber;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    public GetFavourite(FavouriteDB favouriteDB){
        this.favouriteId = favouriteDB.getFavouriteId();
        this.favouriteName = favouriteDB.getName();
        this.favouriteAccountNumber = favouriteDB.getFavouriteAccountNumber();
        this.createdAt = favouriteDB.getCreatedAt();
        this.updatedAt = favouriteDB.getUpdatedAt();
        this.deletedAt = favouriteDB.getDeletedAt();
    }

    public long getFavouriteId() {
        return favouriteId;
    }

    public void setFavouriteId(long favouriteId) {
        this.favouriteId = favouriteId;
    }

    public String getFavouriteName() {
        return favouriteName;
    }

    public void setFavouriteName(String favouriteName) {
        this.favouriteName = favouriteName;
    }

    public String getFavouriteAccountNumber() {
        return favouriteAccountNumber;
    }

    public void setFavouriteAccountNumber(String favouriteAccountNumber) {
        this.favouriteAccountNumber = favouriteAccountNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }
}
