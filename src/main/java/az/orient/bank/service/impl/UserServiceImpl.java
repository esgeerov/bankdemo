package az.orient.bank.service.impl;

import az.orient.bank.dto.request.ReqLogin;
import az.orient.bank.dto.request.ReqToken;
import az.orient.bank.dto.response.RespStatus;
import az.orient.bank.dto.response.RespToKen;
import az.orient.bank.dto.response.RespUser;
import az.orient.bank.dto.response.Response;
import az.orient.bank.entity.User;
import az.orient.bank.entity.UserToken;
import az.orient.bank.enums.EnumAviableStatus;
import az.orient.bank.exception.BankException;
import az.orient.bank.exception.ExceptionConstants;
import az.orient.bank.repository.UserRepository;
import az.orient.bank.repository.UserTokenRepository;
import az.orient.bank.service.UserService;
import az.orient.bank.util.Utility;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    private final Utility utility;

    @Override
    public Response<RespUser> login(ReqLogin reqLogin) {
        Response<RespUser> response = new Response<>();
        RespUser respUser = new RespUser();
        try {
            String username = reqLogin.getUsername();
            String password = reqLogin.getPassword();
            if (username == null || password == null) {
                throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA, "Invalid request data");
            }
            User user = userRepository.findUserByUsernameAndPasswordAndActive(username, password, EnumAviableStatus.ACTIVE.value);
            if (user == null) {
                throw new BankException(ExceptionConstants.INVALID_USER, "Invalid user");
            }
            String token = UUID.randomUUID().toString();
            UserToken userToken = new UserToken();
            userToken.setUser(user);
            userToken.setToken(token);
            userTokenRepository.save(userToken);
            respUser.setFullname(user.getFullname());
            respUser.setUsername(user.getUsername());
            respUser.setRespToKen(new RespToKen(user.getId(), token));
            response.setT(respUser);
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
    public Response logout(ReqToken reqToken) {
        Response response = new Response();
        try {
            UserToken userToken = utility.checkToken(reqToken.getToken(), reqToken.getUserId());
            userToken.setActive(EnumAviableStatus.DEACTIVE.value);
            userTokenRepository.save(userToken);
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
}
