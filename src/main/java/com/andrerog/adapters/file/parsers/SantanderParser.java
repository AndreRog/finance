package com.andrerog.adapters.file.parsers;

import com.andrerog.adapters.file.ExcelReader;
import com.andrerog.core.FinancialRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/** * Santander Excel Parser to a standard format fro the application to use.
 *
 * The excel columns are as follow:
 * Data Operação,Data valor,Descrição,Montante( EUR ),Saldo Contabilístico( EUR )
 */
public class SantanderParser implements Parser {

    private static final Logger logger = LoggerFactory.getLogger(SantanderParser.class);


    @Override
    public List<FinancialRecord> getFinancialRecords(HSSFWorkbook workbook) {
        final List<FinancialRecord> financialRecords = new ArrayList<>();
        final Sheet sheet = workbook.getSheetAt(0);
        int row_counter = 0;
        for (Row row : sheet) {
            row_counter++;

            // skip header rows
            if(row_counter <= 6){
                continue;
            }

            System.out.println(row.getCell(0));
            try {
                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

                Date transactionDate = formatter.parse(row.getCell(0).toString());
                double valueSpent = row.getCell(3).getNumericCellValue();
                String description = row.getCell(2).getStringCellValue();
                double finalBalance = row.getCell(4).getNumericCellValue();

                new FinancialRecord(
                        transactionDate,
                        valueSpent,
                        description,
                        finalBalance
                );

                final String debugString = String.format("transactionDate: %s\nvalueSpent: %s\ndescription: %s\nfinalBalance: %s\n",
                        formatter.format(transactionDate), valueSpent,
                        description, finalBalance);
                logger.info(debugString );

            } catch (ParseException e) {
                logger.error("Error parsing date from Santander Excel", e);
            }
        }
        return null;
    }
}
