package github.denisspec989.retailexpertdemoservice.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Shipment {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private Date date; // Date
    private Double saleValue; // Actual Sales Value
    private Long units; // Volume, units
    @Enumerated(value = EnumType.STRING)
    private PromotionSign promotionSign;
    @OneToOne
    private Product product;
    @OneToOne
    private Address address;
    @OneToOne
    private Customer customer;


}
