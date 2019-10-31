package com.necer.ndialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;

import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;


public abstract class NDialog {


    protected AlertDialog mAlertDialog;
    protected View contentView;
    protected int dialogWidth = -1;
    protected int dialogHeight = -1;
    protected boolean cancleable = true;
    protected boolean isFromBottom;
    protected float dimAmount = -1;//弹出时背景的灰度
    protected float dialogCornersTopRadius = -1;//弹窗上半部分圆角大小
    protected float dialogCornersBottmRadius = -1;//弹窗上半部分圆角大小
    protected int dialogBgColor = -1;//弹窗颜色
    protected Context mContext;
    protected int dialogGravity = -1;//弹窗的位置
    protected int windowAnimation = -1;//弹窗的动画
    protected boolean hasBottmInterval;//弹窗的底部保留间隙，和左右间隙相同大小
    protected int screenWith;//屏幕宽度

    protected DialogInterface.OnClickListener positiveOnClickListener;
    protected DialogInterface.OnClickListener negativeOnClickListener;

    private DialogInterface.OnDismissListener onDismissListener;
    private DialogInterface.OnShowListener onShowListener;


    public NDialog(Context context) {
        mContext = context;
        contentView = LayoutInflater.from(context).inflate(getLayoutId(), null);
        mAlertDialog = new AlertDialog.Builder(context).setView(contentView).create();
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        screenWith = windowManager.getDefaultDisplay().getWidth();
        dialogWidth = screenWith * 6 / 7;
        dialogCornersTopRadius = dialogCornersBottmRadius = 10f;
    }

    public abstract int getLayoutId();

    protected <T extends View> T findViewById(int viewId) {
        return contentView.findViewById(viewId);
    }

    public void show() {
        if (onDismissListener != null) {
            mAlertDialog.setOnDismissListener(onDismissListener);
        }
        if (onShowListener != null) {
            mAlertDialog.setOnShowListener(onShowListener);
        }
        mAlertDialog.setCancelable(cancleable);
        mAlertDialog.show();
        Window window = mAlertDialog.getWindow();

        window.setLayout(dialogWidth == -1 ? LinearLayout.LayoutParams.WRAP_CONTENT : dialogWidth, dialogHeight == -1 ? LinearLayout.LayoutParams.WRAP_CONTENT : dialogHeight);
        window.setBackgroundDrawable(getGradientDrawable());

        if (hasBottmInterval) {
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.y = (screenWith - dialogWidth) / 2;
        }
        if (dimAmount != -1) {
            window.setDimAmount(dimAmount);
        }
        if (dialogGravity != -1) {
            window.setGravity(dialogGravity);
        }
        if (windowAnimation != -1) {
            window.setWindowAnimations(windowAnimation);
        } else if (isFromBottom) {
            window.setGravity(Gravity.BOTTOM);
            window.setWindowAnimations(R.style.dialog_anim_style);
        }
    }

    protected GradientDrawable getGradientDrawable() {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(dialogBgColor == -1 ? Color.WHITE : dialogBgColor);
        if (dialogCornersTopRadius != -1 || dialogCornersBottmRadius != -1) {
            float cornersTopRadius = dp2px(mContext, dialogCornersTopRadius);
            float cornersBottmRadius = dp2px(mContext, dialogCornersBottmRadius);
            gradientDrawable.setCornerRadii(new float[]{cornersTopRadius, cornersTopRadius, cornersTopRadius, cornersTopRadius, cornersBottmRadius, cornersBottmRadius, cornersBottmRadius, cornersBottmRadius});
        }
        return gradientDrawable;
    }

    public boolean isShowing() {
        return mAlertDialog.isShowing();
    }

    public void dismiss() {
        mAlertDialog.dismiss();
    }


    //dialog是否底部弹出
    public NDialog isFromBottom(boolean isFromBottom) {
        this.isFromBottom = isFromBottom;
        return this;
    }

    public NDialog hasBottmInterval(boolean hasBottmInterval) {
        this.hasBottmInterval = hasBottmInterval;
        return this;
    }

    //dialog的圆角
    public NDialog setDialogCornersRadius(float dialogCornersTopRadiusDp, float dialogCornersBottmRadiusDp) {
        this.dialogCornersTopRadius = dialogCornersTopRadiusDp;
        this.dialogCornersBottmRadius = dialogCornersBottmRadiusDp;
        return this;
    }

    //dialog宽度，建议依屏幕的宽为参考
    public NDialog setDialogWidth(int dialogWidthPx) {
        this.dialogWidth = dialogWidthPx;
        return this;
    }

    //dialog高度，建议依屏幕的宽为参考
    public NDialog setDialogHeight(int dialogHeight) {
        this.dialogHeight = dialogHeight;
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

    //dialog是否可取消
    public NDialog setCancelable(boolean cancleable) {
        this.cancleable = cancleable;
        return this;
    }


    public NDialog setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.onDismissListener = onDismissListener;
        return this;
    }

    public NDialog setOnShowListener(DialogInterface.OnShowListener onShowListener) {
        this.onShowListener = onShowListener;
        return this;
    }

    public NDialog setPositiveOnClickListener(DialogInterface.OnClickListener positiveOnClickListener) {
        this.positiveOnClickListener = positiveOnClickListener;
        return this;
    }

    public NDialog setNegativeOnClickListener(DialogInterface.OnClickListener negativeOnClickListener) {
        this.negativeOnClickListener = negativeOnClickListener;
        return this;
    }

    public int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

}
