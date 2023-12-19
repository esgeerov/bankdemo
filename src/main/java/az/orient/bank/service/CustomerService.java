package az.orient.bank.service;

import az.orient.bank.dto.request.ReqCustomer;
import az.orient.bank.dto.request.ReqToken;
import az.orient.bank.dto.response.RespCustomer;
import az.orient.bank.dto.response.Response;

import java.util.List;

public interface CustomerService {
    Response<List<RespCustomer>> getCustomerList(ReqToken reqToken);

    Response<RespCustomer> getCustomerById(ReqCustomer reqCustomer);

    Response addCustomer(ReqCustomer reqCustomer);

    Response updateCustomer(ReqCustomer reqCustomer);

    Response deleteCustomer(ReqCustomer reqCustomer);
}
