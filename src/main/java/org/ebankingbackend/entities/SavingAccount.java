package org.ebankingbackend.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("SA") // delete this for TABLE_PER_CLASS and join
public class SavingAccount extends BankAccount{
    private double interestRate;
}
