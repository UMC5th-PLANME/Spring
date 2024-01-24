package com.planme.main.domain;

import com.planme.main.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MeStoryFocus extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TIME")
    private LocalTime totalFocusTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

}
