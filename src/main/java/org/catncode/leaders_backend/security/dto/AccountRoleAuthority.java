package org.catncode.leaders_backend.security.dto;

import lombok.AllArgsConstructor;
import org.catncode.leaders_backend.account.dto.AccountRole;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class AccountRoleAuthority implements GrantedAuthority {
    private final AccountRole role;

    @Override
    public String getAuthority() {
        return role.toString();
    }

    public static AccountRoleAuthority adminAuthority() {
        return new AccountRoleAuthority(AccountRole.ADMIN);
    }

    public static AccountRoleAuthority managerAuthority() {
        return new AccountRoleAuthority(AccountRole.MANAGER);
    }

    public static AccountRoleAuthority employeeAuthority() {
        return new AccountRoleAuthority(AccountRole.EMPLOYEE);
    }
}
