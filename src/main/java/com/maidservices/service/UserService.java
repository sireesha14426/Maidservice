package com.maidservices.service;

import com.maidservices.models.User;
import com.maidservices.repo.UserRepo;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public User createUser(User user);
    public User getUserById(Long userid);
    public List<User> getAllUsers();
    public String deleteUserById(Long userid);
    public User updateUser(Long userid, String username, String email, String phone, String address, String usertype);
}
