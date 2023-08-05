package com.andrerog.adapters.file.parsers;

import com.andrerog.core.FinancialRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.util.ArrayList;
import java.util.List;

/**
 * Santander Excel Parser to a standard format fro the application to use.
 *
 * The excel columns are as follow:
 * Data Operação,Data valor,Descrição,Montante( EUR ),Saldo Contabilístico( EUR )
 */
public class SantanderParser implements Parser {

    @Override
    public List<FinancialRecord> getFinancialRecords(HSSFWorkbook workbook) {
        List<FinancialRecord> financialRecords = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            System.out.println(row.getCell(0));
            // TODO convert to actual values
//            new FinancialRecord(
//                    row.getCell(0),
//                    row.getCell(2),
//                    row.getCell(3),
//                    row.getCell(4)
//            );


        }
        return null;
    }
}
