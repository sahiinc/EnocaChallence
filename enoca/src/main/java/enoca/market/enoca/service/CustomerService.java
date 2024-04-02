package enoca.market.enoca.service;

import enoca.market.enoca.dto.CustomerRequest;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity addCustomer(CustomerRequest customerRequest);

}
