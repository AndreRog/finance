package com.andrerog.finance.adapters.in.file;

import com.andrerog.finance.core.FinancialRecord;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface TransactionsReader {

    List<FinancialRecord> readExcel(File file, SupportedBanks strategy) throws IOException;

    List<FinancialRecord> read(InputStream input, SupportedBanks strategy) throws IOException;

}
