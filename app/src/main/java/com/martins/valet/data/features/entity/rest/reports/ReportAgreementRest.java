package com.martins.valet.data.features.entity.rest.reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportAgreementRest extends QtdAbstractRest {

    @Expose
    @SerializedName("convenio")
    private String agreement;

    public String getAgreement() {
        return agreement;
    }

    public void setAgreement(String agreement) {
        this.agreement = agreement;
    }
}
