package github.denisspec989.retailexpertdemoservice.model.shipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActualsParsingDto {
    private String date;
    private Long materialCode;
    private Long addressCode;
    private String chain;
    private Long units;
    private Double salesValue;

}
