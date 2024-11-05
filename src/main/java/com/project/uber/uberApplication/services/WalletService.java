package com.project.uber.uberApplication.services;

import com.project.uber.uberApplication.entities.Ride;
import com.project.uber.uberApplication.entities.User;
import com.project.uber.uberApplication.entities.Wallet;
import com.project.uber.uberApplication.entities.enums.TransactionMethod;

public interface WalletService {

    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);
}
