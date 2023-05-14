package github.denisspec989.retailexpertdemoservice.service;

import github.denisspec989.retailexpertdemoservice.entity.Product;
import github.denisspec989.retailexpertdemoservice.model.product.ProductSalesMonthlyDto;
import github.denisspec989.retailexpertdemoservice.model.product.ProductsParsingDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {
    Product getProductById(UUID id);
    void saveAllParsedProducts(List<ProductsParsingDto> productsParsingDtos);
    Optional<Product> getProductByCode(Long code);
    List<ProductSalesMonthlyDto> getProductsAnalytics();
}
