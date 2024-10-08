package com.andrerog.finance.adapters.in.web.json;

import com.andrerog.finance.adapters.in.file.SupportedBanks;
import com.andrerog.finance.core.FinancialRecord;
import com.andrerog.finance.domain.finance.ListBankTransactions;
import com.andrerog.finance.domain.finance.UploadTransactions;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.RestForm;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@Path("/finance")
public class FinanceResource {

    private final UploadTransactions uploadTransactions;

    private final ListBankTransactions listBankTransactions;

    public FinanceResource(final UploadTransactions uploadTransactions,
                           final ListBankTransactions listBankTransactions) {
        this.uploadTransactions = uploadTransactions;
        this.listBankTransactions = listBankTransactions;
    }

    @POST
    @Path("/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Response uploadTransactions(@RestForm("type") SupportedBanks type, @RestForm("file") InputStream file) throws IOException {
        this.uploadTransactions.execute(type, file);
        return Response.noContent().build();
    }

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() throws IOException {
        List<FinancialRecord> records = this.listBankTransactions.execute();
        return Response.ok(records).build();
    }

    @GET
    @Path("/banks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response banks() throws IOException {
        List<String> banks = List.of("SANTANDER", "CAIXA GERAL DEPOSITOS", "ACTIV BANK"); // PUT ON DB
        return Response.ok(banks).build();
    }
}
