package az.orient.bank.dto.request;

import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReqCustomer {
    private Long customerId;
    private String name;
    private String surname;
    private String address;
    private String phone;
    private Date dob;
    private String pin;
    private String seria;
    private ReqToken reqToken;
}
