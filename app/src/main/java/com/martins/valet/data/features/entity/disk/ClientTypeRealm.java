package com.martins.valet.data.features.entity.disk;

import com.martins.valet.domain.features.model.ClientType;

/**
 * Created by policante on 7/15/16.
 */
public enum ClientTypeRealm {
    NORMAL, MONTHLY;

    public static ClientTypeRealm from(ClientType clientType) {
        switch (clientType){
            case MONTHLY:
                return MONTHLY;
            default:
                return NORMAL;
        }
    }
}
