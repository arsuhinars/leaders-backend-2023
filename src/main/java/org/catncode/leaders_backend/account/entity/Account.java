package org.catncode.leaders_backend.account.entity;

import org.catncode.leaders_backend.account.dto.AccountRole;

public class Account {
    private Integer id;
    private String login;
    private String passwordHash;
    private String fullName;
    private AccountRole role;
}
