package com.sparta.scheduleapp.controller;

import com.sparta.scheduleapp.dto.ScheduleRequest;
import com.sparta.scheduleapp.dto.ScheduleResponse;
import com.sparta.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/api/schedules")
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        ScheduleResponse response = scheduleService.save(scheduleRequest);
        return ResponseEntity.ok(response);
    }
}
