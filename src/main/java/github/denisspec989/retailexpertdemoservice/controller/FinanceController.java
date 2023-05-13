package github.denisspec989.retailexpertdemoservice.controller;

import github.denisspec989.retailexpertdemoservice.model.price.PriceDetailDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceShortDto;
import github.denisspec989.retailexpertdemoservice.model.price.PriceUpdateDto;
import github.denisspec989.retailexpertdemoservice.service.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/finance")
@RequiredArgsConstructor
public class FinanceController {
    private final PriceService priceService;

    @GetMapping("/price/list")
    public ResponseEntity<Page<PriceShortDto>> getPricesPaginated(Pageable pageable){
        return new ResponseEntity<>(priceService.getPricesPaginated(pageable), HttpStatus.valueOf(200));
    }
    @GetMapping("/price")
    public ResponseEntity<PriceDetailDto> getPriceDetail(@RequestParam("id")UUID id){
        return new ResponseEntity<>(priceService.getPriceDetail(id), HttpStatus.valueOf(200));
    }
    @PostMapping("/price")
    public ResponseEntity<PriceDetailDto> createNewPrice(@RequestBody @Valid PriceDetailDto priceDetailDto){
        return new ResponseEntity<>(priceService.createNewPrice(priceDetailDto),HttpStatus.valueOf(200));
    }
    @PutMapping("/price")
    public ResponseEntity<PriceDetailDto> updatePrice(@RequestBody @Valid PriceUpdateDto priceUpdateDto){
        return new ResponseEntity<>(priceService.updatePrice(priceUpdateDto),HttpStatus.valueOf(200));
    }
    @DeleteMapping("/price")
    public ResponseEntity deletePrice(@RequestParam("id")UUID id){
        priceService.deletePrice(id);
        return ResponseEntity.status(200).build();
    }
}
