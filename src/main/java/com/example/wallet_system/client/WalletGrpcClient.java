package com.example.wallet_system.client;

import com.example.wallet.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

@Component
public class WalletGrpcClient {

    private final WalletServiceGrpc.WalletServiceBlockingStub blockingStub;

    public WalletGrpcClient() {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        blockingStub = WalletServiceGrpc.newBlockingStub(channel);
    }

    public String creditWallet(String idNumber, double amount) {

        CreditRequest creditRequest = com.example.wallet.CreditRequest.newBuilder()
                .setIdNumber(idNumber)
                .setAmount(amount)
                .build();

        CreditResponse response = blockingStub.creditWallet(creditRequest);
        return response.getMessage();
    }

    public double getBalance(String idNumber) {
        BalanceRequest balanceRequest = BalanceRequest.newBuilder()
                .setIdNumber(idNumber)
                .build();

        BalanceResponse response = blockingStub.getBalance(balanceRequest);
        return response.getBalance();
    }
}
