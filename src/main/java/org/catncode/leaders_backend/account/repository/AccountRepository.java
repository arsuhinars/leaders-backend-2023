package org.catncode.leaders_backend.account.repository;

import org.catncode.leaders_backend.account.entity.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Integer>, PagingAndSortingRepository<Account, Integer> {
    Optional<Account> findByLogin(String login);
}
