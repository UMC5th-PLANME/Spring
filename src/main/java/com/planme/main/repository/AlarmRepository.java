package com.planme.main.repository;

import com.planme.main.domain.Alarm;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlarmRepository extends JpaRepository<Alarm, Long> {

    List<Alarm> findAlarmsByScheduleId(Long schedule_id);
}
