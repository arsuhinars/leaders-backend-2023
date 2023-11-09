package org.catncode.leaders_backend.account.controller;

import org.catncode.leaders_backend.account.dto.AccountDto;
import org.catncode.leaders_backend.account.dto.CreateAccountDto;
import org.catncode.leaders_backend.account.dto.UpdateAccountDto;
import org.catncode.leaders_backend.account.dto.UpdateAccountPasswordDto;
import org.catncode.leaders_backend.account.entity.Account;
import org.catncode.leaders_backend.account.service.AccountServiceImpl;
import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;

public class AccountControllerImpl implements AccountController{

    private final AccountServiceImpl accountServiceImpl;
    AccountDto accountDto;

    public AccountControllerImpl(AccountServiceImpl accountServiceImpl) {
        this.accountServiceImpl = accountServiceImpl;
    }

    @Override
    public Account createAccount(CreateAccountDto schema) throws AppException {
        return accountServiceImpl.create(schema);
    }

    @Override
    public Account getAccountById(Integer id) throws AppException {
        return accountServiceImpl.getById(id);
    }

    @Override
    public Account updateAccountById(UpdateAccountDto schema, Integer id) throws AppException {
        return accountServiceImpl.updateById(id, schema);
    }

    @Override
    public void deleteAccountById(Integer id) throws AppException {
        accountServiceImpl.deleteById(id);
    }

    @Override
    public Account getAccountByLogin(String login) throws AppException {
        return accountServiceImpl.getByLogin(login);
    }

    @Override
    public Account updateAccountByLogin(UpdateAccountDto schema, String login) throws AppException {
        return accountServiceImpl.updateByLogin(login, schema);
    }

    @Override
    public void deleteAccountByLogin(String login) throws AppException {
        accountServiceImpl.deleteByLogin(login);
    }

    @Override
    public void updateAccountPasswordById(UpdateAccountPasswordDto schema, Integer id) throws AppException {
        accountServiceImpl.updatePasswordById(id, schema);
    }

    @Override
    public Pagination<AccountDto> getAllAccounts(Integer page, Integer size) {
        return accountServiceImpl.getAll();
    }

    @Override
    public AccountDto getCurrentAccount() {
        return accountDto;
    }
}
