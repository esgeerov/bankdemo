package az.orient.bank.security;

import az.orient.bank.entity.Users;
import az.orient.bank.enums.EnumAviableStatus;
import az.orient.bank.repository.UsersReposirtory;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {
    private final UsersReposirtory usersReposirtory;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=usersReposirtory.findUsersByEmailAndActive(username, EnumAviableStatus.ACTIVE.value);
        if (user==null){
            throw   new UsernameNotFoundException("Not found "+ username);
        }
        MyUserDetails myUserDetails=new MyUserDetails(user);

        return myUserDetails;
    }
}
