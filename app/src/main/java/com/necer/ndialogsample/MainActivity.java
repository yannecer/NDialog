package com.necer.ndialogsample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.necer.ndialog.NDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void confirmDialog(View view) {


        new NDialog(this).setTitle("我是标题")
                .setTitleColor(Color.parseColor("#00c8aa"))
                .setTitleSize(16)
                .setTitleCenter(false)
                .setMessageCenter(false)
                .setMessage("我是meaasge")
                .setMessageColor(Color.parseColor("#00ff00"))
                .setNegativeButtonText(null)
                .setNegativeTextColor(Color.parseColor("#00ff00"))
                .setPositiveButtonText("确定")
                .setPositiveTextColor(Color.parseColor("#ff0000"))
                .setButtonCenter(false)
                .setButtonSize(14)
                .setCancleable(false)
                .setOnConfirmListener(new NDialog.OnConfirmListener() {
                    @Override
                    public void onClick(int which) {
                        //which,0代表NegativeButton，1代表PositiveButton
                    }
                }).create(NDialog.CONFIRM).show();


    }

    public void intputDialog(View view) {
        new NDialog(this).setTitle("我是标题")
                .setInputHintText("hint")
                .setInputHintTextColor(Color.parseColor("#c1c1c1"))
                .setInputText("text")
                .setInputTextColor(Color.parseColor("#333333"))
                .setInputTextSize(14)
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .setInputLineColor(Color.parseColor("#00ff00"))
                .setPositiveButtonText("确定")
                .setNegativeButtonText("取消")
                .setOnInputListener(new NDialog.OnInputListener() {
                    @Override
                    public void onClick(String inputText, int which) {
                        //which,0代表NegativeButton，1代表PositiveButton
                    }
                }).create(NDialog.INPUT).show();

    }

    public void choiceDialog(View view) {

        new NDialog(this)
                .setItems(new String[]{"aaa", "bbb", "ccc", "ddd"})
                .setItemGravity(Gravity.LEFT)
                .setItemColor(Color.parseColor("#000000"))
                .setItemHeigh(50)
                .setItemSize(16)
                .setDividerHeigh(10)
                .setDividerColor(Color.parseColor("#00ff00"))
                .setHasDivider(true)
                .setOnChoiceListener(new NDialog.OnChoiceListener() {
                    @Override
                    public void onClick(String item, int which) {

                    }
                }).create(NDialog.CHOICE).show();


    }

    class AAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 5;
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

            TextView textView = new TextView(MainActivity.this);
            textView.setText("adfadfsd");

            return textView;
        }
    }


}
