package github.denisspec989.retailexpertdemoservice.controller;

import github.denisspec989.retailexpertdemoservice.model.common.ActualsDto;
import github.denisspec989.retailexpertdemoservice.model.common.CSV;
import github.denisspec989.retailexpertdemoservice.service.CSVConverter;
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

@RestController
@RequestMapping("/files")
@RequiredArgsConstructor
public class FileController {
    private final CSVConverter csvConverter;
    @PostMapping(value = "/load/csv/actuals", consumes = "multipart/form-data")
    public ResponseEntity<List<ActualsDto>> loadCsv(@RequestParam("file") MultipartFile file) throws IOException {
        List<ActualsDto> actuals =  csvConverter.convertActualsList(new CSV(file));
        return new ResponseEntity<>(actuals, HttpStatus.CREATED);
    }
}
