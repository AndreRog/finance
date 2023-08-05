package com.andrerog.adapters.file;

import com.andrerog.adapters.file.parsers.SantanderParser;
import com.andrerog.core.FinancialRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader implements FileReader {

    private static final Logger logger = LoggerFactory.getLogger(ExcelReader.class);

    @Override
    public List<FinancialRecord> readExcel(File file, SupportedBanks strategy) throws IOException {
        logger.info("Parser strategy is: {}", strategy.toString());
        try {
            FileInputStream fileStream = new FileInputStream(file);
            HSSFWorkbook workbook = new HSSFWorkbook(fileStream);

            switch (strategy) {
                case SANTANDER -> {
                    return new SantanderParser().getFinancialRecords(workbook);
                }
                default -> {
                    return new ArrayList<>();
                }
            }
        } catch (IOException ex ) {
            logger.error(ex.getMessage());
            logger.error("Error reading from file \n ", ex);
        }
        return null;
    }
}
