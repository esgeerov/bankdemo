package az.orient.bank.service.impl;

import az.orient.bank.dto.request.ReqAccount;
import az.orient.bank.dto.request.ReqToken;
import az.orient.bank.dto.response.RespAccount;
import az.orient.bank.dto.response.RespCustomer;
import az.orient.bank.dto.response.RespStatus;
import az.orient.bank.dto.response.Response;
import az.orient.bank.entity.Account;
import az.orient.bank.entity.Customers;
import az.orient.bank.enums.EnumAviableStatus;
import az.orient.bank.exception.BankException;
import az.orient.bank.exception.ExceptionConstants;
import az.orient.bank.repository.AccountRepository;
import az.orient.bank.repository.CustomerRepository;
import az.orient.bank.service.AccountService;
import az.orient.bank.service.CustomerService;
import az.orient.bank.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final Utility utility;

    @Override
    public Response<List<RespAccount>> GetAccountListByCustomerId(ReqAccount reqAccount) {
        Response<List<RespAccount>> response = new Response<>();
        try {
            Long custId = reqAccount.getCustomerId();
            ReqToken reqToken = reqAccount.getReqToken();
            utility.checkToken(reqToken.getToken(), reqToken.getUserId());
            if (custId == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid Request Data");
            }

            Customers customers = customerRepository.findByIdAndActive(custId, EnumAviableStatus.ACTIVE.value);
            if (customers == null) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            List<Account> accountList = accountRepository.findAllByCustomersAndActive(customers, EnumAviableStatus.ACTIVE.value);
            if (accountList.isEmpty()) {
                throw new BankException(ExceptionConstants.ACCOUNT_NOT_FOUND, "ACCOUNT NOT FOUND");
            }
            List<RespAccount> respAccountList = accountList.stream().map(account -> mapping(account)).collect(Collectors.toList());
            response.setT(respAccountList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INVALID EXCEPTION"));
        }

        return response;
    }

    @Override
    public Response addAccount(ReqAccount reqAccount) {
        Response response = new Response();
        try {
            ReqToken reqToken = reqAccount.getReqToken();
            utility.checkToken(reqToken.getToken(), reqToken.getUserId());


            String name = reqAccount.getName();
            String accountNo = reqAccount.getAcccountNo();
            String currency = reqAccount.getCurrency();
            String iban = reqAccount.getIban();
            Integer branchNo = reqAccount.getBranchNo();
            Long customerId = reqAccount.getCustomerId();
            if (name == null || accountNo == null || currency == null || iban == null || branchNo == null || customerId == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "INVALID REQUEST DATA");
            }
            Customers customers = customerRepository.findByIdAndActive(customerId, EnumAviableStatus.ACTIVE.value);
            if (customerId == null) {
                throw new BankException(ExceptionConstants.CUSTOMER_NOT_FOUND, "Customer not found");
            }
            Account account = Account.builder().name(name).accountNo(accountNo).iban(iban).currency(currency).branchNo(branchNo).customers(customers).build();

            accountRepository.save(account);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INVALID EXCEPTION"));
        }
        return response;
    }

    @Override
    public Account getAccountById(Long accId) {
        Account account = accountRepository.findAccountByIdAndActive(accId, EnumAviableStatus.ACTIVE.value);
        if (account == null) {
            throw new BankException(ExceptionConstants.ACCOUNT_NOT_FOUND, "Account not found");
        }
        return account;
    }

    private RespAccount mapping(Account account) {
        RespCustomer respCustomer = RespCustomer.builder().id(account.getCustomers().getId()).name(account.getCustomers().getName()).surname(account.getCustomers().getSurname()).address(account.getCustomers().getAddress()).dob(account.getCustomers().getDob()).pin(account.getCustomers().getPin()).phone(account.getCustomers().getPhone()).seria(account.getCustomers().getSeria()).build();
        return RespAccount.builder().accountId(account.getId()).name(account.getName()).accountNo(account.getAccountNo()).iban(account.getIban()).currency(account.getCurrency()).branchNo(account.getBranchNo()).respCustomer(respCustomer).build();
    }
}
