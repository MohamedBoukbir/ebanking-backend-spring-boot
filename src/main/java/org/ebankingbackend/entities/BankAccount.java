package org.ebankingbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.ebankingbackend.enums.AccountStatus;

import java.util.Date;
import java.util.List;
@Entity
@Data @NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE",length = 4,discriminatorType=DiscriminatorType.STRING)
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
    // public abstract class  backAccount { //to use  TABLE_PER_CLASS
//@Inheritance(strategy = InheritanceType.JOINED)
public class BankAccount {
    @Id
    private String id;
    private double balance;
    private Date createdAt;
    @Enumerated(EnumType.STRING)
    private AccountStatus status;
    @ManyToOne
    private Customer customer;
//    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.EAGER)
    @OneToMany(mappedBy = "bankAccount",fetch = FetchType.LAZY)
    private List<AccountOperation> accountOperations;
}
