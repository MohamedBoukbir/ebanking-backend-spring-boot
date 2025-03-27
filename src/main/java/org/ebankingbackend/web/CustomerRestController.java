package org.ebankingbackend.web;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ebankingbackend.dtos.CustomerDTO;
import org.ebankingbackend.entities.Customer;
import org.ebankingbackend.exceptions.CustomerNotFoundException;
import org.ebankingbackend.services.BankAccountService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
public class CustomerRestController {
    private BankAccountService bankAccountService;
    @GetMapping("/customers")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
public List<CustomerDTO> customers() {
return  bankAccountService.listCustomers();
}

    @GetMapping("/customers/search")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<CustomerDTO> searchCustomers(@RequestParam(name="keyword",defaultValue = "") String keyword) {

        return  bankAccountService.searchCustomers("%"+keyword+"%");
    }

@GetMapping("/customers/{id}")
@PreAuthorize("hasAuthority('SCOPE_USER')")
public CustomerDTO getCustomer(@PathVariable(name="id") Long customerId) throws CustomerNotFoundException {
return bankAccountService.getCustomer(customerId);
}
@PostMapping("/customers")
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO) {
return   bankAccountService.saveCustomer(customerDTO);
}
@PutMapping("/customers/{id}")
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
public CustomerDTO updateCustomer(@RequestBody CustomerDTO customerDTO, @PathVariable(name="id") Long customerId) {
   customerDTO.setId(customerId);
   return   bankAccountService.updateCustomer(customerDTO);
}
@DeleteMapping("customers/{id}")
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
public void deleteCustomer(@PathVariable(name="id") Long customerId) throws CustomerNotFoundException {
        bankAccountService.deleteCustomer(customerId);
}

}
