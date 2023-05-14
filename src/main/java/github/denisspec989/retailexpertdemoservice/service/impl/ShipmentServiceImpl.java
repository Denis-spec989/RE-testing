package github.denisspec989.retailexpertdemoservice.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import github.denisspec989.retailexpertdemoservice.entity.*;
import github.denisspec989.retailexpertdemoservice.model.shipment.ActualsParsingDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ShipmentDayDto;
import github.denisspec989.retailexpertdemoservice.repository.ShipmentRepository;
import github.denisspec989.retailexpertdemoservice.service.*;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final ProductService productService;
    private final PriceService priceService;
    private final CustomerService customerService;
    private final SerializablePriceService serializablePriceService;
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    @SneakyThrows
    @Override
    @Transactional
    public Page<ShipmentDayDto> getShipmentsFilteredAndPaginated(Pageable pageable, List<String> groceryChainNames, List<Long> productCodes, String date, PromotionSign promotionSign) {
        BooleanBuilder predicate = new BooleanBuilder();
        if(groceryChainNames!=null){
            List<Predicate> predicateTypes = new ArrayList<>();
            groceryChainNames.forEach(name->{
                predicateTypes.add(QShipment.shipment.customer.groceryChainName.eq(name));
            });
            predicate.andAnyOf(predicateTypes.toArray(new Predicate[0]));
        }
        if(productCodes!=null){
            List<Predicate> predicateTypes = new ArrayList<>();
            productCodes.forEach(code->{
                predicateTypes.add(QShipment.shipment.product.code.eq(code));
            });
            predicate.andAnyOf(predicateTypes.toArray(new Predicate[0]));
        }
        Calendar calendar = Calendar.getInstance();
        if(date==null){
            calendar.setTime(new Date());
            predicate.and(QShipment.shipment.date.year().eq(calendar.get(Calendar.YEAR)));
            predicate.and(QShipment.shipment.date.month().eq(calendar.get(Calendar.MONTH)));
            predicate.and(QShipment.shipment.date.dayOfMonth().eq(calendar.get(Calendar.DAY_OF_MONTH)));
        } else {
            calendar.setTime(simpleDateFormat.parse(date));
            predicate.and(QShipment.shipment.date.year().eq(calendar.get(Calendar.YEAR)));
            predicate.and(QShipment.shipment.date.month().eq(calendar.get(Calendar.MONTH)));
            predicate.and(QShipment.shipment.date.dayOfMonth().eq(calendar.get(Calendar.DAY_OF_MONTH)));
        }
        if(promotionSign!=null){
            predicate.and(QShipment.shipment.promotionSign.eq(promotionSign));
        }
        return shipmentRepository.findAll(predicate,pageable).map(serializablePriceService::fromShipmentToShipmentDto);
    }

    @Override
    @Transactional
    public void saveAllParsedShipments(List<ActualsParsingDto> actuals) {
        shipmentRepository.saveAll(
                actuals.stream().map(new Function<ActualsParsingDto, Shipment>() {
                    @Override
                    public Shipment apply(ActualsParsingDto actualsParsingDto) {
                        Optional<Product> productOptional = productService.getProductByCode(actualsParsingDto.getMaterialCode());
                        Optional<Customer> customerOptional = customerService.getByGroceryChainName(actualsParsingDto.getChain());
                        if(productOptional.isEmpty()||customerOptional.isEmpty()){
                            return null;
                        }
                        Product product = productOptional.get();
                        Customer customer = customerOptional.get();
                        Optional<Address> addressOptional = customer.getAddresses().stream().filter(address->address.getCode().equals(actualsParsingDto.getAddressCode())).findFirst();
                        Optional<Price> priceOptional = priceService.getPriceByCustomerAndProduct(customer,product);
                        if(addressOptional.isEmpty()||priceOptional.isEmpty()){
                            return null;
                        }
                        Address address = addressOptional.get();
                        Price price = priceOptional.get();
                        Calendar calendar = Calendar.getInstance();
                        //year,month,date
                        String[] yearMonthDate = actualsParsingDto.getDate().split("-");
                        calendar.set(Integer.parseInt(yearMonthDate[0]),Integer.parseInt(yearMonthDate[1]),Integer.parseInt(yearMonthDate[2]));
                        Date shipmentDate = calendar.getTime();
                        Optional<Shipment> shipmentOptional = shipmentRepository.
                                findByDateAndSaleValueAndUnitsAndProductAndAddressAndCustomer(shipmentDate,actualsParsingDto.getSalesValue(),actualsParsingDto.getUnits(),product,address,customer);
                        if(shipmentOptional.isEmpty()){
                            Shipment shipment = new Shipment();
                            shipment.setDate(shipmentDate);
                            shipment.setSaleValue(actualsParsingDto.getSalesValue());
                            shipment.setUnits(actualsParsingDto.getUnits());
                            shipment.setPromotionSign(price.getRegularPrice().equals(actualsParsingDto.getSalesValue()/ actualsParsingDto.getUnits())?PromotionSign.REGULAR:PromotionSign.PROMO);
                            shipment.setProduct(product);
                            shipment.setAddress(address);
                            shipment.setCustomer(customer);
                            return shipment;
                        }else {
                            return shipmentOptional.get();
                        }
                    }
                }).filter(entity->entity!=null).collect(Collectors.toList())
        );
    }
}
