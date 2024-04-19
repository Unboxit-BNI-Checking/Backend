package com.unboxit.bnichecking.model;

import java.time.LocalDateTime;

public interface TimestampedEntity {

    LocalDateTime getCreatedAt();

    void setCreatedAt(LocalDateTime createdAt);

    LocalDateTime getUpdatedAt();

    void setUpdatedAt(LocalDateTime updatedAt);
}
