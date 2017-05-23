package com.necer.ndialog;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;
import android.support.v4.widget.Space;
import android.support.v7.app.AlertDialog;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by necer on 2017/5/23.
 */

public class NDialog {

    private Context mContext;
    private EditText mEditText;
    private View inputLine;
    private BaseAdapter adapter;

    public static final int CONFIRM = 100;//确认提示框
    public static final int INPUT = 200;//输入框
    public static final int CHOICE = 300;//选择

    private String positiveButtonText = "确定";
    private String negativeButtonText = "取消";

    private int positiveTextColor = Color.parseColor("#333333");
    private int negativeTextColor = Color.parseColor("#333333");

    private String message;
    private int messageSize = 15;
    private int messageColor = Color.BLACK;
    private boolean isMessageCenter;//信息居中

    private String title;
    private int titleSize = 16;
    private int titleColor = Color.BLACK;
    private boolean isTitleCenter;//标题是否居中

    private int buttonSize = 14;//button字体大小
    private boolean isButtonCenter;//button居中

    private View customView;

    private String inputText;
    private int inputTextSize = 15;
    private int inputTextColor = Color.parseColor("#333333");
    private String inputHintText;
    private int inputHintTextColor = Color.parseColor("#c1c1c1");
    private int inputType;
    private int inputLineColor = Color.parseColor("#003333");

    private boolean hasDivider=true;//选择时，是否有分割线
    private int dividerHeigh = 1;
    private int dividerColor = Color.parseColor("#c1c1c1");

    private String[] items;
    private int itemColor = Color.parseColor("#333333");
    private int itemSize = 14;
    private int itemGravity = Gravity.LEFT;
    private int itemHeigh = 50;
    private boolean cancleable = true;



    public NDialog(Context context) {
        this.mContext = context;
    }

    private OnConfirmListener onConfirmListener;
    private OnInputListener onInputListener;

    private OnChoiceListener onChoiceListener;

    public AlertDialog create(final int type) {
        if (type == CONFIRM) {
        }else if (type == INPUT) {
            customView = LayoutInflater.from(mContext).inflate(R.layout.input_layout, null);
        } else {//CHOICE
            customView = LayoutInflater.from(mContext).inflate(R.layout.choice_layout, null);
        }

        final AlertDialog alertDialog = new AlertDialog.Builder(mContext)
                .setMessage(message)
                .setTitle(title)
                .setView(customView)
                .setCancelable(cancleable)
                .setNegativeButton(negativeButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setButtonClick(type, 0);
                    }
                })
                .setPositiveButton(positiveButtonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setButtonClick(type, 1);
                    }
                }).create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                setDetails(alertDialog, type);
            }
        });

        return alertDialog;
    }

    private void setButtonClick(int type, int which) {
        if (type == INPUT && onInputListener != null) {
            String inputString = mEditText.getText().toString();
            onInputListener.onClick(inputString, which);
        }

        if (type == CONFIRM && onConfirmListener != null) {
            onConfirmListener.onClick(which);
        }
    }


    private void setDetails(AlertDialog alertDialog, int type) {
        switch (type) {
            case CONFIRM:
                setButtonStyle(alertDialog);
                setTitleStyle(alertDialog);
                setMessageStyle(alertDialog);
                break;
            case INPUT:
                setButtonStyle(alertDialog);
                setTitleStyle(alertDialog);
                setInputStyle();
                popupSoftInput(mContext, mEditText);
                break;
            case CHOICE:
                setListViewStyle(alertDialog);
                break;
        }

    }

    private void setListViewStyle(final AlertDialog alertDialog) {

        ListView listView = (ListView) customView.findViewById(R.id.listview);
        listView.setAdapter(adapter == null ? new AAdapter() : adapter);
        if (hasDivider) {
            listView.setDivider(new ColorDrawable(dividerColor));
            listView.setDividerHeight(dividerHeigh);
        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (onChoiceListener != null) {
                    onChoiceListener.onClick(items == null ? null : items[position], position);
                    alertDialog.dismiss();
                }
            }
        });
    }

    private void setInputStyle() {

        mEditText = (EditText) customView.findViewById(R.id.input);
        inputLine = customView.findViewById(R.id.input_line);

        mEditText.setText(inputText);
        mEditText.setTextColor(inputTextColor);
        mEditText.setTextSize(TypedValue.COMPLEX_UNIT_SP, inputTextSize);
        mEditText.setHint(inputHintText);
        mEditText.setHintTextColor(inputHintTextColor);
        if (inputType != 0) {
            mEditText.setInputType(inputType);
        }
        mEditText.setSelection(inputText == null ? 0 : inputText.length());
        inputLine.setBackgroundColor(inputLineColor);

    }

    private void setButtonStyle(AlertDialog alertDialog) {
        Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
        Button negativeButton = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
        negativeButton.setTextColor(negativeTextColor);
        positiveButton.setTextColor(positiveTextColor);

        negativeButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, buttonSize);
        positiveButton.setTextSize(TypedValue.COMPLEX_UNIT_SP, buttonSize);

        if (isButtonCenter) {
            Window window = alertDialog.getWindow();
            Button button3 = (Button) window.findViewById(android.R.id.button3);
            Space space = (Space) window.findViewById(R.id.spacer);

            button3.setVisibility(View.GONE);
            space.setVisibility(View.GONE);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            negativeButton.setLayoutParams(lp);
            positiveButton.setLayoutParams(lp);
        }
    }

    private void setTitleStyle(AlertDialog alertDialog) {
        Window window = alertDialog.getWindow();
        TextView titleView = (TextView) window.findViewById(R.id.alertTitle);

        titleView.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleSize);
        titleView.setTextColor(titleColor);

        if (isTitleCenter) {
            ImageView imageView = (ImageView) window.findViewById(android.R.id.icon);
            imageView.setVisibility(View.GONE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                titleView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            }
        }
    }

    private void setMessageStyle(AlertDialog alertDialog) {
        Window window = alertDialog.getWindow();
        TextView messageView = (TextView) window.findViewById(android.R.id.message);

        messageView.setTextSize(TypedValue.COMPLEX_UNIT_SP, messageSize);
        messageView.setTextColor(messageColor);

        if (isMessageCenter) {
            messageView.setGravity(Gravity.CENTER);
        }
    }

    private void popupSoftInput(final Context context, final EditText editText) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(editText, 0);
            }
        }, 10);
    }


    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public interface OnConfirmListener {
        void onClick(int which);//whichButton:0,1

    }

    public interface OnInputListener {
        void onClick(String inputText, int which);
    }

    public interface OnChoiceListener {
        void onClick(String item, int which);
    }


    class AAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return items == null ? 0 : items.length;
        }
        @Override
        public Object getItem(int position) {
            return null;
        }
        @Override
        public long getItemId(int position) {
            return 0;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_layout, null);
            LinearLayout linearLayout = (LinearLayout) convertView.findViewById(R.id.ll);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dip2px(mContext, itemHeigh));
            linearLayout.setLayoutParams(layoutParams);
            TextView itemView = (TextView) convertView.findViewById(R.id.text1);
            itemView.setText(items[position]);
            itemView.setTextColor(itemColor);
            itemView.setGravity(itemGravity | Gravity.CENTER_VERTICAL);
            itemView.setTextSize(TypedValue.COMPLEX_UNIT_SP, itemSize);
            return convertView;
        }
    }




    /****************参数statr*****************/


    public NDialog setCancleable(boolean cancleable) {
        this.cancleable = cancleable;
        return this;
    }


    public NDialog setButtonCenter(boolean buttonCenter) {
        isButtonCenter = buttonCenter;
        return this;
    }


    public NDialog setOnConfirmListener(OnConfirmListener onConfirmListener) {
        this.onConfirmListener = onConfirmListener;
        return this;
    }

    public NDialog setOnInputListener(OnInputListener onInputListener) {
        this.onInputListener = onInputListener;
        return this;
    }

    public NDialog setOnChoiceListener(OnChoiceListener onChoiceListener) {
        this.onChoiceListener = onChoiceListener;
        return this;
    }

    public NDialog setInputLineColor(int inputLineColor) {
        this.inputLineColor = inputLineColor;
        return this;
    }

    public NDialog setInputHintText(String inputHintText) {
        this.inputHintText = inputHintText;
        return this;
    }

    public NDialog setInputHintTextColor(int inputHintTextColor) {
        this.inputHintTextColor = inputHintTextColor;
        return this;
    }

    public NDialog setInputType(int inputType) {
        this.inputType = inputType;
        return this;
    }

    public NDialog setMessageCenter(boolean messageCenter) {
        isMessageCenter = messageCenter;
        return this;
    }

    public NDialog setItems(String[] items) {
        this.items = items;
        return this;
    }

    public NDialog setItemColor(int itemColor) {
        this.itemColor = itemColor;
        return this;
    }

    public NDialog setItemSize(int itemSize) {
        this.itemSize = itemSize;
        return this;
    }

    public NDialog setItemGravity(int itemGravity) {
        this.itemGravity = itemGravity;
        return this;
    }

    public NDialog setPositiveButtonText(String positiveButtonText) {
        this.positiveButtonText = positiveButtonText;
        return this;
    }

    public NDialog setNegativeButtonText(String negativeButtonText) {
        this.negativeButtonText = negativeButtonText;
        return this;
    }

    public NDialog setPositiveTextColor(int positiveTextColor) {
        this.positiveTextColor = positiveTextColor;
        return this;
    }

    public NDialog setNegativeTextColor(int negativeTextColor) {
        this.negativeTextColor = negativeTextColor;
        return this;
    }

    public NDialog setMessage(String message) {
        this.message = message;
        return this;
    }

    public NDialog setMessageSize(int messageSize) {
        this.messageSize = messageSize;
        return this;
    }

    public NDialog setMessageColor(int messageColor) {
        this.messageColor = messageColor;
        return this;
    }

    public NDialog setTitle(String title) {
        this.title = title;
        return this;
    }

    public NDialog setTitleSize(int sp) {
        this.titleSize = sp;
        return this;
    }

    public NDialog setTitleColor(int titleColor) {
        this.titleColor = titleColor;
        return this;
    }

    public NDialog setTitleCenter(boolean titleCenter) {
        isTitleCenter = titleCenter;
        return this;
    }

    public NDialog setButtonSize(int buttonSize) {
        this.buttonSize = buttonSize;
        return this;
    }

    public NDialog setInputText(String inputText) {
        this.inputText = inputText;
        return this;
    }

    public NDialog setInputTextColor(int inputTextColor) {
        this.inputTextColor = inputTextColor;
        return this;
    }

    public NDialog setInputTextSize(int sp) {
        this.inputTextSize = sp;
        return this;
    }

    public NDialog setHasDivider(boolean hasDivider) {
        this.hasDivider = hasDivider;
        return this;
    }

    public NDialog setDividerHeigh(int px) {
        this.dividerHeigh = px;
        return this;
    }

    public NDialog setDividerColor(int dividerColor) {
        this.dividerColor = dividerColor;
        return this;
    }

    public NDialog setItemHeigh(int dp) {
        this.itemHeigh = dp;
        return this;
    }

    public NDialog setAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    /****************参数end*****************/





}
