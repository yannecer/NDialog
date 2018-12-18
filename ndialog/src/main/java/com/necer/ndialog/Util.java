package com.necer.ndialog;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by necer on 2018/12/12.
 */
public class Util {

    public static float sp2px(Context context, float spVal) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    public static float dp2px(Context context, float dpVal) {
        return  TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

}
