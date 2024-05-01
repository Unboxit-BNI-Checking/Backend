package com.unboxit.bnichecking.entity.db;

import java.time.LocalDateTime;

public interface FavouriteDB {

    Long getFavouriteId();

    String getName();

    long getUserId();

    String getFavouriteAccountNumber();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    LocalDateTime getDeletedAt();

}
