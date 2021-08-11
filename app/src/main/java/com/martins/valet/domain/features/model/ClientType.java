package com.martins.valet.domain.features.model;

import com.martins.valet.data.features.entity.disk.ClientTypeRealm;

/**
 * Created by policante on 7/15/16.
 */
public enum ClientType {
    AVULSO, MONTHLY;

    public static ClientType from(ClientTypeRealm clientType) {
        switch (clientType){
            case MONTHLY:
                return ClientType.MONTHLY;
            default:
                return ClientType.AVULSO;
        }
    }

}
