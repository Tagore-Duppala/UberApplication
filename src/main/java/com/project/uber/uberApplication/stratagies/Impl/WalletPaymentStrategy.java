package com.project.uber.uberApplication.stratagies.Impl;

import com.project.uber.uberApplication.entities.Driver;
import com.project.uber.uberApplication.entities.Payment;
import com.project.uber.uberApplication.entities.Rider;
import com.project.uber.uberApplication.entities.Wallet;
import com.project.uber.uberApplication.entities.enums.PaymentStatus;
import com.project.uber.uberApplication.entities.enums.TransactionMethod;
import com.project.uber.uberApplication.repositories.PaymentRepository;
import com.project.uber.uberApplication.repositories.WalletRepository;
import com.project.uber.uberApplication.services.PaymentService;
import com.project.uber.uberApplication.services.WalletService;
import com.project.uber.uberApplication.stratagies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {

    private final PaymentRepository paymentRepository;
    private final WalletService walletService;


    @Override
    public void processPayment(Payment payment) {

        double paymentAmount = payment.getAmount();
        Driver driver = payment.getRide().getDriver();
        Rider rider = payment.getRide().getRider();
        double driversCut = paymentAmount - (paymentAmount*PLATFORM_FEE);

        Wallet riderWallet =  walletService.deductMoneyFromWallet(rider.getUser(),payment.getAmount(),null,
                payment.getRide(), TransactionMethod.RIDE);

        Wallet driverWallet = walletService.addMoneyToWallet(driver.getUser(), driversCut,null,
                payment.getRide(),TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        paymentRepository.save(payment);

    }
}
