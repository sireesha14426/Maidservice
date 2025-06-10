package com.maidservices.models;

import com.maidservices.constants.Usertype;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String phoneno;
    private String address;
    @Enumerated(EnumType.STRING)
    private Usertype usertype;
}
