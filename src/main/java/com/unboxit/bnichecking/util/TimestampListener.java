package com.unboxit.bnichecking.util;

import com.unboxit.bnichecking.model.TimestampedEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.LocalDateTime;

public class TimestampListener {

    @PrePersist
    public void setCreatedAt(Object entity) {
        if (entity instanceof TimestampedEntity) {
            ((TimestampedEntity) entity).setCreatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void setUpdatedAt(Object entity) {
        if (entity instanceof TimestampedEntity) {
            ((TimestampedEntity) entity).setUpdatedAt(LocalDateTime.now());
        }
    }
}
