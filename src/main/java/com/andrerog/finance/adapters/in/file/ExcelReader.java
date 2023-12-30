package com.andrerog.finance.adapters.in.file;

import com.andrerog.finance.adapters.in.file.parsers.SantanderParser;
import com.andrerog.finance.core.FinancialRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ExcelReader implements TransactionsReader {

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

    @Override
    public List<FinancialRecord> read(InputStream input, SupportedBanks strategy) throws IOException {
        logger.info("Parser strategy is: {}", strategy.toString());

        BufferedReader fileReader = new BufferedReader(new
                InputStreamReader(input, StandardCharsets.UTF_8));
        //System.out.println(input);

        try {
            HSSFWorkbook workbook = new HSSFWorkbook(input);

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
