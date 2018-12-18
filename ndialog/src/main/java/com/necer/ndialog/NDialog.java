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

    protected float dialogCornersRadius;//弹窗圆角大小
    protected int dialogBgColor = -1;//弹窗颜色
    protected int dialogWidth;
    private int dialogHeight;
    protected int dialogGravity;
    private float dimAmount;//弹出时背景的灰度
    protected int screenWith;
    private int windowAnimation;
    private boolean cancleable = true;
    protected boolean isFromBottom;


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
        this.mContext = context;

        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        screenWith = windowManager.getDefaultDisplay().getWidth();
        dimAmount = 0.4f;
        dialogWidth = screenWith * 9 / 10;
        dialogGravity = Gravity.CENTER;
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
                setDialogWindow(dialogg);
                setDialogDetails(mContext,dialogg);

            }
        });
        return alertDialog;
    }

    protected abstract void setDialogDetails(Context context, AlertDialog alertDialog);

    private void setDialogWindow(AlertDialog alertDialog) {

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
        float cornersRadius = Util.dp2px(mContext, dialogCornersRadius);
        gradientDrawable.setCornerRadii(new float[]{cornersRadius, cornersRadius, cornersRadius, cornersRadius, cornersRadius, cornersRadius, cornersRadius, cornersRadius});
        return gradientDrawable;
    }

    //dialog宽度，建议依屏幕的宽为参考
    public NDialog setDialogWidth(int dialogWidthPx) {
        this.dialogWidth = dialogWidthPx;
        return this;
    }

    //dialog高度，建议依屏幕的高为参考
    public NDialog setDialogHeight(int dialogHeightPx) {
        this.dialogHeight = dialogHeightPx;
        return this;
    }

    //dialog是否可取消
    public NDialog setCancelable(boolean cancleable) {
        this.cancleable = cancleable;
        return this;
    }

    //dialog是否底部弹出
    public NDialog setIsFromBottom(boolean isFromBottom) {
        this.isFromBottom = isFromBottom;
        return this;
    }

    //dialog弹出动画
    public NDialog setWindowAnimation(int windowAnimation) {
        this.windowAnimation = windowAnimation;
        return this;
    }

    //dialog弹出位置
    public NDialog setDialogGravity(int gravity) {
        this.dialogGravity = gravity;
        return this;
    }

    //dialog的背景颜色
    public NDialog setDialogBgColor(int dialogBgColor) {
        this.dialogBgColor = dialogBgColor;
        return this;
    }

    //dialog弹出时背景的灰度 0.0f-1.0f 0.0f为透明 1.0f为全黑
    public NDialog setDimAmount(float dimAmount) {
        this.dimAmount = dimAmount;
        return this;
    }

    //dialog的圆角
    public NDialog setDialogCornersRadius(float dialogCornersRadiusDp) {
        this.dialogCornersRadius = dialogCornersRadiusDp;
        return this;
    }

}
