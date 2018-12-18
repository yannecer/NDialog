package com.necer.ndialogsample;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.necer.ndialog.ChoiceDialog;
import com.necer.ndialog.ConfirmDialog;
import com.necer.ndialog.Util;

public class MainActivity extends AppCompatActivity {

    private int screenWith;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        screenWith = windowManager.getDefaultDisplay().getWidth();
    }

    public void materialDesign(View view) {
        new ConfirmDialog(this)
                .setTtitle("标题")
                .setMessage("信息")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }

    public void ios(View view) {
        new ConfirmDialog(this, true)
                .setTtitle("标题")
                .setMessage("信息")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).create().show();
    }

    public void mdChoice1(View view) {
        new ChoiceDialog(this)
                .setItems(new String[]{"aaa", "bbb", "ccc", "ddddd"})
                .setOnItemClickListener(new ChoiceDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(TextView onClickView, int position) {

                    }
                }).create().show();
    }

    public void mdChoice2(View view) {
        new ChoiceDialog(this)
                .setTtitle("请选择")
                .setItems(new String[]{"aaa", "bbb", "ccc", "ddddd"})
                .setOnItemClickListener(new ChoiceDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(TextView onClickView, int position) {

                    }
                })
                .setDialogWidth(screenWith)
                .setIsFromBottom(true)
                .setDialogCornersRadius(0.0f)
                .setDialogGravity(Gravity.BOTTOM)
                .create().show();
    }

    public void iosChoice(View view) {
        new ChoiceDialog(this, true)
                .setItems(new String[]{"aaa", "bbb", "ccc", "ddddd"})
                .hasCancleButton(true)
                .setOnItemClickListener(new ChoiceDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(TextView onClickView, int position) {

                    }
                }).create().show();

    }


    public void wxDel(View view) {
        new ConfirmDialog(this)
                .setMessage("删除后，将清空该聊天信息记录")
                .setNegativeButton("取消", Color.parseColor("#666666"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("删除", Color.RED, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setDialogWidth((int) (screenWith - Util.dp2px(this, 100)))
                .create().show();

    }


    public void wxChoice(View view) {
        new ChoiceDialog(this)
                .setItems(new String[]{"发送给朋友", "收藏", "保存图片", "编辑"})
                .setDividerHight(0)
                .setItemTextColor(Color.parseColor("#575757"))
                .setOnItemClickListener(new ChoiceDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(TextView onClickView, int position) {

                    }
                })
                .setDialogWidth(screenWith)
                .setDialogCornersRadius(0.0f)
                .setIsFromBottom(true)
                .setDialogGravity(Gravity.BOTTOM)
                .create().show();
    }

    public void miConfirm(View view) {

        new ConfirmDialog(this, true)
                .setTtitle("删除", 16.0f, Color.BLACK)
                .setTitleTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
                .setTitlePadding(12, 10)
                .setMessage("确定要删除所选的一个会话吗？", 14f, Color.parseColor("#444444"))
                .setNegativeButton("取消", 14f, Color.parseColor("#333333"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("删除", 14f, Color.parseColor("#5CACE9"), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setDialogWidth((int) (screenWith - Util.dp2px(this, 20)))
                .setDialogGravity(Gravity.BOTTOM)
                .setDialogCornersRadius(10f)
                .setIsFromBottom(true)
                .create().show();
    }


    public void sinaChoice(View view) {

        new ChoiceDialog(this)
                .setTtitle("这是一个新浪微博的选择框的标题，默认最多显示2行，可以设置最大的行数，超过之后会出来省略号")
                .setTtitlePadding(20, 20, 20, 20)
                .setItems(new String[]{"回复", "转发", "复制", "投诉"})
                .setItemText(16.f, Color.BLACK)
                .setItemTextPaddingLeft(20)
                .setOnItemClickListener(new ChoiceDialog.OnItemClickListener() {
                    @Override
                    public void onItemClick(TextView onClickView, int position) {

                    }
                }).setDialogWidth(screenWith * 3 / 4)
                .setDialogCornersRadius(10.f)
                .create().show();

    }

    public void custom(View view) {

        new CustomDialog(this)
                .setDialogCornersRadius(5f)
                .setDialogHeight((int) Util.dp2px(this, 100))
                .setDialogWidth((int) Util.dp2px(this, 100))
                .create().show();

    }

}
