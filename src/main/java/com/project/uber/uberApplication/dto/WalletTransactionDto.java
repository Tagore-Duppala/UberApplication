package com.project.uber.uberApplication.dto;

import com.project.uber.uberApplication.entities.Ride;
import com.project.uber.uberApplication.entities.Wallet;
import com.project.uber.uberApplication.entities.enums.TransactionMethod;
import com.project.uber.uberApplication.entities.enums.TransactionType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletTransactionDto {

    private Long id;

    private Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;

    private RideDto ride;

    private String transactionId;

    private WalletDto wallet;

    private LocalDateTime timestamp;

}
