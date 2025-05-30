package com.maidservices.repo;

import com.maidservices.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
        User save(User user);

}
