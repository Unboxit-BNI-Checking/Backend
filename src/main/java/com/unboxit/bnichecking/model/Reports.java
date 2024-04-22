//package com.unboxit.bnichecking.model;
//
//import jakarta.persistence.*;
//import org.hibernate.annotations.OnDelete;
//import org.hibernate.annotations.OnDeleteAction;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import java.time.LocalDateTime;
//import java.util.List;
//import org.springframework.data.annotation.CreatedDate;
//import org.springframework.data.annotation.LastModifiedDate;
//import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//import org.w3c.dom.Text;
//
//@Entity
//@Table(name = "reports", indexes = {
//        @Index(name = "idx_transaction_id", columnList = "transaction_id"),
//        @Index(name = "idx_reported_account_id", columnList = "reported_account_id")
//})
//@EntityListeners(AuditingEntityListener.class)
//public class Reports {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long reportId;
//
//    @Column(name="chronology", nullable = false)
//    private Text chronology;
//
//    @CreatedDate
//    @Column(name="created_at", nullable = false, updatable = false)
//    private LocalDateTime createdAt;
//
//    @LastModifiedDate
//    @Column(name="updated_at")
//    private LocalDateTime updatedAt;
//
//    @Column(name="deleted_at")
//    private LocalDateTime deletedAt;
//
//    @OneToOne
//    @JoinColumn(name = "transaction_id")
//    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    private List<Favourite> ownedFavourites;
//
//    @ManyToOne
//    @JoinColumn(name = "")
//    @OnDelete(action = OnDeleteAction.NO_ACTION)
//    private List<Favourite> ownedFavourites;
//
//
//}
