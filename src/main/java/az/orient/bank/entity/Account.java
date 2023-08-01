package az.orient.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "account")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String accountNo;
    private String iban;
    private String currency;
    private Integer branchNo;
    @ManyToOne
    @JoinColumn(name="customer_id")
    private Customers customers;
    @CreationTimestamp
    private Date dataDate;
    @ColumnDefault(value = "1")
    private Integer active;


}
