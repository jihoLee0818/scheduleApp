package com.sparta.scheduleapp.controller;

import com.sparta.scheduleapp.dto.ScheduleRequest;
import com.sparta.scheduleapp.dto.ScheduleResponse;
import com.sparta.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;

    @PostMapping("/api/schedules")
    public ResponseEntity<ScheduleResponse> createSchedule(@RequestBody ScheduleRequest scheduleRequest) {
        ScheduleResponse response = scheduleService.save(scheduleRequest);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/schedules")
    public ResponseEntity<List<ScheduleResponse>> getSchedules(@RequestParam(required = false) String manager) {
        List<ScheduleResponse> responseList = scheduleService.getSchedules(manager);
        return ResponseEntity.ok(responseList);
    }

    @GetMapping("/api/schedules/{id}")
    public ResponseEntity<ScheduleResponse> getSchedule(@PathVariable Long id) {
        ScheduleResponse response = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(response);
    }
}
