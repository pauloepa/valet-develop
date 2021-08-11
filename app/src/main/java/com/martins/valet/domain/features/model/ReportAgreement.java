package com.martins.valet.domain.features.model;

public class ReportAgreement extends QtdAbstract {

    private String agreement;

    public ReportAgreement(String agreement, int quantity) {
        super(quantity);
        this.agreement = agreement;
    }

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }
}
