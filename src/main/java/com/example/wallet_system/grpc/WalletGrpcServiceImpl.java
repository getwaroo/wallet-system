package com.example.wallet_system.grpc;

import com.example.wallet.*;
import com.example.wallet_system.server.service.WalletService;
import io.grpc.stub.StreamObserver;
import org.springframework.beans.factory.annotation.Autowired;

public class WalletGrpcServiceImpl extends WalletServiceGrpc.WalletServiceImplBase implements WalletGrpcService {

    @Autowired
    private final WalletService walletService;

    public WalletGrpcServiceImpl(WalletService walletService) {
        this.walletService = walletService;
    }

    @Override
    public void creditWallet(CreditRequest request, StreamObserver<CreditResponse> responseObserver) {
        String userId = request.getIdNumber();
        double amount = request.getAmount();
        CreditResponse.Builder responseBuilder = CreditResponse.newBuilder();

        if (amount <= 0) {
            responseObserver.onError(new IllegalArgumentException("Amount must be greater than 0"));
            return;
        }

        try {
            walletService.creditWallet(userId, amount);
            responseBuilder.setStatus("Okay")
                    .setMessage("Account credited successfully")
                    .build();
        } catch (Exception e) {
            responseBuilder.setMessage("Failed to credit amount: " + e.getMessage());
        }

        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();

    }

    @Override
    public void getBalance(BalanceRequest request, StreamObserver<BalanceResponse> responseObserver) {
        String userId = request.getIdNumber();
        Integer amount = walletService.getBalance(userId);
        BalanceResponse response = BalanceResponse.newBuilder()
                .setUserId(userId)
                .setBalance(amount)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
