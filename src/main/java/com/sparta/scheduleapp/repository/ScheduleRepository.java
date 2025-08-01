package com.sparta.scheduleapp.repository;

import com.sparta.scheduleapp.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByManagerOrderByModifiedAtDesc(String manager);
    List<Schedule> findAllByOrderByModifiedAtDesc();
}
