package github.denisspec989.retailexpertdemoservice.service.impl;

import github.denisspec989.retailexpertdemoservice.entity.ProductCategory;
import github.denisspec989.retailexpertdemoservice.model.common.ProductCategoryDto;
import github.denisspec989.retailexpertdemoservice.repository.ProductCategoryRepository;
import github.denisspec989.retailexpertdemoservice.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    @Transactional
    public ProductCategory getProductCategoryOrCreateNew(ProductCategoryDto productCategoryDto) {
        return productCategoryRepository.findByCodeAndName(productCategoryDto.getCode(),productCategoryDto.getName()).orElseGet(new Supplier<ProductCategory>() {
            @Override
            public ProductCategory get() {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setCode(productCategoryDto.getCode());
                productCategory.setName(productCategoryDto.getName());
                return productCategoryRepository.save(productCategory);
            }
        });
    }

    @Override
    @Transactional
    public List<ProductCategory> findAll() {
        return productCategoryRepository.findAll();
    }
}
