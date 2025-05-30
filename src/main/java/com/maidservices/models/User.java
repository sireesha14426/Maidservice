package com.maidservices.models;

import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.Data;
import org.hibernate.usertype.UserType;
@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userid;
    private String username;
    private String email;
    private String phoneno;
    private String address;
    @Enumerated(EnumType.STRING)
    private Usertype usertype;
}
