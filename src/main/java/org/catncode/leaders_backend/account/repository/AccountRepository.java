package org.catncode.leaders_backend.account.repository;

import org.catncode.leaders_backend.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByLogin(String login);

    boolean existsByLogin(String login);
}
