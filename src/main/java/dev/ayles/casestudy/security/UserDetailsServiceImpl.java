package dev.ayles.casestudy.security;

import dev.ayles.casestudy.database.entity.Employee;
import dev.ayles.casestudy.database.entity.EmployeeRole;
import dev.ayles.casestudy.database.repository.EmployeeRepository;
import dev.ayles.casestudy.database.repository.EmployeeRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeRoleRepository employeeRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employee employee = employeeRepository.findByUsername(username);

        if (employee == null) {
            throw new UsernameNotFoundException("Username '" + username + "' not found in database");
        }

        List<EmployeeRole> employeeRoles = employeeRoleRepository.findByEmployeeId(employee.getId());

        // check the account status
        boolean accountIsEnabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        // setup employee roles
        Collection<? extends GrantedAuthority> springRoles = buildGrantAuthorities(employeeRoles);

        // gets the encrypted password from the database
        String password = employee.getPassword();


        return new org.springframework.security.core.userdetails.User(username, password,
                accountIsEnabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
                springRoles);
    }


    private Collection<? extends GrantedAuthority> buildGrantAuthorities(List<EmployeeRole> employeeRoles) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        for (EmployeeRole role : employeeRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getEmployeeRole()));
        }

        return authorities;
    }

}
