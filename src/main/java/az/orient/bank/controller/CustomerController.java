package az.orient.bank.controller;

import az.orient.bank.dto.request.ReqCustomer;
import az.orient.bank.dto.request.ReqToken;
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

    @PostMapping("/GetCustomerList")
    public Response<List<RespCustomer>> getCustomerList(@RequestBody ReqToken reqToken) {
        return customerService.getCustomerList(reqToken);
    }

    @PostMapping("/GetCustomerById")
    public Response<RespCustomer> getCustomerListById(@RequestBody ReqCustomer reqCustomer) {
        return customerService.getCustomerById(reqCustomer);
    }

    @PostMapping("/AddCustomer")
    public Response addCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.addCustomer(reqCustomer);
    }

    @PutMapping("/UpdateCustomer")
    public Response updateCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.updateCustomer(reqCustomer);
    }

    @PutMapping("/DeleteCustomer")
    public Response deleteCustomer(@RequestBody ReqCustomer reqCustomer) {
        return customerService.deleteCustomer(reqCustomer);
    }
}

