package github.denisspec989.retailexpertdemoservice.service.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import github.denisspec989.retailexpertdemoservice.entity.PromotionSign;
import github.denisspec989.retailexpertdemoservice.entity.QShipment;
import github.denisspec989.retailexpertdemoservice.model.shipment.ShipmentDayDto;
import github.denisspec989.retailexpertdemoservice.repository.ShipmentRepository;
import github.denisspec989.retailexpertdemoservice.service.SerializableService;
import github.denisspec989.retailexpertdemoservice.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
@Service
@RequiredArgsConstructor
public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;
    private final SerializableService serializableService;
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    @SneakyThrows
    @Override
    @Transactional
    public Page<ShipmentDayDto> getShipmentsFilteredAndPaginated(Pageable pageable, List<String> groceryChainNames, List<Long> productCodes, String date, PromotionSign promotionSign) {
        BooleanBuilder predicate = new BooleanBuilder();
        if(!groceryChainNames.isEmpty()){
            List<Predicate> predicateTypes = new ArrayList<>();
            groceryChainNames.forEach(name->{
                predicateTypes.add(QShipment.shipment.customer.groceryChainName.eq(name));
            });
            predicate.andAnyOf(predicateTypes.toArray(new Predicate[0]));
        }
        if(!productCodes.isEmpty()){
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
        return shipmentRepository.findAll(predicate,pageable).map(serializableService::fromShipmentToShipmentDto);
    }
}
