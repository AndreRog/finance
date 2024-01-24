package com.andrerog.finance.adapters.in.web;

import com.andrerog.finance.adapters.in.file.SupportedBanks;
import com.andrerog.finance.core.FinancialSummary;
import com.andrerog.finance.domain.finance.CreateFinancialRecordsRequest;
import com.andrerog.finance.domain.finance.CreateFinancialReport;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestForm;

import java.io.IOException;
import java.io.InputStream;


@Path("/finance")
public class FinanceResource {

    private final CreateFinancialReport createFinancialReport;


    public FinanceResource(CreateFinancialReport createFinancialReport) {
        this.createFinancialReport = createFinancialReport;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(@RestForm("type") SupportedBanks type, @RestForm("file") InputStream file) throws IOException {
        FinancialSummary financialSummary = this.createFinancialReport.execute(new CreateFinancialRecordsRequest(type, file));
        return Response.ok(financialSummary).build();
    }
}
