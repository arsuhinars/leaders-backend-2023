package org.catncode.leaders_backend.account.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catncode.leaders_backend.account.dto.AccountRole;
import org.catncode.leaders_backend.employee.entity.Employee;

import java.util.Objects;

@Entity
@Table(name = "account", indexes = {
        @Index(columnList = "login", unique = true),
})
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true)
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Include
    private Integer id;

    @NonNull
    @Column(nullable = false, unique = true)
    private String login;

    @NonNull
    @Column(nullable = false)
    private String passwordHash;

    @NonNull
    @Column(nullable = false)
    private String fullName;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountRole role;

    @OneToOne(mappedBy = "account", cascade = { CascadeType.REMOVE })
    private Employee employee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return Objects.equals(id, account.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
