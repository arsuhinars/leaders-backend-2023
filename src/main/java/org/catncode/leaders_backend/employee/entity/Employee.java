package org.catncode.leaders_backend.employee.entity;

import jakarta.persistence.*;
import lombok.*;
import org.catncode.leaders_backend.account.entity.Account;
import org.catncode.leaders_backend.employee.dto.EmployeeGrade;
import org.catncode.leaders_backend.navigation.entity.Location;
import org.catncode.leaders_backend.task.entity.Task;

import java.util.HashSet;
import java.util.Objects;
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
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Setter(AccessLevel.PRIVATE)
    @ToString.Include
    private Integer id;

    @NonNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @NonNull
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EmployeeGrade grade;

    @NonNull
    @Column(nullable = false)
    private String locationAddress;

    @OneToMany(mappedBy = "employee", cascade = { CascadeType.REMOVE })
    private Set<Task> tasks = new HashSet<>();

    @NonNull
    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE }, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
