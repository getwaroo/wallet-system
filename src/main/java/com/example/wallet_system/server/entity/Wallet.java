package com.example.wallet_system.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@NoArgsConstructor
public class Wallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "wallet_id")
    private Integer walletId;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "date")
    private LocalDate date;

    @JoinColumn(name = "id_number", unique = true)
    @OneToOne(fetch = FetchType.LAZY)
    private User user;
}
