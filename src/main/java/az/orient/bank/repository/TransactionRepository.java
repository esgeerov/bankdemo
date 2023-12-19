package az.orient.bank.repository;

import az.orient.bank.entity.Account;
import az.orient.bank.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
    List<Transaction> findAllByActiveAndDtAccount(Integer active, Account account);
}
