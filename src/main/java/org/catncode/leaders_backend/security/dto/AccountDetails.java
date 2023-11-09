package org.catncode.leaders_backend.security.dto;

import lombok.Getter;
import org.catncode.leaders_backend.account.entity.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AccountDetails implements UserDetails {
    @Getter
    private final Account account;
    private final Collection<AccountRoleAuthority> authorities;

    public AccountDetails(Account account) {
        this.account = account;
        authorities = Collections.singleton(new AccountRoleAuthority(account.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return account.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return account.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
