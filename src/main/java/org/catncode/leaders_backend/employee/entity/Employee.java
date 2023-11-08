package org.catncode.leaders_backend.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catncode.leaders_backend.account.entity.Account;
import org.catncode.leaders_backend.employee.dto.EmployeeGrade;

@Entity
@Table(name = "employee")
@NoArgsConstructor
@RequiredArgsConstructor
@Setter
@Getter
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Include
    private Integer id;

    @NonNull
    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeGrade grade;

    @NonNull
    @Column(name = "location_address", nullable = false)
    private String locationAddress;
}
