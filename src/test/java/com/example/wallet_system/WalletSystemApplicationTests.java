package com.example.wallet_system;

import com.example.wallet_system.client.WalletGrpcClient;
import com.example.wallet_system.grpc.WalletGrpcServiceImpl;
import com.example.wallet_system.server.repository.WalletRepository;
import com.example.wallet_system.server.service.WalletService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringBootWalletGrpcApplicationTests {

	@Autowired
	private WalletGrpcServiceImpl walletGrpcServiceImpl;

	@Autowired
	private WalletService walletService;

	@Autowired
	private WalletRepository walletRepository;

	private static WalletGrpcClient walletGrpcClient;

	@BeforeAll
	static void setup() {
		walletGrpcClient = new WalletGrpcClient();
	}

	@Test
	void contextLoads() {
		assertThat(walletGrpcServiceImpl).isNotNull();
		assertThat(walletService).isNotNull();
		assertThat(walletRepository).isNotNull();
	}

	@Test
	void creditWalletGrpcServiceTest() {
		String userId = "testuser";
		double amount = 100.0;
		String responseMessage = walletGrpcClient.creditWallet(userId, amount);
		assertThat(responseMessage).isEqualTo("Amount credited successfully");

		double balance = walletGrpcClient.getBalance(userId);
		assertThat(balance).isEqualTo(100.0);
	}

	@Test
	void getBalanceGrpcServiceTest() {
		String userId = "testuser";
		double balance = walletGrpcClient.getBalance(userId);
		assertThat(balance).isEqualTo(100.0);
	}
}
