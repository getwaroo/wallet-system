package com.example.wallet_system.server.repository;

import com.example.wallet_system.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
