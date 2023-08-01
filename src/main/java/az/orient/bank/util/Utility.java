package az.orient.bank.util;

import az.orient.bank.entity.User;
import az.orient.bank.entity.UserToken;
import az.orient.bank.enums.EnumAviableStatus;
import az.orient.bank.exception.BankException;
import az.orient.bank.exception.ExceptionConstants;
import az.orient.bank.repository.UserRepository;
import az.orient.bank.repository.UserTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Utility {
    private  final UserRepository userRepository;
    private final UserTokenRepository userTokenRepository;
    public UserToken checkToken(String token,Long userId){
        if (token==null|| userId==null){
            throw new BankException(ExceptionConstants.INVALID_REQUEST_DATA,"Invalid request data");
        }
        User user= userRepository.findUserByIdAndActive(userId, EnumAviableStatus.ACTIVE.value);
        if (user==null){
            throw new BankException(ExceptionConstants.INVALID_USER,"Invalid user");
        }
        UserToken userToken= userTokenRepository.findUserTokenByUserAndToken(user,token);
        if(userToken==null){
            throw new BankException(ExceptionConstants.INVALID_TOKEN,"INVALID TOKEN");
        }
        if(userToken.getActive()==EnumAviableStatus.DEACTIVE.value){
            throw new BankException(ExceptionConstants.TOKEN_EXPIRED,"Token expired");

        }
        return userToken;
    }
}
