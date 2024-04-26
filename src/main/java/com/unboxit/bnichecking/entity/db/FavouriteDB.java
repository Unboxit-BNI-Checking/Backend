package com.unboxit.bnichecking.entity.db;

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface FavouriteDB {

    long getFavouriteId();
    String getName();
    long getAccountId();
    String getFavouriteAccountNumber();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();
    LocalDateTime getDeletedAt();

}
