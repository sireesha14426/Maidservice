package com.maidservices.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalTime;

@Data
public class MaidDTO {
    private long maidid;
    private String maidname;
    private String field;
    private int rating;
    private String startTime;
    private String endTime;
}
