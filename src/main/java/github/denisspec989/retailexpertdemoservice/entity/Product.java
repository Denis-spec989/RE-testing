package github.denisspec989.retailexpertdemoservice.entity;

import lombok.*;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products")
@Getter
@Setter
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private Long code; // Material_No
    private String name; // Material_Desc_RUS
    @ManyToOne
    @JoinColumn(name = "category_id",nullable = false)
    private ProductCategory category;
    /*
    private Long categoryCode; // Категория  ?
    private String categoryName; // Брэнд ?

     */
}
