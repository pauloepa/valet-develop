package com.martins.valet.data.features.entity.rest.reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QtdAbstractRest {

    @Expose
    @SerializedName("qtd")
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
