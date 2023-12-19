package az.orient.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class RespAccount {
    private Long accountId;
    private String name;
    private String accountNo;
    private String iban;
    private String currency;
    private Integer branchNo;
    private RespCustomer respCustomer;

}
