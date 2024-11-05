package com.project.uber.uberApplication.stratagies;

import com.project.uber.uberApplication.entities.Ride;
import com.project.uber.uberApplication.entities.enums.PaymentMethod;
import com.project.uber.uberApplication.stratagies.Impl.CashPaymentStrategy;
import com.project.uber.uberApplication.stratagies.Impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentStrategyManager {

    private final CashPaymentStrategy cashPaymentStrategy;
    private final WalletPaymentStrategy walletPaymentStrategy;

    public PaymentStrategy paymentMethod(Ride ride){

        return switch (ride.getPaymentMethod()){
            case CASH -> cashPaymentStrategy;
            case WALLET -> walletPaymentStrategy;
        };

    }

}
