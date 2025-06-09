package com.maidservices.service;

import com.maidservices.models.User;
import com.maidservices.models.Usertype;
import com.maidservices.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;


}
