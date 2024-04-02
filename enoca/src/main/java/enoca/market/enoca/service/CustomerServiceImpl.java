package enoca.market.enoca.service;

import enoca.market.enoca.dto.CustomerRequest;
import enoca.market.enoca.dto.CustomerResponse;
import enoca.market.enoca.entity.Customer;
import enoca.market.enoca.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public ResponseEntity addCustomer(CustomerRequest customerRequest) {

        CustomerResponse response = new CustomerResponse();
        response.code = 0;
        response.status = "succeed";
        response.message = "Müşteri kaydı başarılı";

        Customer customer = new Customer();
        customer.commercialName = customerRequest.commercialName;
        customer.firstName = customerRequest.firstName;
        customer.lastName = customerRequest.lastName;

        customer = customerRepository.save(customer);
        response.id = customer.id;

        return ResponseEntity.ok(response);
    }
}
