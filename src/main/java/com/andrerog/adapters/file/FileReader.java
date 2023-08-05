package com.andrerog.adapters.file;

import com.andrerog.core.FinancialRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface FileReader {

    List<FinancialRecord> readExcel(File file, SupportedBanks strategy) throws IOException;

}
