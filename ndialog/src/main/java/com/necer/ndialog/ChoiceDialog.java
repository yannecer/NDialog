package com.necer.ndialog;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

/**
 * Created by necer on 2018/12/14.
 */
public class ChoiceDialog extends NDialog {


    private int titleMaxLines;//title的行数
    private int titleColor;//title的颜色
    private float titleSize;//title的大小
    private Typeface titleTypeface;
    private int titlePaddingLeft, titlePaddingTop, titlePaddingRight, titlePaddingBottom;
    private int titleGravity;

    private int itemTextColor;
    private float itemTextSize;
    private Typeface itemTextTypeface;
    private int itemHeight;
    private int itemTextGravity;
    private int itemTextPaddingLeft, itemTextPaddingRight;


    private Typeface cancelButtonTextTypeface;

    private int dividerColor;
    private int dividerHeight;

    private int itemDividerPadding;

    private Object[] items;

    private boolean hasCancleButton;
    private String cancleButtonText;

    private boolean isIos;

    private OnItemClickListener onItemClickListener;

    public ChoiceDialog(Context context) {
        this(context, false);
    }

    public ChoiceDialog(Context context, boolean isIos) {
        super(context);
        this.isIos = isIos;
        titleMaxLines = 2;
        titleTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
        titlePaddingLeft = titlePaddingRight = titlePaddingTop = titlePaddingBottom = 15;
        itemTextTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
        itemHeight = 50;
        itemTextPaddingLeft = itemTextPaddingRight = 15;
        dividerColor = Color.LTGRAY;
        dividerHeight = 1;
        itemDividerPadding = 15;


        if (isIos) {
            dialogWidth = (int) (screenWith - Util.dp2px(mContext, 30));
            titleGravity = Gravity.CENTER;
            titleColor = Color.parseColor("#2C7CF6");
            titleSize = 20f;
            itemTextGravity = Gravity.CENTER;
            itemTextColor = Color.parseColor("#2C7CF6");
            itemTextSize = 18f;
            isFromBottom = true;
            dialogGravity = Gravity.BOTTOM;
            dialogCornersRadius = 13f;
            cancelButtonTextTypeface = Typeface.defaultFromStyle(Typeface.BOLD);
        } else {
            titleColor = Color.BLACK;
            titleGravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
            titleSize = 16f;
            itemTextGravity = Gravity.LEFT | Gravity.CENTER_VERTICAL;
            itemTextColor = Color.BLACK;
            itemTextSize = 14f;
            dialogCornersRadius = 3f;
            isFromBottom = false;
            dialogGravity = Gravity.CENTER;
            cancelButtonTextTypeface = Typeface.defaultFromStyle(Typeface.NORMAL);
        }

    }

    @Override
    protected void setDialogDetails(Context context,final AlertDialog alertDialog) {
        GradientDrawable divider = new GradientDrawable();
        divider.setColor(dividerColor);
        divider.setSize(dividerHeight, dividerHeight);

        LinearLayout allParentView = new LinearLayout(mContext);
        allParentView.setOrientation(LinearLayout.VERTICAL);

        LinearLayout parentView = new LinearLayout(mContext);
        parentView.setOrientation(LinearLayout.VERTICAL);
        parentView.setDividerDrawable(divider);
        parentView.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        if (!TextUtils.isEmpty(title)) {
            TextView titleView = new TextView(mContext);
            titleView.setTextSize(TypedValue.COMPLEX_UNIT_PX, Util.sp2px(mContext, titleSize));
            titleView.setTextColor(titleColor);
            titleView.setMaxLines(titleMaxLines);
            titleView.setEllipsize(TextUtils.TruncateAt.END);
            titleView.setGravity(titleGravity);
            titleView.setPadding((int) Util.dp2px(mContext, titlePaddingTop), (int) Util.dp2px(mContext, titlePaddingLeft), (int) Util.dp2px(mContext, titlePaddingRight), (int) Util.dp2px(mContext, titlePaddingBottom));
            titleView.setTypeface(titleTypeface);
            titleView.setText(title);
            parentView.addView(titleView);
        }

        if (items == null || items.length <= 0) {
            throw new RuntimeException("items的长度不能为0");
        }

        NestedScrollView nestedScrollView = new NestedScrollView(mContext);
        LinearLayout itemParent = new LinearLayout(mContext);
        itemParent.setOrientation(LinearLayout.VERTICAL);
        itemParent.setDividerDrawable(divider);
        itemParent.setDividerPadding((int) Util.dp2px(mContext, itemDividerPadding));
        itemParent.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        for (int i = 0; i < items.length; i++) {
            final TextView textView = getItemTextView(itemTextTypeface);
            textView.setText(items[i].toString());
            final int finalI = i;
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(textView, finalI);
                    }
                }
            });

            itemParent.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) Util.dp2px(mContext, itemHeight)));
        }

        nestedScrollView.addView(itemParent);
        parentView.addView(nestedScrollView);
        allParentView.addView(parentView);

        if (hasCancleButton) {
            final TextView textView = getItemTextView(cancelButtonTextTypeface);
            textView.setText(cancleButtonText == null ? "取消" : cancleButtonText);
            if (isIos) {
                alertDialog.getWindow().setBackgroundDrawable(getGradientDrawable(Color.TRANSPARENT));
                parentView.setBackgroundDrawable(getGradientDrawable(dialogBgColor == -1 ? Color.WHITE : dialogBgColor));
                allParentView.addView(new Space(mContext), new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, (screenWith - dialogWidth) / 2));
                textView.setBackgroundDrawable(getGradientDrawable(dialogBgColor == -1 ? Color.WHITE : dialogBgColor));
            } else {
                View view = new View(mContext);
                view.setBackgroundColor(dividerColor);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dividerHeight);
                layoutParams.setMargins((int) Util.dp2px(mContext, itemDividerPadding), 0, (int) Util.dp2px(mContext, itemDividerPadding), 0);
                allParentView.addView(view, layoutParams);
            }

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(textView, -1);
                    }
                }
            });

            allParentView.addView(textView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (int) Util.dp2px(mContext, itemHeight)));
        }

        alertDialog.setContentView(allParentView);
    }

    private TextView getItemTextView(Typeface typeface) {
        TextView textView = new TextView(mContext);

        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = textView.getContext().getTheme();
        if (theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)) {
            textView.setBackgroundResource(typedValue.resourceId);
        }
        textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, Util.sp2px(mContext, itemTextSize));
        textView.setTextColor(itemTextColor);
        textView.setMaxLines(1);
        textView.setEllipsize(TextUtils.TruncateAt.END);
        textView.setGravity(itemTextGravity);
        textView.setPadding((int) Util.dp2px(mContext, itemTextPaddingLeft), 0, (int) Util.dp2px(mContext, itemTextPaddingRight), 0);
        textView.setTypeface(typeface);
        return textView;
    }


    public ChoiceDialog setTtitle(String title) {
        this.title = title;
        return this;
    }

    public ChoiceDialog setTitleSize(float titleSizeSp) {
        this.titleSize = titleSizeSp;
        return this;
    }

    public ChoiceDialog setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public ChoiceDialog setTtitle(String title, float titleSizeSp) {
        this.title = title;
        this.titleSize = titleSizeSp;
        return this;
    }

    public ChoiceDialog setTtitle(String title, int titleColor) {
        this.title = title;
        this.titleColor = titleColor;
        return this;
    }

    public ChoiceDialog setTtitle(String title, float titleSizeSp, int titleColor) {
        this.title = title;
        this.titleSize = titleSizeSp;
        this.titleColor = titleColor;
        return this;
    }

    public ChoiceDialog setTtitlePadding(int titlePaddingLeftDp, int titlePaddingTopDp, int titlePaddingRightDp, int titlePaddingBottomDp) {
        this.titlePaddingLeft = titlePaddingLeftDp;
        this.titlePaddingTop = titlePaddingTopDp;
        this.titlePaddingRight = titlePaddingRightDp;
        this.titlePaddingBottom = titlePaddingBottomDp;
        return this;
    }

    public ChoiceDialog setItemTextSize(float itemTextSizeSp) {
        this.itemTextSize = itemTextSizeSp;
        return this;
    }

    public ChoiceDialog setItemTextColor(int itemTextColor) {
        this.itemTextColor = itemTextColor;
        return this;
    }

    public ChoiceDialog setItemText(float itemTextSizeSp, int itemTextColor) {
        this.itemTextSize = itemTextSizeSp;
        this.itemTextColor = itemTextColor;
        return this;
    }

    public ChoiceDialog setItemTextPaddingLeft(int itemTextPaddingLeftDp) {
        this.itemTextPaddingLeft = itemTextPaddingLeftDp;
        return this;
    }

    public ChoiceDialog setItemTextPaddingRight(int itemTextPaddingRightDp) {
        this.itemTextPaddingRight = itemTextPaddingRightDp;
        return this;
    }

    public ChoiceDialog setItemTextPadding(int itemTextPaddingLeftDp, int itemTextPaddingRightDp) {
        this.itemTextPaddingLeft = itemTextPaddingLeftDp;
        this.itemTextPaddingRight = itemTextPaddingRightDp;
        return this;
    }

    public ChoiceDialog setItems(Object[] items) {
        this.items = items;
        return this;
    }

    public ChoiceDialog setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        return this;
    }

    public ChoiceDialog setDividerHeight(int dividerHeightpX) {
        this.dividerHeight = dividerHeightpX;
        return this;
    }

    public ChoiceDialog hasCancleButton(boolean hasCancleButton) {
        this.hasCancleButton = hasCancleButton;
        return this;
    }

    public ChoiceDialog hasCancleButton(boolean hasCancleButton, String cancleButtonText) {
        this.hasCancleButton = hasCancleButton;
        this.cancleButtonText = cancleButtonText;
        return this;
    }

    public ChoiceDialog setTitleMaxLines(int titleMaxLines) {
        this.titleMaxLines = titleMaxLines;
        return this;
    }

    public ChoiceDialog setTitleTypeface(Typeface titleTypeface) {
        this.titleTypeface = titleTypeface;
        return this;
    }

    public ChoiceDialog setItemTextTypeface(Typeface itemTextTypeface) {
        this.itemTextTypeface = itemTextTypeface;
        return this;
    }

    public ChoiceDialog setTitleGravity(int titleGravity) {
        this.titleGravity = titleGravity;
        return this;
    }

    public ChoiceDialog setItemHeight(int itemHeight) {
        this.itemHeight = itemHeight;
        return this;

    }

    public ChoiceDialog setItemTextGravity(int itemTextGravity) {
        this.itemTextGravity = itemTextGravity;
        return this;
    }

    public ChoiceDialog setItemDividerPadding(int itemDividerPadding) {
        this.itemDividerPadding = itemDividerPadding;
        return this;
    }


    public ChoiceDialog setCancleButtonText(String cancleButtonText) {
        this.cancleButtonText = cancleButtonText;
        return this;
    }

    public ChoiceDialog setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        return this;
    }

    public interface OnItemClickListener {
        void onItemClick(TextView onClickView, int position);
    }
}
