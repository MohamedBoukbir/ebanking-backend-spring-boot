package org.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ebankingbackend.dtos.CustomerDTO;
import org.ebankingbackend.entities.Customer;
import org.ebankingbackend.exceptions.CustomerNotFoundException;
import org.ebankingbackend.services.BankAccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
public class CustomerRestController {
    private BankAccountService bankAccountService;

    @GetMapping("/customers")
public List<CustomerDTO> customers() {
return  bankAccountService.listCustomers();
}
@GetMapping("/customers/{id}")
public CustomerDTO getCustomer(@PathVariable(name="id") Long customerId) throws CustomerNotFoundException {
return bankAccountService.getCustomer(customerId);
}
@PostMapping("/customers")
public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
return   bankAccountService.saveCustomer(customerDTO);
}
@PutMapping("/customers/{id}")
public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable(name="id") Long customerId) {
   customerDTO.setId(customerId);
   return   bankAccountService.updateCustomer(customerDTO);
}
@DeleteMapping("customers/{id}")
public void deleteCustomer(@PathVariable(name="id") Long customerId) throws CustomerNotFoundException {}

}
