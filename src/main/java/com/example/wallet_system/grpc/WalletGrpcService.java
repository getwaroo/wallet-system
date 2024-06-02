package com.example.wallet_system.grpc;

import com.example.wallet.BalanceRequest;
import com.example.wallet.BalanceResponse;
import com.example.wallet.CreditRequest;
import com.example.wallet.CreditResponse;
import io.grpc.stub.StreamObserver;

public interface WalletGrpcService {

    void creditWallet(CreditRequest request, StreamObserver<CreditResponse> responseObserver);

    void getBalance(BalanceRequest request, StreamObserver<BalanceResponse> responseObserver);

}
