package az.orient.bank.dto.response;

import lombok.Data;

@Data
public class RespUser {

    private String username;
    private String fullname;
    private RespToKen respToKen;
}
