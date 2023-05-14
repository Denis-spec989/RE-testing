package github.denisspec989.retailexpertdemoservice.service.impl;

import github.denisspec989.retailexpertdemoservice.model.common.AddressDto;
import github.denisspec989.retailexpertdemoservice.model.customer.CustomersParsingDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceParsingDto;
import github.denisspec989.retailexpertdemoservice.model.product.ProductsParsingDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ActualsParsingDto;
import github.denisspec989.retailexpertdemoservice.model.common.CSV;
import github.denisspec989.retailexpertdemoservice.service.CSVConverter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVConverterImpl implements CSVConverter {
    @Override
    public List<ActualsParsingDto> convertActualsList(CSV csv) {
        try(
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(csv.getMultipartFile().getInputStream(), StandardCharsets.UTF_8));
                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('\t'))
        ) {
            ArrayList<ActualsParsingDto> actuals = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for(CSVRecord csvRecord: csvRecords){
                ActualsParsingDto actualsParsingDto = new ActualsParsingDto();
                actualsParsingDto.setDate(csvRecord.get("Date").trim());
                actualsParsingDto.setMaterialCode(Long.parseLong(csvRecord.get("Material No").trim()));
                actualsParsingDto.setAddressCode(Long.parseLong(csvRecord.get("CH3 Ship To Code").trim()));
                actualsParsingDto.setChain(csvRecord.get("Chain").trim());
                actualsParsingDto.setUnits(Long.parseLong(csvRecord.get("Volume, units").trim()));
                actualsParsingDto.setSalesValue(Double.parseDouble(csvRecord.get("Actual Sales Value").trim().replace(",",".")));
                actuals.add(actualsParsingDto);
            }
            return actuals;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductsParsingDto> convertProductsList(CSV csv) {
        try(
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(csv.getMultipartFile().getInputStream(), StandardCharsets.UTF_8));
                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('\t'))
        ) {
            ArrayList<ProductsParsingDto> products = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for(CSVRecord csvRecord: csvRecords){
                ProductsParsingDto product = new ProductsParsingDto();
                product.setNumber(Long.parseLong(csvRecord.get("Material_No").trim()));
                product.setName(csvRecord.get("Material_Desc_RUS").trim());
                product.setCategoryCode(Long.parseLong(csvRecord.get("L3_Product_Category_Code").trim()));
                product.setCategoryName(csvRecord.get("L3_Product_Category_Name").trim());
                products.add(product);
            }
            return products;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CustomersParsingDto> convertCustomersList(CSV csv) {
        try(
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(csv.getMultipartFile().getInputStream(), StandardCharsets.UTF_8));
                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('\t'))
        ) {
            ArrayList<CustomersParsingDto> customers = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for(CSVRecord csvRecord: csvRecords){
                CustomersParsingDto customer = new CustomersParsingDto();
                AddressDto addressDto = new AddressDto();
                addressDto.setCode(Long.parseLong(csvRecord.get("CH3 Ship To Code").trim()));
                addressDto.setValue(csvRecord.get("CH3 Ship To Name").trim());
                customer.setAddress(addressDto);
                customer.setGroceryChainName(csvRecord.get("Chain_name").trim());
                customers.add(customer);
            }
            return customers;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public List<PriceParsingDto> convertPricesList(CSV csv) {
        try(
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(csv.getMultipartFile().getInputStream(), StandardCharsets.UTF_8));
                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('\t'))
        ) {
            ArrayList<PriceParsingDto> prices = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for(CSVRecord csvRecord: csvRecords){
                PriceParsingDto price = new PriceParsingDto();
                price.setChainName(csvRecord.get("Chain_name").trim());
                price.setMaterialCode(Long.parseLong(csvRecord.get("Material No").trim()));
                price.setRegularPrice(Double.parseDouble(csvRecord.get("Regular price per unit").trim().replace(",",".")));
                prices.add(price);
            }
            return prices;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
