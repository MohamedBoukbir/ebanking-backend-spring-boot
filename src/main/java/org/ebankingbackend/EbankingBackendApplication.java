package org.ebankingbackend;

import org.ebankingbackend.dtos.BankAccountDTO;
import org.ebankingbackend.dtos.CurrentBankAccountDTO;
import org.ebankingbackend.dtos.CustomerDTO;
import org.ebankingbackend.dtos.SavingBankAccountDTO;
import org.ebankingbackend.entities.*;
import org.ebankingbackend.enums.AccountStatus;
import org.ebankingbackend.enums.OperationType;
import org.ebankingbackend.exceptions.BalanceNotSufficientException;
import org.ebankingbackend.exceptions.BankAccountNotFoundException;
import org.ebankingbackend.exceptions.CustomerNotFoundException;
import org.ebankingbackend.repositories.AccountOperationRepository;
import org.ebankingbackend.repositories.BankAccountRepository;
import org.ebankingbackend.repositories.CustomerRepository;
import org.ebankingbackend.services.BankAccountService;
import org.ebankingbackend.services.BankService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;


@SpringBootApplication
public class EbankingBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(EbankingBackendApplication.class, args);
    }

    @Bean
    CommandLineRunner start1(BankAccountService bankAccountService){
        return args -> {

            Stream.of("Hassan","Imane","Mohamed").forEach(name -> {
                CustomerDTO customer = new CustomerDTO();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                bankAccountService.saveCustomer(customer);
            });
            bankAccountService.listCustomers().forEach(customer->{
                try {
                    bankAccountService.saveCurrentBankAccount(Math.random()*90000,9000,customer.getId());
                    bankAccountService.saveSavingBankAccount(Math.random()*120000,5.5,customer.getId());

                } catch (CustomerNotFoundException  e) {
                    e.printStackTrace();
                }
            });

            List<BankAccountDTO>  bankAccountList =bankAccountService.bankAccountList();
            for (BankAccountDTO bankAccount:bankAccountList){
                for (int i=0;i<10;i++){
                    String accountId;
                    if(bankAccount instanceof SavingBankAccountDTO){
                        accountId  = ((SavingBankAccountDTO) bankAccount).getId();

                    }else{
                        accountId  = ((CurrentBankAccountDTO) bankAccount).getId();
                    }
                    bankAccountService.credit(accountId,10000+Math.random()*120000,"credit");
                    bankAccountService.debit(accountId,1000+Math.random()*1200,"debit");
                }

            }

        };
    }



    //@Bean
    CommandLineRunner start(CustomerRepository customerRepository,
                            BankAccountRepository bankAccountRepository, AccountOperationRepository accountOperationRepository){
        return args -> {
            Stream.of("Hassan","Yassine","Aicha").forEach(name -> {
                Customer customer = new Customer();
                customer.setName(name);
                customer.setEmail(name + "@gmail.com");
                customerRepository.save(customer);
            });
            customerRepository.findAll().forEach(cust -> {
                CurrentAccount currentAccount = new CurrentAccount();
                currentAccount.setId(UUID.randomUUID().toString());
                currentAccount.setBalance(Math.random()*9000);
                currentAccount.setCreatedAt(new Date());
                currentAccount.setCustomer(cust);
                currentAccount.setStatus(AccountStatus.CREATED);
                currentAccount.setOverDraft(9000);
                bankAccountRepository.save(currentAccount);

                SavingAccount savingAccount = new SavingAccount();
                savingAccount.setBalance(Math.random()*9000);
                savingAccount.setId(UUID.randomUUID().toString());
                savingAccount.setCreatedAt(new Date());
                savingAccount.setCustomer(cust);
                savingAccount.setStatus(AccountStatus.CREATED);
                savingAccount.setInterestRate(5.5);
                bankAccountRepository.save(savingAccount);


            });
            bankAccountRepository.findAll().forEach(acc->{
                for(int i=0;i<10;i++){
                    AccountOperation op = new AccountOperation();
                    op.setOperationDate(new Date());
                    op.setAmount(Math.random()*12000);
                    op.setType(Math.random()>0.5 ? OperationType.DEBIT :OperationType.CREDIT);
                    op.setBankAccount(acc);
                    accountOperationRepository.save(op);
                }

            });


        };
    }


}
