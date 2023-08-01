package az.orient.bank.dto.response;

import lombok.Builder;

@Builder
public class RespAccount {
    private Long accountId;
    private String name;
    private String acccountNo;
    private String iban;
    private String currency;
    private Integer branchNo;
    private RespCustomer respCustomer;

}
