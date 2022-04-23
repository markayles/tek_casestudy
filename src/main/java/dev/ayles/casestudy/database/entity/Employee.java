package dev.ayles.casestudy.database.entity;

import com.fasterxml.jackson.annotation.JsonView;
import dev.ayles.casestudy.JsonViews;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @JsonView(JsonViews.WorkOrderNoteAJAX.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @ToString.Exclude
    @Column(name = "password")
    private String password;

    @JsonView(JsonViews.WorkOrderNoteAJAX.class)
    @Column(name = "first_name")
    private String firstName;

    @JsonView(JsonViews.WorkOrderNoteAJAX.class)
    @Column(name = "last_name")
    private String lastName;

    @JsonView(JsonViews.WorkOrderNoteAJAX.class)
    @Column(name = "title")
    private String title;

    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime = new Date();

    @ToString.Exclude
    @OneToMany(mappedBy = "employee")
    private List<WorkOrderNote> workOrderNotes;

    @ToString.Exclude
    @ManyToMany(mappedBy = "employees")
    private List<WorkOrder> workOrders = new ArrayList<>();
}
