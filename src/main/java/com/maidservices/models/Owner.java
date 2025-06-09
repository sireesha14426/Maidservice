package com.maidservices.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String flatname;
    private Long houseno;
}
