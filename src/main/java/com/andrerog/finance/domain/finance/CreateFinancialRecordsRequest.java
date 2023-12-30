package com.andrerog.finance.domain.finance;

import com.andrerog.finance.adapters.in.file.SupportedBanks;
import jakarta.ws.rs.FormParam;

import java.io.InputStream;
import java.util.Objects;

public class CreateFinancialRecordsRequest {
    @FormParam("type")
    private final  SupportedBanks type;
    @FormParam("file")
    private final InputStream file;

    public CreateFinancialRecordsRequest() {
        this.type = null;
        this.file = null;
    }
    public CreateFinancialRecordsRequest(SupportedBanks type, InputStream file) {
        this.type = type;
        this.file = file;
    }

    public SupportedBanks type() {
        return type;
    }

    public InputStream file() {
        return file;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CreateFinancialRecordsRequest) obj;
        return Objects.equals(this.type, that.type) &&
                Objects.equals(this.file, that.file);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, file);
    }

    @Override
    public String toString() {
        return "CreateFinancialRecordsRequest[" +
                "type=" + type + ", " +
                "file=" + file + ']';
    }

}
