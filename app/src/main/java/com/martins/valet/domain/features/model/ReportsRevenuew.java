package com.martins.valet.domain.features.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by policante on 8/12/16.
 */
public class ReportsRevenuew {

    private QtdAbstract avulso;

    private QtdAbstract open;

    private ReportGeneral general;

    private QtdAbstract mensal;

    private List<ReportAgreement> agreements;

    public ReportsRevenuew() {
        avulso = new QtdAbstract();
        open = new QtdAbstract();
        general = new ReportGeneral();
        mensal = new QtdAbstract();
        agreements = new ArrayList<>();
    }

    public QtdAbstract getAvulso() {
        return avulso;
    }

    public void setAvulso(QtdAbstract avulso) {
        this.avulso = avulso;
    }

    public QtdAbstract getOpen() {
        return open;
    }

    public void setOpen(QtdAbstract open) {
        this.open = open;
    }

    public ReportGeneral getGeneral() {
        return general;
    }

    public void setGeneral(ReportGeneral general) {
        this.general = general;
    }

    public QtdAbstract getMensal() {
        return mensal;
    }

    public void setMensal(QtdAbstract mensal) {
        this.mensal = mensal;
    }

    public List<ReportAgreement> getAgreements() {
        return agreements;
    }

    public void setAgreements(List<ReportAgreement> agreements) {
        this.agreements = agreements;
    }
}