package com.andrerog.adapters.file.parsers;

import com.andrerog.core.FinancialRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface Parser {

    List<FinancialRecord> getFinancialRecords(HSSFWorkbook workbook);
}
