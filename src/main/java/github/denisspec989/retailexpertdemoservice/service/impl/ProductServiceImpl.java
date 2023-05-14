package github.denisspec989.retailexpertdemoservice.service.impl;

import github.denisspec989.retailexpertdemoservice.entity.Product;
import github.denisspec989.retailexpertdemoservice.entity.ProductCategory;
import github.denisspec989.retailexpertdemoservice.model.product.ProductsParsingDto;
import github.denisspec989.retailexpertdemoservice.repository.ProductRepository;
import github.denisspec989.retailexpertdemoservice.service.ProductCategoryService;
import github.denisspec989.retailexpertdemoservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    @Override
    @Transactional
    public Product getProductById(UUID id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public void saveAllParsedProducts(List<ProductsParsingDto> productsParsingDtos) {
        productRepository.saveAll(
                productsParsingDtos.stream().map(new Function<ProductsParsingDto, Product>() {
                    @Override
                    public Product apply(ProductsParsingDto productsParsingDto) {
                        ProductCategory productCategory = productCategoryService.getProductCategoryOrCreateNew(productsParsingDto.getCategory());
                        Optional<Product> productOptional = productRepository.findByCodeAndNameAndCategory(productsParsingDto.getNumber(),productsParsingDto.getName(),productCategory);
                        if(productOptional.isEmpty()) {
                            Product product = new Product();
                            product.setCode(productsParsingDto.getNumber());
                            product.setName(productsParsingDto.getName());
                            product.setCategory(productCategory);
                            return  product;
                        } else {
                            return productOptional.get();
                        }
                    }
                }).collect(Collectors.toList())
        );
    }

    @Override
    public Optional<Product> getProductByCode(Long code) {
        return productRepository.findByCode(code);
    }

}
