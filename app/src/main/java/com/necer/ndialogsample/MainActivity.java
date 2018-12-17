package com.necer.ndialogsample;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.necer.ndialog.ChoiceDialog;
import com.necer.ndialog.ConfirmDialog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void confirmDialog(View view) {


        int width = getWindowManager().getDefaultDisplay().getHeight();

        new ConfirmDialog(this, false)
                .setTtitle("标题")
                .setMessage(" int width = getWindowManag                .setMessage(\" int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth();\")\n                .setMessage(\" int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth();\")\n                .setMessage(\" int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth();\")\n                .setMessage(\" int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth();\")\n                .setMessage(\" int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth();\")\ner().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth(); int width = getWindowManager().getDefaultDisplay().getWidth();")

                //  .setCancelable(true)
                // .setDialogBgColor(Color.GREEN)
                // .setMessageColor(Color.GRAY)
                .setPositiveButton("aaa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "asdfasdfas", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("删除", 18, Color.RED, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setDialogHeight(width/2)
                .create().show();


    }

    public void intputDialog(View view) {

        new ChoiceDialog(this, false)
                .setTtitle("挑剔", 20f)
                .setItems(new String[]{"aaaa", "asdfasdf"})
                .setItemTextSize(12f)
                .setOnItemClickListener(new ChoiceDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(TextView onClickView, int position) {
                        Log.e("sss", "sss::" + onClickView);
                        Log.e("sss", "sss::" + position);
                        Toast.makeText(MainActivity.this,position+"",Toast.LENGTH_SHORT).show();
                    }
                })
             //   .setIsFromBottom(true)
                .create().show();


       /* new NDialog(this).setTitle("请输入。。。")
                .setInputHintText("hint")
                .setInputHintTextColor(Color.parseColor("#c1c1c1"))
                .setInputText("")
                .setInputTextColor(Color.parseColor("#333333"))
                .setInputTextSize(14)
                .setInputType(InputType.TYPE_CLASS_TEXT)
                .setInputLineColor(Color.parseColor("#00ff00"))
                .setPositiveButtonText("确定")
                .setNegativeButtonText("取消")
                .setNegativeTextColor(Color.parseColor("#c1c1c1"))
                .setOnInputListener(new NDialog.OnInputListener() {
                    @Override
                    public void onClick(String inputText, int which) {
                        //which,0代表NegativeButton，1代表PositiveButton
                        Toast.makeText(MainActivity.this, "输入了：：" + inputText, Toast.LENGTH_SHORT).show();
                    }
                }).create(NDialog.INPUT).show();
*/
    }

    public void choiceDialog(View view) {

       /* new NDialog(this)
                .setItems(new String[]{"aaa", "bbb", "ccc", "ddd"})
                .setItemGravity(Gravity.LEFT)
                .setItemColor(Color.parseColor("#000000"))
                .setItemHeigh(50)
                .setItemSize(16)
                .setDividerHeigh(1)
                .setAdapter(null)
                .setDividerColor(Color.parseColor("#c1c1c1"))
                .setHasDivider(true)
                .setOnChoiceListener(new NDialog.OnChoiceListener() {
                    @Override
                    public void onClick(String item, int which) {
                        Toast.makeText(MainActivity.this, "选择了：：" + item, Toast.LENGTH_SHORT).show();


                    }
                }).create(NDialog.CHOICE).show();*/


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
