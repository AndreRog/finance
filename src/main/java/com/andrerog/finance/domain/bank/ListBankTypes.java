package com.andrerog.finance.domain.bank;

import com.andrerog.finance.adapters.in.file.SupportedBanks;
import com.andrerog.finance.domain.finance.UploadTransactions;
import org.jboss.logging.Logger;

import java.util.Arrays;
import java.util.List;

public class ListBankTypes {

    private final Logger LOG = Logger.getLogger(UploadTransactions.class);
    // TODO :Receive DB (AT THE END OF THE END)
    public List<SupportedBanks> execute(){
        return Arrays.stream(SupportedBanks.values()).toList();
    }
}
