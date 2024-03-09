package com.andrerog.finance.adapters.in.web.htmx;

import com.andrerog.finance.core.FinancialRecord;
import com.andrerog.finance.domain.finance.ListBankTransactions;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.io.IOException;
import java.util.List;

@Path("htmx/transactions")
public class BankTransactionResource {
    private final ListBankTransactions listBankTransactions;

    // TOOD: remove this
    @CheckedTemplate(requireTypeSafeExpressions = false)
    public static class Templates {

        public static native TemplateInstance list(List<FinancialRecord> financialRecords);

    }

    public BankTransactionResource(final ListBankTransactions listBankTransactions) {
        this.listBankTransactions = listBankTransactions;
    }

    @GET
    public TemplateInstance list() throws IOException {
        List<FinancialRecord> records = this.listBankTransactions.execute();
        return Templates.list(records);
    }

}


