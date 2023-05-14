package github.denisspec989.retailexpertdemoservice.repository;

import github.denisspec989.retailexpertdemoservice.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, UUID> {
    Optional<ProductCategory> findByCodeAndName(Long code,String name);
}
