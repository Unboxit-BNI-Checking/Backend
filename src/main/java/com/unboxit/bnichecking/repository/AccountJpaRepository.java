package com.unboxit.bnichecking.repository;

import java.util.List;

import com.unboxit.bnichecking.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AccountJpaRepository extends JpaRepository<Account, Long> {

    Account findAccountByAccountNumber(String accountNumber);

    Account findAccountByAccountId(Long accountId);

//    @Query(value="SELECT * FROM users WHERE name=?1 AND phone_number = ?2", nativeQuery = true)
//    List<User> findByUserNameAndUserPhoneNumber(String userName, String userPhoneNumber);
//
//    List<User> findByUserPhoneNumber(String userPhoneNumber);

}
