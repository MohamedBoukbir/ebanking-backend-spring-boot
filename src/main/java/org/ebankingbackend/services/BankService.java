package org.ebankingbackend.services;

import lombok.AllArgsConstructor;
import org.ebankingbackend.entities.BankAccount;
import org.ebankingbackend.entities.CurrentAccount;
import org.ebankingbackend.entities.SavingAccount;
import org.ebankingbackend.repositories.BankAccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class BankService {
    private BankAccountRepository bankAccountRepository;
    public void consulter (){
        BankAccount bankAccount =bankAccountRepository.findById("28bed36a-92f7-4ae3-bd9b-decc5ab439a2").orElse(null);
        if (bankAccount !=null){
            System.out.println("***********************************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getCreatedAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if(bankAccount instanceof CurrentAccount){
                System.out.println ("Over Draft => "+ ((CurrentAccount)bankAccount).getOverDraft());

            }else if(bankAccount instanceof SavingAccount){
                System.out.println ("Rate => "+((SavingAccount)bankAccount).getInterestRate());

            }
            bankAccount.getAccountOperations().forEach(op->{
                System.out.println(op.getType() +"\t" + op.getOperationDate() +"\t" +op.getAmount());


            });
        }

    }
}
