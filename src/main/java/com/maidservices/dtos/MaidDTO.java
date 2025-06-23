package com.maidservices.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Data
public class MaidDTO {
    private long id;
    private String name;
    private String field;
    private int rating;
    private String startTime;
    private String endTime;
    private String description;
    private String userName;
    private String password;
}
