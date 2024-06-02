package com.example.wallet_system.server.repository;

import com.example.wallet_system.server.entity.Wallet;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class WalletRepositoryCustomImpl implements WalletRepositoryCustom {

    private EntityManager entityManager;

    @Override
    public Wallet findByIdNumber(String idNumber) {
        String jpql = "SELECT w FROM Wallet w JOIN w.user u WHERE u.idNumber = :idNumber";
        TypedQuery<Wallet> query = entityManager.createQuery(jpql, Wallet.class);
        query.setParameter("idNumber", idNumber);
        return query.getSingleResult();
    }
}
