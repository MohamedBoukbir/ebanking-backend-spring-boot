package org.ebankingbackend.repositories;

import org.ebankingbackend.entities.BankAccount;
import org.ebankingbackend.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, String> {
}
