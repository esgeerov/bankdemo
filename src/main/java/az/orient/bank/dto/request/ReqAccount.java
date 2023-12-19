package az.orient.bank.dto.request;

import lombok.*;

@Data
public class ReqAccount {

    private String name;
    private String acccountNo;
    private String iban;
    private String currency;
    private Integer branchNo;
    private Long customerId;
    private ReqToken reqToken;
}
