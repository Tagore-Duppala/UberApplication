package com.project.uber.uberApplication.services.Impl;

import com.project.uber.uberApplication.dto.WalletTransactionDto;
import com.project.uber.uberApplication.entities.WalletTransaction;
import com.project.uber.uberApplication.repositories.WalletTransactionRepository;
import com.project.uber.uberApplication.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {

    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {
        walletTransactionRepository.save(walletTransaction);
    }
}
