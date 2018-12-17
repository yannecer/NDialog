package com.necer.ndialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * Created by necer on 2018/12/17.
 */
public abstract class NDialog {

    protected Context mContext;

    private float dialogCornersRadius;//弹窗圆角大小
    protected int dialogBgColor = -1;//弹窗颜色
    protected int dialogWidth;
    private int dialogHeight;
    protected int dialogGravity;
    private float dimAmount;//弹出时背景的灰度
    protected int screenWith;
    private int windowAnimation;
    private boolean cancleable = true;
    protected boolean isFromBottom;

    protected boolean isIos;
    protected String positiveButtonText;
    protected String negativeButtonText;

    protected int positiveButtonColor;
    protected int negativeButtonColor;

    protected float positiveButtonSize;
    protected float negativeButtonSize;

    protected String message;
    protected String title;

    protected DialogInterface.OnClickListener positiveOnClickListener;
    protected DialogInterface.OnClickListener negativeOnClickListener;

    public NDialog(Context context) {
        this(context, false);
    }

    public NDialog(Context context, boolean isIos) {
        this.mContext = context;
        this.isIos = isIos;

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWith = windowManager.getDefaultDisplay().getWidth();
        dimAmount = 0.4f;
        if (isIos) {
            dialogCornersRadius = 50;
           // dialogWidth = screenWith * 3 / 4;
        } else {
            dialogCornersRadius = 10;
           // dialogWidth = screenWith * 9 / 10;
        }
    }

    public AlertDialog create() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(title)
                .setMessage(message)
                .setNegativeButton(negativeButtonText, negativeOnClickListener)
                .setPositiveButton(positiveButtonText, positiveOnClickListener);
        AlertDialog alertDialog = builder.create();
        alertDialog.setCancelable(cancleable);
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                AlertDialog dialogg = (AlertDialog) dialog;
                setAlertDialogWindow(dialogg);
                setAlertDialogDetails(dialogg);

            }
        });
        return alertDialog;
    }

    protected abstract void setAlertDialogDetails(AlertDialog alertDialog);

    private void setAlertDialogWindow(AlertDialog alertDialog) {

        Window window = alertDialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();

        window.setDimAmount(dimAmount);

        window.setGravity(dialogGravity);

        if (dialogGravity != Gravity.CENTER) {
            lp.y = (screenWith - dialogWidth) / 2;
        }

        window.setLayout(dialogWidth, dialogHeight == 0 ? LinearLayout.LayoutParams.WRAP_CONTENT : dialogHeight);
        window.setBackgroundDrawable(getGradientDrawable(dialogBgColor == -1 ? Color.WHITE : dialogBgColor));

        if (windowAnimation != 0) {
            window.setWindowAnimations(windowAnimation);
        } else if (isFromBottom) {
            window.setWindowAnimations(R.style.dialog_anim_style);
        }

    }

    protected GradientDrawable getGradientDrawable(int color) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadii(new float[]{dialogCornersRadius, dialogCornersRadius, dialogCornersRadius, dialogCornersRadius, dialogCornersRadius, dialogCornersRadius, dialogCornersRadius, dialogCornersRadius});
        return gradientDrawable;
    }

    public NDialog setDialogWidth(int dialogWidth) {
        this.dialogWidth = dialogWidth;
        return this;
    }

    public NDialog setDialogHeight(int dialogHeight) {
        this.dialogHeight = dialogHeight;
        return this;
    }

    public NDialog setCancelable(boolean cancleable) {
        this.cancleable = cancleable;
        return this;
    }

    public NDialog setIsFromBottom(boolean isFromBottom) {
        this.isFromBottom = isFromBottom;
        return this;
    }

    public NDialog setWindowAnimation(int windowAnimation) {
        this.windowAnimation = windowAnimation;
        return this;
    }

    public NDialog setDialogGravity(int gravity) {
        this.dialogGravity = gravity;
        return this;
    }

    public NDialog setDialogBgColor(int dialogBgColor) {
        this.dialogBgColor = dialogBgColor;
        return this;
    }


}
