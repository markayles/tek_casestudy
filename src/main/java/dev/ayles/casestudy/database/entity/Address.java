package dev.ayles.casestudy.database.entity;

import com.fasterxml.jackson.annotation.JsonView;
import dev.ayles.casestudy.JsonViews;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses")
public class Address {
    @JsonView(JsonViews.CustomerAddressesAJAX.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @JsonView(JsonViews.CustomerAddressesAJAX.class)
    @Column(name = "street")
    private String street;

    @JsonView(JsonViews.CustomerAddressesAJAX.class)
    @Column(name = "city")
    private String city;

    @JsonView(JsonViews.CustomerAddressesAJAX.class)
    @Column(name = "state")
    private String state;

    @JsonView(JsonViews.CustomerAddressesAJAX.class)
    @Column(name = "zip")
    private String zip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
