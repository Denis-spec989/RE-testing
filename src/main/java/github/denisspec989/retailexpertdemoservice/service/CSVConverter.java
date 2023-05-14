package github.denisspec989.retailexpertdemoservice.service;

import github.denisspec989.retailexpertdemoservice.model.customer.CustomersParsingDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceParsingDto;
import github.denisspec989.retailexpertdemoservice.model.product.ProductsParsingDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ActualsParsingDto;
import github.denisspec989.retailexpertdemoservice.model.common.CSV;

import java.util.List;

public interface CSVConverter {
    List<ActualsParsingDto> convertActualsList(CSV csv);
    List<ProductsParsingDto> convertProductsList(CSV csv);
    List<CustomersParsingDto> convertCustomersList(CSV csv);
    List<PriceParsingDto> convertPricesList(CSV csv);
}
