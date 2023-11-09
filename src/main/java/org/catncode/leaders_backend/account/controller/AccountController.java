package org.catncode.leaders_backend.account.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.catncode.leaders_backend.account.dto.AccountDto;
import org.catncode.leaders_backend.account.dto.CreateAccountDto;
import org.catncode.leaders_backend.account.dto.UpdateAccountDto;
import org.catncode.leaders_backend.account.dto.UpdateAccountPasswordDto;
import org.catncode.leaders_backend.core.dto.Pagination;
import org.catncode.leaders_backend.core.exception.AppException;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/accounts")
public interface AccountController {
    @PostMapping
    AccountDto createAccount(@Valid @RequestBody CreateAccountDto schema) throws AppException;

    @GetMapping("/{id}")
    AccountDto getAccountById(@PathVariable @NotNull @Min(1) Integer id) throws AppException;

    @PutMapping("/{id}")
    AccountDto updateAccountById(
            @Valid @RequestBody UpdateAccountDto schema, @PathVariable @NotNull @Min(1) Integer id
    ) throws AppException;

    @DeleteMapping("/{id}")
    void deleteAccountById(@PathVariable @NotNull @Min(1) Integer id) throws AppException;

    @GetMapping("/login/{login}")
    AccountDto getAccountByLogin(@PathVariable @NotEmpty String login) throws AppException;

    @PutMapping("/login/{login}")
    AccountDto updateAccountByLogin(
            @Valid @RequestBody UpdateAccountDto schema, @PathVariable @NotEmpty String login
    ) throws AppException;

    @DeleteMapping("/login/{login}")
    void deleteAccountByLogin(
            @PathVariable @NotEmpty String login
    ) throws AppException;

    @PutMapping("/{id}/password")
    void updateAccountPasswordById(
            @Valid @RequestBody UpdateAccountPasswordDto schema, @PathVariable @NotNull @Min(1) Integer id
    ) throws AppException;

    @GetMapping("/all")
    Pagination<AccountDto> getAllAccounts(
            @RequestParam(defaultValue = "0") @Min(0) Integer page,
            @RequestParam(defaultValue = "10") @Min(1) Integer size
    );

    @GetMapping("/current")
    AccountDto getCurrentAccount(Authentication authentication);
}
