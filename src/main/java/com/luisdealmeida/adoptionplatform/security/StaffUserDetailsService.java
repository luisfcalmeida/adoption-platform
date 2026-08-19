package com.luisdealmeida.adoptionplatform.security;

import com.luisdealmeida.adoptionplatform.entity.StaffAccount;
import com.luisdealmeida.adoptionplatform.repository.StaffAccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class StaffUserDetailsService implements UserDetailsService {

    private final StaffAccountRepository staffAccountRepository;

    public StaffUserDetailsService(StaffAccountRepository staffAccountRepository) {
        this.staffAccountRepository = staffAccountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        StaffAccount staffAccount = staffAccountRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found..."));

        return User.builder()
                .username(staffAccount.getEmail())
                .password(staffAccount.getPasswordHash())
                .roles("STAFF")
                .disabled(!staffAccount.isActive())
                .build();
    }
}
