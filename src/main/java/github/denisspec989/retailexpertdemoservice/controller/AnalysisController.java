package github.denisspec989.retailexpertdemoservice.controller;

import github.denisspec989.retailexpertdemoservice.model.price.PriceShortDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ShipmentDto;
import github.denisspec989.retailexpertdemoservice.service.ShipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/analysis")
@RequiredArgsConstructor
public class AnalysisController {
    private final ShipmentService shipmentService;
    @GetMapping("/shipment/day")
    public ResponseEntity<Page<ShipmentDto>> getDailyShipmentsPaginatedAndFiltered(Pageable pageable, @RequestParam(required = false) List<String> groceryChainNames,@RequestParam(required = false) List<Long> productCodes,String date){
        return new ResponseEntity<>(shipmentService.getShipmentsFilteredAndPaginated(pageable,groceryChainNames,productCodes,date), HttpStatus.valueOf(200));
    }
}
