package az.orient.bank.controller;

import az.orient.bank.dto.request.ReqCustomer;
import az.orient.bank.dto.response.RespCustomer;
import az.orient.bank.dto.response.Response;
import az.orient.bank.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("customer")
public class CustomerController {
    private final CustomerService customerService;
    @GetMapping("GetCustomerList")
    public Response<List<RespCustomer>> getCustomerList(){
        return customerService.getCustomerList();
    }
    @GetMapping("GetCustomerById/{id}")
    public  Response<RespCustomer> getCustomerListById(@PathVariable Long id){
        return customerService.getCustomerById(id);
    }
    @PostMapping("/AddCustomer")
    public Response addCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.addCustomer(reqCustomer);
    }
    @PutMapping("/UpdateCustomer")
    public Response updateCustomer(@RequestBody ReqCustomer reqCustomer){
        return customerService.updateCustomer(reqCustomer);
    }
    @PutMapping("/DeleteCustomer/{customerId}")
    public Response deleteCustomer(@PathVariable Long customerId) {
        return customerService.deleteCustomer(customerId);
    }
}

