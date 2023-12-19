package az.orient.bank.service;

import az.orient.bank.dto.request.ReqTransaction;
import az.orient.bank.dto.response.RespTransaction;
import az.orient.bank.dto.response.Response;

import java.util.List;

public interface TransactionService {
    Response<List<RespTransaction>> getTransactionListByAccountId(ReqTransaction reqTransaction);

    Response createTransaction(ReqTransaction reqTransaction);
}
