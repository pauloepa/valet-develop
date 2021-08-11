package com.martins.valet.Utils.Helpers;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by policante on 7/16/16.
 */
public class ResourceHelper {

    public static int loadDrawableResource(Context context, String image){
        return context.getResources().getIdentifier(image, "drawable", context.getPackageName());
    }

}
