package com.necer.ndialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.ButtonBarLayout;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.TextView;

/**
 * Created by necer on 2018/12/13.
 */
public class ConfirmDialog {


    private String positiveButtonText;
    private String negativeButtonText;


    private int positiveButtonColor;
    private int negativeButtonColor;

    private float positiveButtonSize;
    private float negativeButtonSize;


    private int contentPaddingTop = 15;
    private int contentPaddingBottom = 30;

    private float iosCornersRadius = 50;//ios弹窗圆角大小
    private int iosDividerColor;
    private int dialogWidth;

    private boolean cancleable = true;

    private String message;
    private float messageSize;
    private int messageColor;

    private String title;
    private float titleSize;
    private int titleColor;

    private DialogInterface.OnClickListener positiveOnClickListener;
    private DialogInterface.OnClickListener negativeOnClickListener;


    private Context mContext;
    private boolean isIos;

    public ConfirmDialog(Context context) {
        this.mContext = context;
    }

    public ConfirmDialog(Context context, boolean isIos) {
        this(context);
        this.isIos = isIos;
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
        builder.setCancelable(cancleable);

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
        TextView messageView = window.findViewById(android.R.id.message);

        titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize == 0 ? titleView.getTextSize() : Util.sp2px(mContext, titleSize));
        titleView.setTextColor(titleColor == 0 ? titleView.getCurrentTextColor() : titleColor);

        messageView.setTextSize(TypedValue.COMPLEX_UNIT_PX, messageSize == 0 ? messageView.getTextSize() : Util.sp2px(mContext, messageSize));
        messageView.setTextColor(messageColor == 0 ? messageView.getCurrentTextColor() : messageColor);

        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);

        negativeButton.setTextColor(negativeButtonColor == 0 ? negativeButton.getCurrentTextColor() : negativeButtonColor);
        positiveButton.setTextColor(positiveButtonColor == 0 ? positiveButton.getCurrentTextColor() : positiveButtonColor);

        negativeButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, negativeButtonSize == 0 ? negativeButton.getTextSize() : Util.sp2px(mContext, negativeButtonSize));
        positiveButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, positiveButtonSize == 0 ? positiveButton.getTextSize() : Util.sp2px(mContext, positiveButtonSize));

        if (!(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message))) {
            titleView.setPadding(titleView.getPaddingLeft(), titleView.getTotalPaddingTop() + (int) Util.dp2px(mContext, contentPaddingTop), titleView.getPaddingRight(), titleView.getPaddingBottom() + (int) Util.dp2px(mContext, contentPaddingBottom));
            messageView.setPadding(messageView.getPaddingLeft(), messageView.getTotalPaddingTop() + (int) Util.dp2px(mContext, contentPaddingTop), messageView.getPaddingRight(), messageView.getPaddingBottom() + (int) Util.dp2px(mContext, contentPaddingBottom));
        }

        if (isIos) {
            //圆角背景
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(Color.WHITE);
            gradientDrawable.setCornerRadii(new float[]{iosCornersRadius, iosCornersRadius, iosCornersRadius, iosCornersRadius, iosCornersRadius, iosCornersRadius, iosCornersRadius, iosCornersRadius});
            window.setBackgroundDrawable(gradientDrawable);

            int with = window.getWindowManager().getDefaultDisplay().getWidth();
            window.setLayout(dialogWidth == 0 ? (with * 3 / 4) : dialogWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                titleView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                messageView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }

            LinearLayout.LayoutParams buttomParams = new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.WRAP_CONTENT, 1);
            negativeButton.setLayoutParams(buttomParams);
            positiveButton.setLayoutParams(buttomParams);

            Space space = window.findViewById(R.id.spacer);
            space.setVisibility(View.GONE);
            //  Button button3 = window.findViewById(android.R.id.button3);
            //  button3.setVisibility(View.GONE);

            GradientDrawable divider = new GradientDrawable();
            divider.setColor(iosDividerColor == 0 ? Color.LTGRAY : iosDividerColor);
            divider.setSize(1, 1);

            ButtonBarLayout buttonBarLayout = (ButtonBarLayout) negativeButton.getParent();
            buttonBarLayout.setDividerDrawable(divider);
            buttonBarLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

            ScrollView scrollView = (ScrollView) buttonBarLayout.getParent();
            AlertDialogLayout parent = (AlertDialogLayout) scrollView.getParent();

            View dividerView = new View(mContext);
            dividerView.setBackgroundColor(iosDividerColor == 0 ? Color.LTGRAY : iosDividerColor);
            parent.addView(dividerView, 2, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
        } else if (dialogWidth != 0) {
            window.setLayout(dialogWidth, LinearLayout.LayoutParams.WRAP_CONTENT);
        }
    }

    public ConfirmDialog setCancelable(boolean cancleable) {
        this.cancleable = cancleable;
        return this;
    }


    public ConfirmDialog setTtitle(String title) {
        this.title = title;
        return this;
    }

    public ConfirmDialog setTitleSize(float titleSizeSp) {
        this.titleSize = titleSizeSp;
        return this;
    }

    public ConfirmDialog setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public ConfirmDialog setTtitle(String title, float titleSizeSp) {
        this.title = title;
        this.titleSize = titleSizeSp;
        return this;
    }

    public ConfirmDialog setTtitle(String title, int titleColor) {
        this.title = title;
        this.titleColor = titleColor;
        return this;
    }

    public ConfirmDialog setTtitle(String title, float titleSizeSp, int titleColor) {
        this.title = title;
        this.titleSize = titleSizeSp;
        this.titleColor = titleColor;
        return this;
    }


    public ConfirmDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public ConfirmDialog setMessageSize(float messageSizeSp) {
        this.messageSize = messageSizeSp;
        return this;
    }

    public ConfirmDialog setMessageColor(int messageColor) {
        this.messageColor = messageColor;
        return this;
    }


    public ConfirmDialog setMessage(String message, float messageSizeSp) {
        this.message = message;
        this.messageSize = messageSizeSp;
        return this;
    }

    public ConfirmDialog setMessage(String message, int messageColor) {
        this.message = message;
        this.messageColor = messageColor;
        return this;
    }

    public ConfirmDialog setMessage(String message, float messageSizeSp, int messageColor) {
        this.message = message;
        this.messageSize = messageSizeSp;
        this.messageColor = messageColor;
        return this;
    }

    public ConfirmDialog setNegativeButton(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
        return this;
    }


    public ConfirmDialog setNegativeButtonSize(float negativeButtonSizeSp) {
        this.negativeButtonSize = negativeButtonSizeSp;
        return this;
    }

    public ConfirmDialog setNegativeButtonColor(int negativeButtonColor) {
        this.negativeButtonColor = negativeButtonColor;
        return this;
    }

    public ConfirmDialog setNegativeButton(String negativeButtonText, float negativeButtonSizeSp) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonSize = negativeButtonSizeSp;
        return this;
    }


    public ConfirmDialog setNegativeButton(String negativeButtonText, int negativeButtonColor) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonColor = negativeButtonColor;
        return this;
    }

    public ConfirmDialog setNegativeButton(String negativeButtonText, float negativeButtonSizeSp, int negativeButtonColor) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonSize = negativeButtonSizeSp;
        this.negativeButtonColor = negativeButtonColor;
        return this;
    }


    public ConfirmDialog setNegativeButton(String negativeButtonText, DialogInterface.OnClickListener negativeOnClickListener) {
        this.negativeButtonText = negativeButtonText;
        this.negativeOnClickListener = negativeOnClickListener;
        return this;
    }

    public ConfirmDialog setNegativeButton(String negativeButtonText, float negativeButtonSizeSp, DialogInterface.OnClickListener negativeOnClickListener) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonSize = negativeButtonSizeSp;
        this.negativeOnClickListener = negativeOnClickListener;
        return this;
    }

    public ConfirmDialog setNegativeButton(String negativeButtonText, int negativeButtonColor, DialogInterface.OnClickListener negativeOnClickListener) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonColor = negativeButtonColor;
        this.negativeOnClickListener = negativeOnClickListener;
        return this;
    }


    public ConfirmDialog setNegativeButton(String negativeButtonText, float negativeButtonSize, int negativeButtonColor, DialogInterface.OnClickListener negativeOnClickListener) {
        this.negativeButtonText = negativeButtonText;
        this.negativeButtonSize = negativeButtonSize;
        this.negativeButtonColor = negativeButtonColor;
        this.negativeOnClickListener = negativeOnClickListener;
        return this;
    }


    public ConfirmDialog setPositiveButton(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
        return this;
    }

    public ConfirmDialog setPositiveButtonSize(float positiveButtonSizeSp) {
        this.positiveButtonSize = positiveButtonSizeSp;
        return this;
    }

    public ConfirmDialog setPositiveButtonColor(int positiveButtonColor) {
        this.positiveButtonColor = positiveButtonColor;
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, float positiveButtonSizeSp) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonSize = positiveButtonSizeSp;
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, int positiveButtonColor) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonColor = positiveButtonColor;
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, float positiveButtonSizeSp, int positiveButtonColor) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonSize = positiveButtonSizeSp;
        this.positiveButtonColor = positiveButtonColor;
        return this;
    }


    public ConfirmDialog setPositiveButton(String positiveButtonText, DialogInterface.OnClickListener positiveOnClickListener) {
        this.positiveButtonText = positiveButtonText;
        this.positiveOnClickListener = positiveOnClickListener;
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, float positiveButtonSizeSp, DialogInterface.OnClickListener positiveOnClickListener) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonSize = positiveButtonSizeSp;
        this.positiveOnClickListener = positiveOnClickListener;
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, int positiveButtonColor, DialogInterface.OnClickListener positiveOnClickListener) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonColor = positiveButtonColor;
        this.positiveOnClickListener = positiveOnClickListener;
        return this;
    }

    public ConfirmDialog setPositiveButton(String positiveButtonText, float positiveButtonSizeSp, int positiveButtonColor, DialogInterface.OnClickListener positiveOnClickListener) {
        this.positiveButtonText = positiveButtonText;
        this.positiveButtonSize = positiveButtonSizeSp;
        this.positiveButtonColor = positiveButtonColor;
        this.positiveOnClickListener = positiveOnClickListener;
        return this;
    }


    public ConfirmDialog setIosCornersRadius(float iosCornersRadius) {
        this.iosCornersRadius = iosCornersRadius;
        return this;
    }

    public ConfirmDialog setIosDividerColor(int iosDividerColor) {
        this.iosDividerColor = iosDividerColor;
        return this;
    }

    public ConfirmDialog setDialogWidth(int dialogWidth) {
        this.dialogWidth = dialogWidth;
        return this;
    }
}
