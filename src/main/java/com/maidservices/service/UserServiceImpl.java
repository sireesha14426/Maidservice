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


    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User getUserById(Long userid) {
        return userRepo.findById(userid).orElse(null);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public String deleteUserById(Long userid) {
        if(userRepo.existsById(userid)){
            userRepo.deleteById(userid);
            return "User deleted By Id: " + userid;
        }else{
            return "User doesn't exist";
        }
    }

    @Override
    public User updateUser(Long userid, String username, String email, String phone, String address, String usertype) {
        Optional<User> optionalUser= userRepo.findById(userid);
        if(optionalUser.isPresent()){
            User existinguser=optionalUser.get();
            existinguser.setUsername(username);
            existinguser.setEmail(email);
            existinguser.setPhoneno(phone);
            existinguser.setAddress(address);
            existinguser.setUsertype(Usertype.valueOf(usertype.toUpperCase()));

            return userRepo.save(existinguser);
        }
        else{
            return null;
    }
}
}
