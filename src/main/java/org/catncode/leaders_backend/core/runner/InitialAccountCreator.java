package org.catncode.leaders_backend.core.runner;

import org.catncode.leaders_backend.account.dto.AccountRole;
import org.catncode.leaders_backend.account.dto.CreateAccountDto;
import org.catncode.leaders_backend.account.exception.AccountLoginAlreadyExistsException;
import org.catncode.leaders_backend.account.service.AccountService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class InitialAccountCreator implements ApplicationRunner {
    private final AccountService accountService;

    @Value("${leaders_backend.initial_account.login:admin}")
    private String login;

    @Value("${leaders_backend.initial_account.password:qwerty12}")
    private String password;

    @Value("${leaders_backend.initial_account.full_name:Ivanov Ivan Ivanovich}")
    private String fullName;

    public InitialAccountCreator(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            accountService.create(new CreateAccountDto(login, password, fullName, AccountRole.ADMIN));
        } catch (AccountLoginAlreadyExistsException exception) {
            System.out.println("Initial account with login \"" + login + "\" already exists. Skipping.");
        }
    }
}
