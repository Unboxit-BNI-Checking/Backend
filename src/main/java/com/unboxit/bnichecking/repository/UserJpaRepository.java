package com.unboxit.bnichecking.repository;

import com.unboxit.bnichecking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    User findUserByUserId(Long userId);
    User findUserByUsername(String username);


}
