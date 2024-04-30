package com.unboxit.bnichecking.repository;

import com.unboxit.bnichecking.model.Admins;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminJpaRepository extends JpaRepository<Admins, Long> {

    Admins findAdminsByUsername(String username);
}
