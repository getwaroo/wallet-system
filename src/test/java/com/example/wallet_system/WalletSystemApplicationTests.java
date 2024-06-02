package com.example.wallet_system;

import com.example.wallet_system.client.WalletGrpcClient;
import com.example.wallet_system.grpc.WalletGrpcServiceImpl;
import com.example.wallet_system.server.entity.User;
import com.example.wallet_system.server.entity.Wallet;
import com.example.wallet_system.server.repository.UserRepository;
import com.example.wallet_system.server.repository.WalletRepository;
import com.example.wallet_system.server.service.WalletService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class SpringBootWalletGrpcApplicationTests {

    @Autowired
    private WalletGrpcServiceImpl walletGrpcServiceImpl;

    @Autowired
    private WalletService walletService;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private UserRepository userRepository;

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

        // Arrange
        User user = new User();
        user.setIdNumber("12345");
        user.setName("TestUser");
        user.setAddress("Flacq");
        userRepository.save(user);

        double amount = 100.0;

        String responseMessage = walletGrpcClient.creditWallet(user.getIdNumber(), amount);
        assertThat(responseMessage).isEqualTo("Amount credited successfully");

        // Assert
        Optional<Wallet> walletOpt = Optional.ofNullable(walletRepository.findByIdNumber(user.getIdNumber()));
        assertThat(walletOpt).isNotNull();
        assertThat(walletOpt.get().getAmount()).isEqualTo(amount);
        assertThat(walletOpt.get().getDate()).isEqualTo(LocalDate.now());
        assertThat(walletOpt.get().getUser()).isEqualTo(user);
    }

    @Test
    void creditWalletGrpcServiceTestInvalidUser() {

        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> walletService.creditWallet("1245", 1500),
                "Expected creditWallet to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("User not found"));

    }

    @Test
    void getBalanceGrpcServiceTest() {
        User user = new User();
        user.setIdNumber("4722");
        user.setName("TestUserBal");
        user.setAddress("Curepipe");
        userRepository.save(user);

        double amount = 1500;

        walletGrpcClient.creditWallet(user.getIdNumber(), amount);
        double amountQuery = walletGrpcClient.getBalance(user.getIdNumber());
        assertThat(amount).isEqualTo(amountQuery);
    }

    @Test
    void getBalanceGrpcServiceTestInvalidUser() {
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> walletService.getBalance("invalid_id"),
                "Expected getBalance to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().contains("Invalid id number"));
    }
}
