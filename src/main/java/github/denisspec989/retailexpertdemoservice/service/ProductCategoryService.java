package github.denisspec989.retailexpertdemoservice.service;

import github.denisspec989.retailexpertdemoservice.entity.ProductCategory;
import github.denisspec989.retailexpertdemoservice.model.common.ProductCategoryDto;

public interface ProductCategoryService {
    ProductCategory getProductCategoryOrCreateNew(ProductCategoryDto productCategoryDto);
}
