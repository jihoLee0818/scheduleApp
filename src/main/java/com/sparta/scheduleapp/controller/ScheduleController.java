package com.sparta.scheduleapp.controller;

import com.sparta.scheduleapp.dto.ScheduleRequest;
import com.sparta.scheduleapp.dto.ScheduleResponse;
import com.sparta.scheduleapp.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    @PutMapping("/api/schedules/{id}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequest requestDto) {
        ScheduleResponse response = scheduleService.updateSchedule(id, requestDto);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/api/schedules/{id}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long id, @RequestBody Map<String, String> requestbody) {
    String password = requestbody.get("password");
    scheduleService.deleteSchedule(id, password);
    return ResponseEntity.ok("일정이 삭제되었습니다.");
    }


}
