package com.project.uber.uberApplication.stratagies.Impl;

import com.project.uber.uberApplication.entities.Driver;
import com.project.uber.uberApplication.entities.Payment;
import com.project.uber.uberApplication.entities.Wallet;
import com.project.uber.uberApplication.entities.enums.PaymentStatus;
import com.project.uber.uberApplication.entities.enums.TransactionMethod;
import com.project.uber.uberApplication.repositories.PaymentRepository;
import com.project.uber.uberApplication.services.WalletService;
import com.project.uber.uberApplication.stratagies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//Total payment = 100
//platform fee = 30

@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {

    private final WalletService walletService;
    private final PaymentRepository paymentRepository;



    @Override
    public void processPayment(Payment payment) {

        double paymentAmount = payment.getAmount();
        Driver driver = payment.getRide().getDriver();
        Wallet driverWallet = walletService.findByUser(driver.getUser());
        double driversCut = paymentAmount - (paymentAmount*PLATFORM_FEE);

        Wallet updatedDriverWallet = walletService.deductMoneyFromWallet(driver.getUser(),
                driversCut,null,payment.getRide(), TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.COMPLETED);
        paymentRepository.save(payment);

    }
}
