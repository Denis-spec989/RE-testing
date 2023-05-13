package github.denisspec989.retailexpertdemoservice.service;

import github.denisspec989.retailexpertdemoservice.entity.Product;

import java.util.UUID;

public interface ProductService {
    Product getProductById(UUID id);
}
