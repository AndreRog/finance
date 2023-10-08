package com.andrerog;

import com.andrerog.adapters.file.ExcelReader;
import com.andrerog.adapters.file.FileReader;
import com.andrerog.adapters.file.SupportedBanks;
import com.andrerog.core.FinancialRecord;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        FileReader fileParser = new ExcelReader();
        // TODO: this should be from a file at choice
        List<FinancialRecord> parse = fileParser.readExcel(new File("descarga.xls"), SupportedBanks.SANTANDER);
    }
}

