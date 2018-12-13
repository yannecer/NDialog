package com.necer.ndialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Window;
import android.widget.TextView;

/**
 * Created by necer on 2018/12/13.
 */
public class ConfirmDialog {


    private String positiveButtonText = "确定";
    private String negativeButtonText = "取消";

    private int positiveButtonColor = Color.parseColor("#666666");
    private int negativeButtonColor = Color.parseColor("#666666");


    private String message;
    private int messageSize = 15;
    private int messageColor = Color.BLACK;

    private String title;
    private int titleSize = 16;
    private int titleColor = Color.BLACK;

    private DialogInterface.OnClickListener positiveOnClickListener;
    private DialogInterface.OnClickListener negativeOnClickListener;


    private Context mContext;

    public ConfirmDialog(Context context) {
        this.mContext = context;
    }

    public AlertDialog create() {


        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);

        if (!TextUtils.isEmpty(title)) {
            builder.setTitle(title);
        }
        if (!TextUtils.isEmpty(message)) {
            builder.setMessage(message);
        }

        builder.setNegativeButton(negativeButtonText, negativeOnClickListener);
        builder.setPositiveButton(positiveButtonText, positiveOnClickListener);

        final AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                setDetails(alertDialog);
            }
        });

        return alertDialog;
    }

    private void setDetails(AlertDialog alertDialog) {

        Window window = alertDialog.getWindow();
        TextView titleView = window.findViewById(R.id.alertTitle);

        // titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize);
        // titleView.setTextColor(titleColor);


        // ViewGroup.LayoutParams layoutParams = titleView.getLayoutParams();

        // titleView.setPadding(0, (int) Util.dp2px(mContext, 10), 0, (int) Util.dp2px(mContext, 10));


    }


    public ConfirmDialog setPositiveButtonColor(int positiveButtonColor) {
        this.positiveButtonColor = positiveButtonColor;
        return this;
    }


    public ConfirmDialog setNegativeButtonColor(int negativeButtonColor) {
        this.negativeButtonColor = negativeButtonColor;
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener positiveOnClickListener) {
        this.positiveButtonText = positiveButtonText;
        this.positiveOnClickListener = positiveOnClickListener;
        return this;
    }

    public ConfirmDialog setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener negativeOnClickListener) {
        this.negativeButtonText = negativeButtonText;
        this.negativeOnClickListener = negativeOnClickListener;
        return this;
    }

    public ConfirmDialog setNegativeButton(DialogInterface.OnClickListener negativeOnClickListener) {
        this.negativeOnClickListener = negativeOnClickListener;
        return this;
    }


    public ConfirmDialog setPositiveButton(DialogInterface.OnClickListener positiveOnClickListener) {
        this.positiveOnClickListener = positiveOnClickListener;
        return this;
    }


    public ConfirmDialog setMessage(String title, String message) {
        this.title = title;
        this.message = message;
        return this;
    }


}
