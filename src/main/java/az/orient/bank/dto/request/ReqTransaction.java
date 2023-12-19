package az.orient.bank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqTransaction {


    private Long dtAccountId;
    private String crAccount;//hansi hesaba pul getmeyi
    private Double amount;//mebleg
    private String currency;//valyutasi
    private ReqToken reqToken;

}
