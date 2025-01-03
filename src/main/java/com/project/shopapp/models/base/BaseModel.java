package com.project.shopapp.models.base;

import com.project.shopapp.common.RECORD_STATUS;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class BaseModel {
    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createdAt;

    @Column(name = "updated_at", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant updatedAt;

//    @Column(name = "createdBy")
//    private String createdBy;
//
//    @Column(name = "updatedBy")
//    private String updatedBy;

    @Column(name = "record_status", columnDefinition = "TEXT DEFAULT 'ACTIVE'")
    @Enumerated(EnumType.STRING)
    private RECORD_STATUS recordStatus = RECORD_STATUS.ACTIVE;


    @PrePersist
    protected void createdAt() {
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    @PreUpdate
    protected void updatedAt() {
        this.updatedAt = Instant.now();
    }


    public void updateRecordStatus(RECORD_STATUS status) {
        if (status == null) {
            recordStatus = RECORD_STATUS.DELETED;
            return;
        }
        this.recordStatus = status;
    }
}
