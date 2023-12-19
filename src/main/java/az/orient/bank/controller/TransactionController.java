package az.orient.bank.controller;

import az.orient.bank.dto.request.ReqTransaction;
import az.orient.bank.dto.response.RespTransaction;
import az.orient.bank.dto.response.Response;
import az.orient.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    @GetMapping("/GetTransactionListByAccountId")
    public Response<List<RespTransaction>> getTransactionListByAccountId(@RequestBody ReqTransaction reqTransaction){
        return transactionService.getTransactionListByAccountId(reqTransaction);
    }
    @PostMapping("/CreateTransaction")
    public Response CreateTransaction(@RequestBody ReqTransaction reqTransaction){
        return transactionService.createTransaction(reqTransaction);
    }
}
