package com.sparta.scheduleapp.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ScheduleRequest {
    private String title;
    private String contents;
    private String manager;
    private String password;
}
