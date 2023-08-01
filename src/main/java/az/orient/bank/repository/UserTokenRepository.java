package az.orient.bank.repository;

import az.orient.bank.entity.User;
import az.orient.bank.entity.UserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTokenRepository extends JpaRepository<UserToken,Long> {
    UserToken findUserTokenByUserAndToken(User user,String token);

}
