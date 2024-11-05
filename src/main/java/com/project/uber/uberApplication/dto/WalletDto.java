package com.project.uber.uberApplication.dto;

import com.project.uber.uberApplication.entities.User;
import com.project.uber.uberApplication.entities.WalletTransaction;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WalletDto {

    private Long id;

    private User user;

    private Double balance;

    private List<WalletTransactionDto> transactions;
}
