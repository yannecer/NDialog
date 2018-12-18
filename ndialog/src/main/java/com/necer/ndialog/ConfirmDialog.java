package com.necer.ndialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AlertDialogLayout;
import android.support.v7.widget.ButtonBarLayout;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
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
public class ConfirmDialog extends NDialog {




    private int titlePaddingTop;
    private int titlePaddingBottom;

    private int messagePaddingTop;
    private int messagePaddingBottom;

    private Typeface titleTypeface;
    private Typeface messageTypeface;


    private int iosDividerColor;
    private int iosDividerSize;
    private float messageSize;
    private int messageColor;
    private float titleSize;
    private int titleColor;

    private boolean isIos;


    public ConfirmDialog(Context context) {
        this(context, false);
    }


    public ConfirmDialog(Context context, boolean isIos) {
        super(context);
        this.isIos = isIos;
        if (isIos) {
            dialogCornersRadius = 15f;
            iosDividerSize = 1;
            iosDividerColor = Color.LTGRAY;
            titleSize = 18f;
            positiveButtonColor = Color.parseColor("#2C7CF6");
            negativeButtonColor = Color.parseColor("#2C7CF6");
            positiveButtonSize = 18f;
            negativeButtonSize = 18f;
            dialogWidth = screenWith * 3 / 4;
        } else {
            dialogCornersRadius = 3f;
        }
    }

    @Override
    protected void setDialogDetails(Context context,AlertDialog alertDialog) {

        Window window = alertDialog.getWindow();

        TextView titleView = window.findViewById(R.id.alertTitle);
        TextView messageView = window.findViewById(android.R.id.message);

        titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleSize == 0 ? titleView.getTextSize() : Util.sp2px(mContext, titleSize));
        titleView.setTextColor(titleColor == 0 ? titleView.getCurrentTextColor() : titleColor);
        titleView.setPadding(titleView.getPaddingLeft(), titlePaddingTop == 0 ? titleView.getPaddingTop() : (int) Util.dp2px(mContext, titlePaddingTop), titleView.getPaddingRight(), titlePaddingBottom == 0 ? titleView.getPaddingBottom() : (int) Util.dp2px(mContext, titlePaddingBottom));
        if (titleTypeface != null) {
            titleView.setTypeface(titleTypeface);
        }

        messageView.setTextSize(TypedValue.COMPLEX_UNIT_PX, messageSize == 0 ? messageView.getTextSize() : Util.sp2px(mContext, messageSize));
        messageView.setTextColor(messageColor == 0 ? messageView.getCurrentTextColor() : messageColor);
        messageView.setPadding(messageView.getPaddingLeft(), messagePaddingTop == 0 ? messageView.getPaddingTop() : (int) Util.dp2px(mContext, messagePaddingTop), messageView.getPaddingRight(), messagePaddingBottom == 0 ? messageView.getPaddingBottom() : (int) Util.dp2px(mContext, messagePaddingBottom));
        if (messageTypeface != null) {
            messageView.setTypeface(messageTypeface);
        }

        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);

        negativeButton.setTextColor(negativeButtonColor == 0 ? negativeButton.getCurrentTextColor() : negativeButtonColor);
        positiveButton.setTextColor(positiveButtonColor == 0 ? positiveButton.getCurrentTextColor() : positiveButtonColor);

        negativeButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, negativeButtonSize == 0 ? negativeButton.getTextSize() : Util.sp2px(mContext, negativeButtonSize));
        positiveButton.setTextSize(TypedValue.COMPLEX_UNIT_PX, positiveButtonSize == 0 ? positiveButton.getTextSize() : Util.sp2px(mContext, positiveButtonSize));

        if (!(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(message))) {
            titleView.setPadding(titleView.getPaddingLeft(), (int) Util.dp2px(mContext, titlePaddingTop == 0 ? 15 : titlePaddingTop), titleView.getPaddingRight(), (int) Util.dp2px(mContext, titlePaddingBottom == 0 ? 20 : titlePaddingBottom));
            messageView.setPadding(messageView.getPaddingLeft(), (int) Util.dp2px(mContext, messagePaddingTop == 0 ? 15 : messagePaddingTop), messageView.getPaddingRight(), (int) Util.dp2px(mContext, messagePaddingBottom == 0 ? 20 : messagePaddingBottom));
        }

        if (isIos) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                titleView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                messageView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
                messageView.setGravity(Gravity.CENTER);
            }

            LinearLayout.LayoutParams buttomParams = new LinearLayout.LayoutParams(0, FrameLayout.LayoutParams.MATCH_PARENT, 1);
            buttomParams.setMargins(0, 0, 0, 0);
            negativeButton.setLayoutParams(buttomParams);
            positiveButton.setLayoutParams(buttomParams);
            negativeButton.setPadding(0, 0, 0, 0);
            positiveButton.setPadding(0, 0, 0, 0);

            Space space = window.findViewById(R.id.spacer);
            space.setVisibility(View.GONE);
            //  Button button3 = window.findViewById(android.R.id.button3);
            //  button3.setVisibility(View.GONE);

            GradientDrawable divider = new GradientDrawable();
            divider.setColor(iosDividerColor);
            divider.setSize(iosDividerSize, iosDividerSize);

            ButtonBarLayout buttonBarLayout = (ButtonBarLayout) negativeButton.getParent();
            buttonBarLayout.setPadding(0, 0, 0, 0);
            buttonBarLayout.setDividerDrawable(divider);
            buttonBarLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

            ScrollView scrollView = (ScrollView) buttonBarLayout.getParent();
            AlertDialogLayout parent = (AlertDialogLayout) scrollView.getParent();

            View dividerView = new View(mContext);
            dividerView.setBackgroundColor(iosDividerColor);
            parent.addView(dividerView, 2, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
        }


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

    public ConfirmDialog setIosDividerColor(int iosDividerColor) {
        this.iosDividerColor = iosDividerColor;
        return this;
    }

    public ConfirmDialog setIosDividerSize(int iosDividerSizePx) {
        this.iosDividerSize = iosDividerSizePx;
        return this;
    }


    public ConfirmDialog setTitlePaddingTop(int titlePaddingTopDp) {
        this.titlePaddingTop = titlePaddingTopDp;
        return this;
    }

    public ConfirmDialog setTitlePaddingBottom(int titlePaddingBottomDp) {
        this.titlePaddingBottom = titlePaddingBottomDp;
        return this;
    }

    public ConfirmDialog setTitlePadding(int titlePaddingTopDp, int titlePaddingBottomDp) {
        this.titlePaddingTop = titlePaddingTopDp;
        this.titlePaddingBottom = titlePaddingBottomDp;
        return this;
    }

    public ConfirmDialog setMessagePaddingTop(int messagePaddingTopDp) {
        this.messagePaddingTop = messagePaddingTopDp;
        return this;
    }

    public ConfirmDialog setMessagePaddingBottom(int messagePaddingBottomDp) {
        this.messagePaddingBottom = messagePaddingBottomDp;
        return this;
    }

    public ConfirmDialog setMessagePadding(int messagePaddingTopDp, int messagePaddingBottomDp) {
        this.messagePaddingTop = messagePaddingTopDp;
        this.messagePaddingBottom = messagePaddingBottomDp;
        return this;
    }

    public ConfirmDialog setTitleTypeface(Typeface titleTypeface) {
        this.titleTypeface = titleTypeface;
        return this;
    }

    public ConfirmDialog setMessageTypeface(Typeface messageTypeface) {
        this.messageTypeface = messageTypeface;
        return this;
    }
}
