package com.maidservices.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
@Data
@Entity
@Table(name="maidTable")
public class Maid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(name="category")
    private String field;
    private int rating;
    private String startTime;
    private String endTime;
    private String description;
    private String userName;
    private String password;
}
