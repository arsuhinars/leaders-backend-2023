package org.catncode.leaders_backend.account.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catncode.leaders_backend.account.dto.AccountRole;

@Entity
@Table(name = "account")
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Include
    private Integer id;

    @NonNull
    @Column(nullable = false)
    private String login;

    @NonNull
    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @NonNull
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountRole role;
}
