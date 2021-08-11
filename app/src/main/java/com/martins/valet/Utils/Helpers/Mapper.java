package com.martins.valet.Utils.Helpers;

/**
 * Created by policante on 7/16/16.
 */
public interface Mapper<M, P> {
    P modelToData(M model);
    M dataToModel(P data);
}
