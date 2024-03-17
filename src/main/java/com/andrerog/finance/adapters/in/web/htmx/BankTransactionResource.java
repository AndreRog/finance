package com.andrerog.finance.adapters.in.web.htmx;

import com.andrerog.finance.core.FinancialRecord;
import com.andrerog.finance.domain.bank.ListBankTypes;
import com.andrerog.finance.domain.finance.ListBankTransactions;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Path("htmx/transactions")
public class BankTransactionResource {
    private final ListBankTransactions listBankTransactions;
    private final ListBankTypes listBankTypes;

    @CheckedTemplate(requireTypeSafeExpressions = false)
    public static class Templates {

        public static native TemplateInstance list(List<FinancialRecord> financialRecords);

    }

    public BankTransactionResource(final ListBankTransactions listBankTransactions,
                                   final ListBankTypes listBankTypes) {
        this.listBankTransactions = listBankTransactions;
        this.listBankTypes = listBankTypes;
    }

    @GET
    public TemplateInstance list() throws IOException {
        List<FinancialRecord> records = this.listBankTransactions.execute();
        List<String> banks = this.listBankTypes.execute().stream().map(Enum::toString).collect(Collectors.toList());
        return Templates.list(records).data("banks", banks);
    }

}


