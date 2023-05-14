package github.denisspec989.retailexpertdemoservice.service.impl;

import github.denisspec989.retailexpertdemoservice.model.common.ActualsDto;
import github.denisspec989.retailexpertdemoservice.model.common.CSV;
import github.denisspec989.retailexpertdemoservice.service.CSVConverter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVConverterImpl implements CSVConverter {
    @Override
    public List<ActualsDto> convertActualsList(CSV csv) {
        try(
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(csv.getMultipartFile().getInputStream(), StandardCharsets.UTF_8));
                CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withFirstRecordAsHeader().withDelimiter('\t'))
        ) {
            ArrayList<ActualsDto> actuals = new ArrayList<>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for(CSVRecord csvRecord: csvRecords){
                ActualsDto actualsDto = new ActualsDto();
                actualsDto.setDate(csvRecord.get("Date").trim());
                actualsDto.setMaterialCode(Long.parseLong(csvRecord.get("Material No").trim()));
                actualsDto.setAddressCode(Long.parseLong(csvRecord.get("CH3 Ship To Code").trim()));
                actualsDto.setChain(csvRecord.get("Chain").trim());
                actualsDto.setUnits(Long.parseLong(csvRecord.get("Volume, units").trim()));
                actualsDto.setSalesValue(Double.parseDouble(csvRecord.get("Actual Sales Value").trim().replace(",",".")));
                actuals.add(actualsDto);
            }
            return actuals;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
