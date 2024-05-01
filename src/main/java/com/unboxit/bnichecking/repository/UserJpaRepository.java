package com.unboxit.bnichecking.repository;

import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findUserByUserId(Long userId);
    User findUserByUsername(String username);
}
