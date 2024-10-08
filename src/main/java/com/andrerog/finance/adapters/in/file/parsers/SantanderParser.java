package com.andrerog.finance.adapters.in.file.parsers;

import com.andrerog.finance.core.FinancialRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/** * Santander Excel Parser to a standard format for the application to use.
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
            if(row_counter <= 6 || row == null || row.getCell(0) == null){
                continue;
            }

            try {
                final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                OffsetDateTime transactionDate = LocalDate.parse(
                        row.getCell(0).toString(),
                        formatter
                ).atStartOfDay().atOffset(ZoneOffset.UTC);

                transactionDate = transactionDate.withOffsetSameInstant(ZoneOffset.UTC);
                double valueSpent = row.getCell(3).getNumericCellValue();
                String description = row.getCell(2).getStringCellValue();
                double finalBalance = row.getCell(4).getNumericCellValue();

                financialRecords.add(new FinancialRecord(
                        transactionDate,
                        valueSpent,
                        description,
                        finalBalance
                ));

//                final String debugString = String.format("transactionDate: %s\nvalueSpent: %s\ndescription: %s\nfinalBalance: %s\n",
//                        formatter.format(transactionDate), valueSpent,
//                        description, finalBalance);
//                logger.debug(debugString );
//

            } catch (DateTimeParseException e) {
                logger.error("Error parsing date from Santander Excel", e);
            }
        }

        return financialRecords;
    }
}
