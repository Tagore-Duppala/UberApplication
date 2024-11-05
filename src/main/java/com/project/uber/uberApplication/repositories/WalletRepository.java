package com.project.uber.uberApplication.repositories;

import com.project.uber.uberApplication.entities.User;
import com.project.uber.uberApplication.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {

    Wallet findByUser(User user);

}
