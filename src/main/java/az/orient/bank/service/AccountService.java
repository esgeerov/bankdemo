package az.orient.bank.service;

import az.orient.bank.dto.request.ReqAccount;
import az.orient.bank.dto.response.RespAccount;
import az.orient.bank.dto.response.Response;
import az.orient.bank.entity.Account;

import java.util.List;

public interface AccountService {
    Response<List<RespAccount>> GetAccountListByCustomerId(ReqAccount reqAccount);

    Response addAccount(ReqAccount reqAccount);

    Account getAccountById(Long accId);
}
