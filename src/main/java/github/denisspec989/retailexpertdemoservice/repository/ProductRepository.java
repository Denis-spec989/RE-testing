package github.denisspec989.retailexpertdemoservice.repository;

import github.denisspec989.retailexpertdemoservice.entity.Customer;
import github.denisspec989.retailexpertdemoservice.entity.Product;
import github.denisspec989.retailexpertdemoservice.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {
    Optional<Product> findByCodeAndNameAndCategory(Long code, String name, ProductCategory productCategory);
}
