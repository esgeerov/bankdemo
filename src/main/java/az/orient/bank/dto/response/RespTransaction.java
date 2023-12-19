package az.orient.bank.dto.response;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class RespTransaction {
    private Long trId;
    private String custname;
    private String dtAccount;
    private String crAccount;//hansi hesaba pul getmeyi
    private Double amount;//mebleg
    private String currency;//valyutasi
    private Date trDate;
}
