package az.orient.bank.repository;

import az.orient.bank.entity.User;
import az.orient.bank.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersReposirtory extends JpaRepository<Users,Long> {

    Users findUsersByEmailAndActive(String email,Integer active);
}
