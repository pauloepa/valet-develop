package com.martins.valet.Utils.Helpers;

import android.content.Context;
import android.content.SharedPreferences;

import com.martins.valet.R;

public class Preferences {

    private static final String SHARED_NAME = "com.martins.valet";
    private Context context;
    private String company;
    private String cnpj;
    private String phone;
    private String address;
    private String observation;

    public Preferences(Context context) {
        this.context = context;
        load();
    }

    public void load() {
        SharedPreferences shared = this.context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);

        this.company = shared.getString("company", context.getString(R.string.company_name));
        this.cnpj = shared.getString("document", context.getString(R.string.company_document));
        this.phone = shared.getString("phone", context.getString(R.string.company_phone));
        this.address = shared.getString("address", context.getString(R.string.company_address));
        this.observation = shared.getString("observation", context.getString(R.string.company_observation));
    }

    public void save() {
        SharedPreferences shared = this.context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();

        editor.putString("company", this.company);
        editor.putString("document", this.cnpj);
        editor.putString("phone", this.phone);
        editor.putString("address", this.address);
        editor.putString("observation", this.observation);

        editor.apply();
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getDocument() {
        return cnpj;
    }

    public void setDocument(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }
}
