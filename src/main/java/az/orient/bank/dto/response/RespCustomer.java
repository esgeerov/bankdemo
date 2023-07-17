package az.orient.bank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RespCustomer {
    private Long id;
    private String name;
    private String surname;
    private String address;
    private Date dob;
    private String phone;
    private String  pin;
    private String seria;
}
