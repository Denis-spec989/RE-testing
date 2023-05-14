package github.denisspec989.retailexpertdemoservice.service.impl;

import com.querydsl.core.BooleanBuilder;
import github.denisspec989.retailexpertdemoservice.entity.*;
import github.denisspec989.retailexpertdemoservice.model.product.ProductSalesMonthlyDto;
import github.denisspec989.retailexpertdemoservice.model.product.ProductsParsingDto;
import github.denisspec989.retailexpertdemoservice.repository.ProductRepository;
import github.denisspec989.retailexpertdemoservice.repository.ShipmentRepository;
import github.denisspec989.retailexpertdemoservice.service.CustomerService;
import github.denisspec989.retailexpertdemoservice.service.ProductCategoryService;
import github.denisspec989.retailexpertdemoservice.service.ProductService;
import github.denisspec989.retailexpertdemoservice.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductCategoryService productCategoryService;
    private final CustomerService customerService;
    private final ShipmentRepository shipmentRepository;
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
    @Transactional
    public Optional<Product> getProductByCode(Long code) {
        return productRepository.findByCode(code);
    }

    @Override
    @Transactional
    public List<ProductSalesMonthlyDto> getProductsAnalytics() {
        List<ProductSalesMonthlyDto> productAnalyticsDtos = new ArrayList<>();
        List<Customer> customers =  customerService.findAll();
        for(Customer customer:customers) {
            List<Shipment> shipments = shipmentRepository.findAll(Sort.by("date").ascending());
            Shipment oldestShipment = shipments.get(0);
            Date oldestDate = oldestShipment.getDate();
            Shipment newlyShipment = shipments.get(shipments.size()-1);
            Date newlyDate = newlyShipment.getDate();
            List<ProductCategory> productCategories = productCategoryService.findAll();
            for(ProductCategory productCategory:productCategories){
                LocalDateTime newlyLocalDateTime = newlyDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                LocalDateTime oldestLocalDateTime = oldestDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
                //for(LocalDateTime oldestLocalDateTime = oldestDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();oldestLocalDateTime.isBefore(newlyLocalDateTime);oldestLocalDateTime.plusMonths(1)){
                    BooleanBuilder predicate = new BooleanBuilder();
                    predicate.and(QShipment.shipment.customer.eq(customer));
                    predicate.and(QShipment.shipment.product.category.eq(productCategory));
                    predicate.and(QShipment.shipment.date.year().eq(oldestLocalDateTime.getYear()));
                    predicate.and(QShipment.shipment.date.month().eq(oldestLocalDateTime.getMonthValue()));
                    Iterable<Shipment> monthlyShipmentsByPredicate = shipmentRepository.findAll(predicate);
                    System.out.println("#####CHECKING");
                    Long unitsSoldByRegularPrice=0L;
                    Long unitsSoldByPromoPrice=0L;
                    Long totalCount=0L;
                    for (Shipment shipment:monthlyShipmentsByPredicate){
                        if (shipment.getPromotionSign().equals(PromotionSign.PROMO)){
                            unitsSoldByPromoPrice= unitsSoldByPromoPrice + shipment.getUnits();
                            totalCount= totalCount+shipment.getUnits();
                        }else {
                            unitsSoldByRegularPrice= unitsSoldByRegularPrice+shipment.getUnits();
                            totalCount=totalCount+shipment.getUnits();
                        }
                        Double promoPercent = Double.parseDouble(String.valueOf(unitsSoldByPromoPrice))/Double.parseDouble(String.valueOf(totalCount))*100;
                        productAnalyticsDtos.add(new ProductSalesMonthlyDto(customer.getGroceryChainName(),productCategory,oldestLocalDateTime.getMonth(),unitsSoldByRegularPrice,unitsSoldByPromoPrice,promoPercent));
                    }
                //}
            }
        }
        return productAnalyticsDtos;
    }


}
