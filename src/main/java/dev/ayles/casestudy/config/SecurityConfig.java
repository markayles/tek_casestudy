package dev.ayles.casestudy.config;

import dev.ayles.casestudy.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/error/**", "/login/**", "/index").permitAll()
//                .antMatchers("/admin/**", "/cart/**", "/user/**").authenticated()
                .antMatchers("/workorder/**").hasAuthority("EMPLOYEE")
                .antMatchers("/customer/getAddressesForCustomer/**").hasAuthority("EMPLOYEE")
                .antMatchers("/customer/**").hasAuthority("MANAGER")
                .antMatchers("/employee/**").hasAuthority("ADMIN")
                .and()
                .formLogin()
                // this is the URL of the login page
                .loginPage("/login/login")
                .failureUrl("/login/login?error=true")
                // this is the URL where the login page will submit
                .loginProcessingUrl("/login/loginSubmit")
                .defaultSuccessUrl("/index")
                .and()
                .logout()
                .invalidateHttpSession(true)
                // this is the URL to log the user out
                .logoutUrl("/login/logout")
                // the URL that the user goes to after they logout
                .logoutSuccessUrl("/index")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error/403");
    }

    @Bean
    public DaoAuthenticationProvider getAuthenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(getPasswordEncoder());
        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
        auth.authenticationProvider(getAuthenticationProvider());
    }

    @Bean(name="passwordEncoder")
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
