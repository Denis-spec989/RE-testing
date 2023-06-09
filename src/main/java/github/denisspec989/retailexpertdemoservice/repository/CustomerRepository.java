package github.denisspec989.retailexpertdemoservice.repository;

import github.denisspec989.retailexpertdemoservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {
    Optional<Customer> findByGroceryChainName(String groceryChainName);
}
