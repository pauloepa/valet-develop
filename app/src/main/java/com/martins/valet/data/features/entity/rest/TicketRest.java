package com.martins.valet.data.features.entity.rest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by policante on 7/27/16.
 */
public class TicketRest {

    @Expose
    @SerializedName("ticket")
    private String ticket;
    @Expose
    @SerializedName("dataEntrada")
    private Date created;
    @Expose
    @SerializedName("veiculo")
    private VehycleResponse vehycle;

    public TicketRest() {
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public VehycleResponse getVehycle() {
        return vehycle;
    }

    public void setVehycle(VehycleResponse vehycle) {
        this.vehycle = vehycle;
    }
}
