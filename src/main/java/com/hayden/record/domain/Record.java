package com.hayden.record.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
@Entity
@Table(name = "`record`")
@EntityListeners(AuditingEntityListener.class)
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recordId;

    // 회원과 다대일 관계 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)  // record.user_id → user.user_id
    @JsonIgnore
    private User user;

    @Column(length = 15, columnDefinition = "CHAR(15) DEFAULT 'BOOK'")
    private String category;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(length = 255)
    private String genre;

    @Column(length = 255)
    private String relatedPeople;

    private Integer charge;
    private LocalDate completedAt;
    private String addData;

    @Column(length = 3000)
    private String review;

    private Integer satisfaction;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
