package com.sparta.scheduleapp.service;

import com.sparta.scheduleapp.dto.ScheduleRequest;
import com.sparta.scheduleapp.dto.ScheduleResponse;
import com.sparta.scheduleapp.entity.Schedule;
import com.sparta.scheduleapp.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponse save(ScheduleRequest requestDto){
        Schedule savedschedule = scheduleRepository.save(
                new Schedule(
                        requestDto.getTitle(),
                        requestDto.getContents(),
                        requestDto.getManager(),
                        requestDto.getPassword()
                )
        );
        return new ScheduleResponse(savedschedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponse> getSchedules(String manager){
        List<Schedule> schedules;
        if(manager==null || manager.isEmpty()){
            schedules = scheduleRepository.findAllByOrderByModifiedAtDesc();
        } else {
            schedules = scheduleRepository.findAllByManagerOrderByModifiedAtDesc(manager);
        }

        List<ScheduleResponse> responseList = new ArrayList<>();
        for (Schedule schedule : schedules) {
            responseList.add(new ScheduleResponse(schedule));
        }
        return responseList;
    }
}
