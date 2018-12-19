package com.necer.ndialogsample;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.necer.ndialog.NDialog;

/**
 * Created by necer on 2018/12/18.
 */
public class CustomDialog extends NDialog {

    public CustomDialog(Context context) {
        super(context);
    }

    @Override
    protected void setDialogDetails(Context context,AlertDialog alertDialog) {

        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_custom, null);

        alertDialog.setContentView(inflate);


        setCanceledOnTouchOutside(true);
        setCancelable(true);
        setDimAmount(0F);
    }
}
