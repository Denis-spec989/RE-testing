package github.denisspec989.retailexpertdemoservice.controller;

import github.denisspec989.retailexpertdemoservice.model.customer.CustomersParsingDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceParsingDto;
import github.denisspec989.retailexpertdemoservice.model.product.ProductsParsingDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ActualsParsingDto;
import github.denisspec989.retailexpertdemoservice.model.common.CSV;
import github.denisspec989.retailexpertdemoservice.service.CSVConverter;
import github.denisspec989.retailexpertdemoservice.service.CustomerService;
import github.denisspec989.retailexpertdemoservice.service.SerializableCustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final CustomerService customerService;
    private final SerializableCustomerService serializableCustomerService;
    private final CSVConverter csvConverter;
    @PostMapping(value = "/load/csv/actuals", consumes = "multipart/form-data")
    public ResponseEntity<List<ActualsParsingDto>> loadCsvActuals(@RequestParam("file") MultipartFile file) throws IOException {
        List<ActualsParsingDto> actuals =  csvConverter.convertActualsList(new CSV(file));
        return new ResponseEntity<>(actuals, HttpStatus.CREATED);
    }
    @PostMapping(value = "/load/csv/customers", consumes = "multipart/form-data")
    public void loadCsvCustomers(@RequestParam("file") MultipartFile file) {
        csvConverter.convertCustomersList(new CSV(file)).forEach(new Consumer<CustomersParsingDto>() {
            @Override
            public void accept(CustomersParsingDto customersParsingDto) {
                customerService.saveCustomer(serializableCustomerService.fromCustomerParsingDtoToCustomer(customersParsingDto));
            }
        });
    }
    @PostMapping(value = "/load/csv/prices", consumes = "multipart/form-data")
    public ResponseEntity<List<PriceParsingDto>> loadCsvPrices(@RequestParam("file") MultipartFile file) throws IOException {
        List<PriceParsingDto> prices =  csvConverter.convertPricesList(new CSV(file));
        return new ResponseEntity<>(prices, HttpStatus.CREATED);
    }
    @PostMapping(value = "/load/csv/products", consumes = "multipart/form-data")
    public ResponseEntity<List<ProductsParsingDto>> loadCsvProducts(@RequestParam("file") MultipartFile file) throws IOException {
        List<ProductsParsingDto> products =  csvConverter.convertProductsList(new CSV(file));
        return new ResponseEntity<>(products, HttpStatus.CREATED);
    }
}
