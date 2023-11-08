package org.catncode.leaders_backend.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catncode.leaders_backend.account.entity.Account;
import org.catncode.leaders_backend.employee.dto.EmployeeGrade;
import org.catncode.leaders_backend.task.entity.Task;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employee", indexes = {
        @Index(columnList = "account_id")
})
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
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeGrade grade;

    @NonNull
    @Column(nullable = false)
    private String locationAddress;

    @OneToMany(mappedBy = "employee")
    private Set<Task> tasks = new HashSet<>();
}
