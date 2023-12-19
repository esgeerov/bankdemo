package az.orient.bank.service.impl;

import az.orient.bank.dto.request.ReqToken;
import az.orient.bank.dto.request.ReqTransaction;
import az.orient.bank.dto.response.RespStatus;
import az.orient.bank.dto.response.RespTransaction;
import az.orient.bank.dto.response.Response;
import az.orient.bank.entity.Account;
import az.orient.bank.entity.Transaction;
import az.orient.bank.enums.EnumAviableStatus;
import az.orient.bank.exception.BankException;
import az.orient.bank.exception.ExceptionConstants;
import az.orient.bank.repository.AccountRepository;
import az.orient.bank.repository.TransactionRepository;
import az.orient.bank.service.AccountService;
import az.orient.bank.service.TransactionService;
import az.orient.bank.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final AccountService accountService;
    private final TransactionRepository transactionRepository;
    private final Utility utility;

    @Override
    public Response<List<RespTransaction>> getTransactionListByAccountId(ReqTransaction reqTransaction) {
        Response<List<RespTransaction>> response = new Response<>();
        try {
            Long accId=reqTransaction.getDtAccountId();
            ReqToken reqToken = reqTransaction.getReqToken();
            utility.checkToken(reqToken.getToken(), reqToken.getUserId());
            if (accId == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            Account account = accountService.getAccountById(accId);
            List<Transaction> transactionList = transactionRepository.findAllByActiveAndDtAccount(EnumAviableStatus.ACTIVE.value, account);
            if (transactionList == null || transactionList.isEmpty()) {
                throw new BankException(ExceptionConstants.TRANSACTION_NOT_FOUND, "Transaction not found");
            }
            List<RespTransaction> respTransactionList = transactionList.stream().map(this::mapper).collect(Collectors.toList());
            response.setT(respTransactionList);
            response.setStatus(RespStatus.getSuccessMessage());
        } catch (
                BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INVALID EXCEPTION"));
        }
        return response;
    }

    @Override
    public Response createTransaction(ReqTransaction reqTransaction) {
        Response response = new Response();
        try {
            ReqToken reqToken = reqTransaction.getReqToken();
            utility.checkToken(reqToken.getToken(), reqToken.getUserId());
            Double amount = reqTransaction.getAmount();
            String currency = reqTransaction.getCurrency();
            Long dtAccountId = reqTransaction.getDtAccountId();
            String crAccount = reqTransaction.getCrAccount();
            if ( amount == null || currency == null || dtAccountId == null || crAccount == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
           Account dtAccount=accountService.getAccountById(dtAccountId);
           Transaction transaction=Transaction.builder()
                   .dtAccount(dtAccount)
                   .amount(amount)
                   .crAccount(crAccount)
                   .currency(currency)
                   .build();
           transactionRepository.save(transaction);
           response.setStatus(RespStatus.getSuccessMessage());

        } catch (
                BankException ex) {
            ex.printStackTrace();
            response.setStatus(new RespStatus(ex.getCode(), ex.getMessage()));

        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(new RespStatus(ExceptionConstants.INTERNAL_EXCEPTION, "INVALID EXCEPTION"));
        }

        return response;
    }

    public RespTransaction mapper(Transaction transaction) {
        return RespTransaction.builder()
                .trId(transaction.getId())
                .dtAccount(transaction.getDtAccount() != null ? transaction.getDtAccount().getIban() : null)
                .amount(transaction.getAmount())
                .currency(transaction.getCurrency())
                .crAccount(transaction.getCrAccount())
                .custname(transaction.getDtAccount() != null ? transaction.getDtAccount().getCustomers().getName() + " " + transaction.getDtAccount().getCustomers().getSurname() : null)
                .trDate(transaction.getPaydataDate())
                .build();

    }
}
