package com.andrerog.finance.adapters.in.file.parsers;

import com.andrerog.finance.core.FinancialRecord;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface Parser {

    List<FinancialRecord> getFinancialRecords(HSSFWorkbook workbook);
}
