package com.project.uber.uberApplication.services.Impl;

import com.project.uber.uberApplication.entities.Ride;
import com.project.uber.uberApplication.entities.User;
import com.project.uber.uberApplication.entities.Wallet;
import com.project.uber.uberApplication.entities.WalletTransaction;
import com.project.uber.uberApplication.entities.enums.TransactionMethod;
import com.project.uber.uberApplication.entities.enums.TransactionType;
import com.project.uber.uberApplication.exceptions.ResourceNotFoundException;
import com.project.uber.uberApplication.repositories.WalletRepository;
import com.project.uber.uberApplication.services.WalletService;
import com.project.uber.uberApplication.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WalletServiceImp implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletTransactionService walletTransactionService;

    @Override
    @Transactional
    public Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);

        wallet.setBalance(wallet.getBalance()+amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .transactionMethod(transactionMethod)
                .transactionType(TransactionType.CREDIT)
                .amount(amount)
                .wallet(wallet)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet deductMoneyFromWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod) {
        Wallet wallet = findByUser(user);
        if(wallet.getBalance()<amount) throw new RuntimeException("There is no enough balance in the wallet, Please add money to continue, Balance: "+wallet.getBalance());
        wallet.setBalance(wallet.getBalance()-amount);

        WalletTransaction walletTransaction = WalletTransaction.builder()
                .transactionId(transactionId)
                .ride(ride)
                .transactionMethod(transactionMethod)
                .transactionType(TransactionType.DEBIT)
                .amount(amount)
                .wallet(wallet)
                .build();

        walletTransactionService.createNewWalletTransaction(walletTransaction);

        wallet.getTransactions().add(walletTransaction);
        return walletRepository.save(wallet);
    }

    @Override
    public void withdrawAllMyMoneyFromWallet() {

    }

    @Override
    public Wallet findWalletById(Long walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(()->new ResourceNotFoundException("Wallet not found with Id: "+walletId));
    }

    @Override
    public Wallet createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        return walletRepository.save(wallet);
    }

    @Override
    public Wallet findByUser(User user) {
        return walletRepository.findByUser(user);
    }
}
