package com.martins.valet.data.features.entity.rest.reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by policante on 8/12/16.
 */
public class ReportVehycleRest {

    @Expose
    @SerializedName("avulso")
    private QtdAbstractRest avulso;

    @Expose
    @SerializedName("aberto")
    private QtdAbstractRest open;

    @Expose
    @SerializedName("geral")
    private ReportGeneralRest general;

    @Expose
    @SerializedName("mensal")
    private QtdAbstractRest mensal;

    @Expose
    @SerializedName("convenio")
    private List<ReportAgreementRest> agreements;

    public QtdAbstractRest getAvulso() {
        return avulso;
    }

    public void setAvulso(QtdAbstractRest avulso) {
        this.avulso = avulso;
    }

    public QtdAbstractRest getOpen() {
        return open;
    }

    public void setOpen(QtdAbstractRest open) {
        this.open = open;
    }

    public ReportGeneralRest getGeneral() {
        return general;
    }

    public void setGeneral(ReportGeneralRest general) {
        this.general = general;
    }

    public QtdAbstractRest getMensal() {
        return mensal;
    }

    public void setMensal(QtdAbstractRest mensal) {
        this.mensal = mensal;
    }

    public List<ReportAgreementRest> getAgreements() {
        return agreements;
    }

    public void setAgreements(List<ReportAgreementRest> agreements) {
        this.agreements = agreements;
    }
}

