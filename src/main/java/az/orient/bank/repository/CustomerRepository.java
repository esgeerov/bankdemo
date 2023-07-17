package az.orient.bank.repository;

import az.orient.bank.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CustomerRepository extends JpaRepository<Customers,Long>{

    List<Customers> findAllByActive(Integer active);

    Customers findByIdAndActive(Long customerId, Integer value);

}









