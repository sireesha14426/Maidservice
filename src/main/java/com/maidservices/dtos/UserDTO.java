package com.maidservices.dtos;

import com.maidservices.models.Usertype;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String phoneno;
    private String address;
    @Enumerated(EnumType.STRING)
    private Usertype usertype;
}
