package github.denisspec989.retailexpertdemoservice.service.impl;

import github.denisspec989.retailexpertdemoservice.entity.Product;
import github.denisspec989.retailexpertdemoservice.repository.ProductRepository;
import github.denisspec989.retailexpertdemoservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    @Transactional
    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElseThrow();
    }
}
