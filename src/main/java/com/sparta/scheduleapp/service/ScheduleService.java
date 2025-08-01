package com.sparta.scheduleapp.service;

import com.sparta.scheduleapp.dto.ScheduleRequest;
import com.sparta.scheduleapp.dto.ScheduleResponse;
import com.sparta.scheduleapp.entity.Schedule;
import com.sparta.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponse save(ScheduleRequest requestDto){
        Schedule savedschedule = scheduleRepository.save(new Schedule(requestDto.getTitle(), requestDto.getContents(), requestDto.getManager(), requestDto.getPassword()));
        return new ScheduleResponse(savedschedule);
    }
}
