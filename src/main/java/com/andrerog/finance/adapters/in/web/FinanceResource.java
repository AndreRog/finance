package com.andrerog.finance.adapters.in.web;

import com.andrerog.finance.core.FinancialRecord;
import com.andrerog.finance.core.FinancialSummary;
import com.andrerog.finance.domain.finance.CreateFinancialRecordsRequest;
import com.andrerog.finance.domain.finance.CreateFinancialReport;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.util.List;


@Path("/finance")
public class FinanceResource {

    private final CreateFinancialReport createFinancialReport;


    public FinanceResource(CreateFinancialReport createFinancialReport) {
        this.createFinancialReport = createFinancialReport;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(@BeanParam CreateFinancialRecordsRequest createFinancialRecordsRequest) throws IOException {
        FinancialSummary financialSummary = this.createFinancialReport.execute(createFinancialRecordsRequest);
        return Response.ok(financialSummary).build();
    }
}
