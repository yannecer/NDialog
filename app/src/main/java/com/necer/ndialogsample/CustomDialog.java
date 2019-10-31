package com.necer.ndialogsample;

import android.content.Context;

import com.necer.ndialog.NDialog;

/**
 * Created by necer on 2018/12/18.
 */
public class CustomDialog extends NDialog {


    public CustomDialog(Context context) {
        super(context);
       // setDialogCornersRadius(20,20);
      //  isFromBottom(true);
      //  hasBottmInterval(true);
    }

    @Override
    public int getLayoutId() {
        return R.layout.dialog_ios;
    }
}
