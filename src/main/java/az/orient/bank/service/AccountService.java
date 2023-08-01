package az.orient.bank.service;

import az.orient.bank.dto.request.ReqAccount;
import az.orient.bank.dto.response.RespAccount;
import az.orient.bank.dto.response.Response;

import java.util.List;

public interface AccountService {
    Response<List<RespAccount>> GetAccountListByCustomerId(Long cusId);

    Response addAccount(ReqAccount reqAccount);
}
