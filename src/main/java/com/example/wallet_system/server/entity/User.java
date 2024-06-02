package com.example.wallet_system.server.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id_number")
    private String idNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;
}
