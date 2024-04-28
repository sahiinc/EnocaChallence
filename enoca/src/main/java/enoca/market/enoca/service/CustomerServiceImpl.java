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
        response.setMessage("Müşteri kaydı başarılı");

        Customer customer = new Customer();
        customer.setCommercialName(customerRequest.getCommercialName());
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());

        customer = customerRepository.save(customer);
        response.setId(customer.getId());

        return ResponseEntity.ok(response);
    }
}
