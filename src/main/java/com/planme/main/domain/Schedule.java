package com.planme.main.domain;

import com.planme.main.domain.common.BaseEntity;
import com.planme.main.domain.enums.Repeat;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(columnDefinition = "TINYINT(1)")
    private boolean status; //  0: 진행중, 1: 진행완료

    private LocalDate startDate;

    private LocalDate endDate;

    @Column(columnDefinition = "TIME")
    private LocalTime startTime;

    @Column(columnDefinition = "TIME")
    private LocalTime endTime;

    @ColumnDefault("0")
    @Column(columnDefinition = "TINYINT(1)")
    private boolean alarm; //  0: 알람 설정 x, 1: 알람 설정

    @Column(columnDefinition = "TIME")
    private LocalTime alarmTime;

//    @ColumnDefault("0")   //  아래 repeatPeriod 속성에 none이 들어가서 이 컬럼은 필요 없을 거 같아서 일단 주석 처리 해뒀습니다.
//    @Column(columnDefinition = "TINYINT(1)")
//    private boolean repeat; //  0: 반복x, 1: 반복

    private Repeat repeatPeriod;

    private String repeatDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setTitle(String title){this.title= title;}

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setAlarm(boolean alarm) {
        this.alarm = alarm;
    }

    public void setAlarmTime(LocalTime alarmTime) {
        this.alarmTime = alarmTime;
    }

    public void setRepeatPeriod(Repeat repeatPeriod) {
        this.repeatPeriod = repeatPeriod;
    }

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alarm> alarms = new ArrayList<>();

    public void addAlarm(Alarm alarm) {
        this.alarms.add(alarm);
        alarm.setSchedule(this);
    }

}
