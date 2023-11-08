package org.catncode.leaders_backend.security.service;

import org.catncode.leaders_backend.account.repository.AccountRepository;
import org.catncode.leaders_backend.security.dto.AccountDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccountDetailService implements UserDetailsService {
    private final AccountRepository repository;

    public AccountDetailService(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var account = repository.findByLogin(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid credentials were provided"));
        return new AccountDetails(account);
    }
}
