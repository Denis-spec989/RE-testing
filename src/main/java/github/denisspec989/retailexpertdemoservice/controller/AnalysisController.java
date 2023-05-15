package github.denisspec989.retailexpertdemoservice.controller;

import github.denisspec989.retailexpertdemoservice.entity.PromotionSign;
import github.denisspec989.retailexpertdemoservice.model.product.ProductSalesMonthlyDto;
import github.denisspec989.retailexpertdemoservice.model.shipment.ShipmentDayDto;
import github.denisspec989.retailexpertdemoservice.service.ProductService;
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
    private final ProductService productService;
    @GetMapping("/shipment/day")
    public ResponseEntity<Page<ShipmentDayDto>> getDailyShipmentsPaginatedAndFiltered(Pageable pageable,
                                                                                      @RequestParam(required = false) List<String> groceryChainNames,
                                                                                      @RequestParam(required = false) List<Long> productCodes,
                                                                                      @RequestParam(required = false) String date,
                                                                                      @RequestParam(required = false)PromotionSign promotionSign){
        return new ResponseEntity<>(shipmentService.getShipmentsFilteredAndPaginated(pageable,groceryChainNames,productCodes,date,promotionSign), HttpStatus.valueOf(200));
    }
    @GetMapping("/shipment/monthly")
    public ResponseEntity<List<ProductSalesMonthlyDto>> getShipmentsGrouped(@RequestParam("year")Integer year,@RequestParam("month") Integer month){
        return new ResponseEntity<>(productService.getProductsAnalytics(year,month),HttpStatus.OK);
    }
}
