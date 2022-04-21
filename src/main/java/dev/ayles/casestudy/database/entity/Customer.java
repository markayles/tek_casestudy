package dev.ayles.casestudy.database.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer")
    private Set<Address> addresses;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer")
    private Set<WorkOrder> workOrders;
}
