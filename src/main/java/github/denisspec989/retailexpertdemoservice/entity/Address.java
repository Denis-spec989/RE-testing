package github.denisspec989.retailexpertdemoservice.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String value; // Ship to name
    private Long code; // Ship to Code
    @ManyToMany(mappedBy = "addresses")
    private List<Customer> customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address)) return false;
        Address address = (Address) o;
        return Objects.equals(getValue(), address.getValue()) && Objects.equals(getCode(), address.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue(), getCode());
    }
}
