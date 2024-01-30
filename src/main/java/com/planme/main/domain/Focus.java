package com.planme.main.domain;

import com.planme.main.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Time;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Focus extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Builder.Default
    @Column(columnDefinition = "TIME")
    private LocalTime focusTime = LocalTime.parse("00:00:00");

    @Builder.Default
    @Column(columnDefinition = "TIME")
    private LocalTime breakTime = LocalTime.parse("00:00:00");

    private Long repeatCnt;

    private Long currentRepeatCnt;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;


    public void updateFocus(LocalTime focusTime, LocalTime breakTime, Long repeatCnt) {
        this.focusTime = focusTime;
        this.breakTime = breakTime;
        this.repeatCnt = repeatCnt;
        this.currentRepeatCnt = 0L;
    }

    public void incrementCurrentRepeatCnt() {
        this.currentRepeatCnt++;
    }


}
