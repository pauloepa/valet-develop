package com.martins.valet.data.features.entity.rest.reports;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by policante on 8/12/16.
 */
public class ReportsRevenuewRest {

    @Expose
    @SerializedName("veiculo")
    private ReportVehycleRest vehycle;

    public ReportVehycleRest getVehycle() {
        return vehycle;
    }

    public void setVehycle(ReportVehycleRest vehycle) {
        this.vehycle = vehycle;
    }
}
