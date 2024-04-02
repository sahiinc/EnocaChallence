package enoca.market.enoca.controllerv1;


import enoca.market.enoca.dto.CustomerRequest;
import enoca.market.enoca.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/addCustomer")
    public ResponseEntity addCustomer(@RequestBody CustomerRequest customerRequest) {
        System.out.println("addCustomer start");
        System.out.println("customerRequest: " + customerRequest);
        return customerService.addCustomer(customerRequest);
    }



}
