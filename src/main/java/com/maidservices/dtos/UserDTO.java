package com.maidservices.dtos;

import com.maidservices.models.Usertype;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class UserDTO {
    private long userid;
    private String username;
    private String email;
    private String phoneno;
    private String address;
    @Enumerated(EnumType.STRING)
    private Usertype usertype;
}
