package az.orient.bank.service;

import az.orient.bank.dto.request.ReqLogin;
import az.orient.bank.dto.request.ReqToken;
import az.orient.bank.dto.response.RespUser;
import az.orient.bank.dto.response.Response;

public interface UserService {
    Response<RespUser> login(ReqLogin reqLogin);

    Response logout(ReqToken reqToken);
}
