package com.example.wallet_system.server.service;


public interface WalletService {

    public void creditWallet(String id_number, double amount);

    public Integer getBalance(String id_number);
}
