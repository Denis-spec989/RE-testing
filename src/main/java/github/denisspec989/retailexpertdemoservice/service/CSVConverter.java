package github.denisspec989.retailexpertdemoservice.service;

import github.denisspec989.retailexpertdemoservice.model.common.ActualsDto;
import github.denisspec989.retailexpertdemoservice.model.common.CSV;

import java.util.List;

public interface CSVConverter {
    List<ActualsDto> convertActualsList(CSV csv);
}
