package org.catncode.leaders_backend.account.service;

import org.catncode.leaders_backend.account.dto.CreateAccountDto;
import org.catncode.leaders_backend.account.dto.UpdateAccountDto;
import org.catncode.leaders_backend.account.dto.UpdateAccountPasswordDto;
import org.catncode.leaders_backend.account.entity.Account;
import org.catncode.leaders_backend.core.exception.AppException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AccountService {
    Account create(CreateAccountDto dto) throws AppException;

    Page<Account> getAll(Pageable pageable) throws AppException;

    Account getById(Integer id) throws AppException;

    Account getByLogin(String login) throws AppException;

    Account updateById(Integer id, UpdateAccountDto dto) throws AppException;

    Account updateByLogin(String login, UpdateAccountDto dto) throws AppException;

    Account updatePasswordById(Integer id, UpdateAccountPasswordDto dto) throws AppException;

    void deleteById(Integer id) throws AppException;

    void deleteByLogin(String login) throws AppException;
}
