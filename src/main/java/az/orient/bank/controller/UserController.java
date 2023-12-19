package az.orient.bank.controller;

import az.orient.bank.dto.request.ReqLogin;
import az.orient.bank.dto.request.ReqToken;
import az.orient.bank.dto.response.RespUser;
import az.orient.bank.dto.response.Response;
import az.orient.bank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor

public class UserController{
    private final UserService userService;


    @PostMapping("/login")
    public Response<RespUser> login(@RequestBody ReqLogin reqLogin){
        return userService.login(reqLogin);
    }

    @PostMapping("/logout")
    public Response logout(@RequestBody ReqToken reqToken) {
        return userService.logout(reqToken);
    }
}


