package az.orient.bank.dto.request;

import lombok.Data;

@Data
public class ReqToken {
    private Long userId;
    private String token;
}
