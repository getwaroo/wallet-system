package com.example.wallet_system.server.repository;

import com.example.wallet_system.server.entity.Wallet;

public interface WalletRepositoryCustom {
    Wallet findByIdNumber(String idNumber);
}
