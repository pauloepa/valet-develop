package com.martins.valet.domain.features.model;

public class QtdAbstract {

    private int quantity;

    public QtdAbstract() {
    }

    public QtdAbstract(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
