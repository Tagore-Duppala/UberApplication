package com.project.uber.uberApplication.services;

import com.project.uber.uberApplication.dto.WalletTransactionDto;
import com.project.uber.uberApplication.entities.WalletTransaction;

public interface WalletTransactionService {

    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
