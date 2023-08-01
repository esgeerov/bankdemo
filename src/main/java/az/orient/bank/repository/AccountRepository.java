package az.orient.bank.repository;

import az.orient.bank.entity.Account;
import az.orient.bank.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {
    List<Account> findAllByCustomersAndActive(Customers customers,Integer active);
}
