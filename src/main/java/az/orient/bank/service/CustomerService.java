package az.orient.bank.service;

import az.orient.bank.dto.request.ReqCustomer;
import az.orient.bank.dto.response.RespCustomer;
import az.orient.bank.dto.response.Response;

import java.util.List;

public interface CustomerService {
    Response<List<RespCustomer>> getCustomerList();

    Response<RespCustomer> getCustomerById(Long customerId);

    Response addCustomer(ReqCustomer reqCustomer);

    Response updateCustomer(ReqCustomer reqCustomer);

    Response deleteCustomer(Long customerId);
}
