package az.orient.bank.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@EnableWebSecurity
@Component
@RequiredArgsConstructor
public class SpringSecurity  extends WebSecurityConfigurerAdapter {
    private final MyUserDetailsService myUserDetailsService;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("samir").password("samir123").roles("ADMIN").and()
//                .withUser("ibrahim").password("ibos123").roles("USER");
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeHttpRequests().antMatchers("/user/**").permitAll()
                .antMatchers("/customer/**").hasAnyRole("ADMIN","USER")
                .antMatchers("/account/**","/transaction/**").hasRole("ADMIN").anyRequest().authenticated().and().httpBasic();
    }
}
