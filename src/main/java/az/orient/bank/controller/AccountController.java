package az.orient.bank.controller;

import az.orient.bank.dto.request.ReqAccount;
import az.orient.bank.dto.response.RespAccount;
import az.orient.bank.dto.response.RespCustomer;
import az.orient.bank.dto.response.Response;
import az.orient.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    @GetMapping("/GetAccountListByCustomerId/{custId}")
    public Response<List<RespAccount>> GetAccountListByCustomerId(@PathVariable Long custId){

        return accountService.GetAccountListByCustomerId(custId);
    }
    @PostMapping("/addAccount")
    public Response addAccount(@RequestBody ReqAccount reqAccount){
        return accountService.addAccount(reqAccount);
    }



}
