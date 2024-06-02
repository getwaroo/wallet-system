package com.example.wallet_system.client.controller;

import com.example.wallet_system.client.WalletGrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private final WalletGrpcClient walletGrpcClient;

    public WalletController(WalletGrpcClient walletGrpcClient) {
        this.walletGrpcClient = walletGrpcClient;
    }

    @PostMapping("/credit")
    public String creditWallet(@RequestParam String userId, @RequestParam double amount) {
        return walletGrpcClient.creditWallet(userId, amount);
    }

    @GetMapping("/balance")
    public double getBalance(@RequestParam String userId) {
        return walletGrpcClient.getBalance(userId);
    }
}
