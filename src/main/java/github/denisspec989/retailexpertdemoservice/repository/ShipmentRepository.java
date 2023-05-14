package github.denisspec989.retailexpertdemoservice.repository;

import github.denisspec989.retailexpertdemoservice.entity.Shipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.UUID;

public interface ShipmentRepository extends JpaRepository<Shipment, UUID>, QuerydslPredicateExecutor<Shipment> {
}
