package com.maidservices.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String flatName;
    private String houseNo;
    private String description;
}
