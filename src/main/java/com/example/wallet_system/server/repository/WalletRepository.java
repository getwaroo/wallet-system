package com.example.wallet_system.server.repository;

import com.example.wallet_system.server.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {

    Wallet findByIdNumber(String idNumber);

}
