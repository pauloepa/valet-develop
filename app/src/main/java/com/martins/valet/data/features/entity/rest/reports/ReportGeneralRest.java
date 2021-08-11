package com.martins.valet.data.features.entity.rest.reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReportGeneralRest extends QtdAbstractRest {

    @Expose
    @SerializedName("total")
    private String valueTotal;
    @Expose
    @SerializedName("inicial")
    private String valueBegin;
    @Expose
    @SerializedName("final")
    private String valueFinal;

    public String getValueTotal() {
        return valueTotal;
    }

    public void setValueTotal(String valueTotal) {
        this.valueTotal = valueTotal;
    }

    public String getValueBegin() {
        return valueBegin;
    }

    public void setValueBegin(String valueBegin) {
        this.valueBegin = valueBegin;
    }

    public String getValueFinal() {
        return valueFinal;
    }

    public void setValueFinal(String valueFinal) {
        this.valueFinal = valueFinal;
    }
}
