package com.maidservices.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalTime;
@Data
@Entity
public class Maid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long maidid;
    private String maidname;
    private String field;
    private int rating;
    private String startTime;
    private String endTime;
}
