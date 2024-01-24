package com.andrerog.finance.domain.finance;

import com.andrerog.finance.adapters.in.file.SupportedBanks;

import java.io.InputStream;

public record CreateFinancialRecordsRequest(SupportedBanks type, InputStream file){
}
