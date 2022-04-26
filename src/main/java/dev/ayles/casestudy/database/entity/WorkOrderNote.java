package dev.ayles.casestudy.database.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import dev.ayles.casestudy.JsonViews;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "work_order_notes")
public class WorkOrderNote {
    @JsonView(JsonViews.WorkOrderNoteAJAX.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonView(JsonViews.WorkOrderNoteAJAX.class)
    private String note;

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_order_id")
    private WorkOrder workOrder;

    @JsonView(JsonViews.WorkOrderNoteAJAX.class)
    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @JsonView(JsonViews.WorkOrderNoteAJAX.class)
    @Column(name = "automated", columnDefinition = "TINYINT")
    private Boolean automated;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "EEE, MMM dd, yyyy HH:mm", timezone="America/New_York")
    @JsonView(JsonViews.WorkOrderNoteAJAX.class)
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate = new Date();
}
