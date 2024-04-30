package com.unboxit.bnichecking.repository;

import com.unboxit.bnichecking.entity.http.response.GetAllUser;
import com.unboxit.bnichecking.model.Account;
import com.unboxit.bnichecking.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserJpaRepository extends JpaRepository<User, Long>  {

    @Query(value="SELECT * FROM users WHERE user_id=?1", nativeQuery = true)
    User findUserByUserId(long user_id);
}
