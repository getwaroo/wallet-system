package com.example.wallet_system.server.service;

import com.example.wallet.WalletServiceGrpc;
import com.example.wallet_system.server.entity.User;
import com.example.wallet_system.server.entity.Wallet;
import com.example.wallet_system.server.repository.UserRepository;
import com.example.wallet_system.server.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Optional;

public class WalletServiceImpl extends WalletServiceGrpc.WalletServiceImplBase implements WalletService {

    @Autowired
    private final WalletRepository walletRepository;

    @Autowired
    private final UserRepository userRepository;

    public WalletServiceImpl(WalletRepository walletRepository, UserRepository userRepository) {
        this.walletRepository = walletRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void creditWallet(String id_number, double amount) {

        Optional<User> user = userRepository.findById(id_number);

        user.ifPresentOrElse(user1 -> {
            Wallet wallet = new Wallet();
            wallet.setUser(user1);
            wallet.setAmount(1500);
            wallet.setDate(LocalDate.now());
            walletRepository.save(wallet);
        }, () -> {
            throw new RuntimeException("User not found");
        });

    }

    @Override
    public Integer getBalance(String id_number) {
        Wallet wallet = walletRepository.findByIdNumber(id_number);
        if (Objects.isNull(wallet)) {
            throw new RuntimeException("Invalid id number");
        }
        return wallet.getAmount();
    }
}
