package az.orient.bank.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.*;

@Data
@Entity
@Table(name = "transaction")
@DynamicInsert
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "dt_account_id")
    private Account dtAccount;
    private String crAccount;
    private Double amount;
    private String currency;
    private Date payDate;
    @CreationTimestamp
    private Date paydataDate;
    @ColumnDefault(value = "1")
    private Integer active;
}
