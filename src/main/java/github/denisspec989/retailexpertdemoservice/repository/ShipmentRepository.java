package github.denisspec989.retailexpertdemoservice.repository;

import github.denisspec989.retailexpertdemoservice.entity.Address;
import github.denisspec989.retailexpertdemoservice.entity.Customer;
import github.denisspec989.retailexpertdemoservice.entity.Product;
import github.denisspec989.retailexpertdemoservice.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID>, QuerydslPredicateExecutor<Shipment> {
    Optional<Shipment> findByDateAndSaleValueAndUnitsAndProductAndAddressAndCustomer(Date date, Double saleValue, Long units, Product product, Address address, Customer customer);

}
