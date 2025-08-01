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

    @Transactional(readOnly = true)
    public ScheduleResponse getScheduleById(Long id){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 ID의 일정이 없습니다.")
        );
        return new ScheduleResponse(schedule);
    }

    @Transactional
    public ScheduleResponse updateSchedule(Long id, ScheduleRequest requestDto){
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당하는 id의 일정이 없습니다.")
        );
        if(!schedule.getPassword().equals(requestDto.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(requestDto.getTitle(), requestDto.getManager());

        return new ScheduleResponse(schedule);
    }
}
