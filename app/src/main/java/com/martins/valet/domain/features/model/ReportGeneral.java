package com.martins.valet.domain.features.model;

import android.text.TextUtils;

public class ReportGeneral extends QtdAbstract {

    private String valueTotal;
    private String valueBegin;
    private String valueFinal;

    public String getValueTotal() {
        return TextUtils.isEmpty(valueTotal) ? "0" : valueTotal;
    }

    public void setValueTotal(String valueTotal) {
        this.valueTotal = valueTotal;
    }

    public String getValueBegin() {
        return TextUtils.isEmpty(valueBegin) ? "0" : valueBegin;
    }

    public void setValueBegin(String valueBegin) {
        this.valueBegin = valueBegin;
    }

    public String getValueFinal() {
        return TextUtils.isEmpty(valueFinal) ? "0" : valueFinal;
    }

    public void setValueFinal(String valueFinal) {
        this.valueFinal = valueFinal;
    }
}
