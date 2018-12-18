# NDialog
使用Builder模式设置字体大小、颜色、位置的属性


## 效果图
![](https://github.com/yannecer/NDialog/blob/master/app/dialog2.gif)

## 使用方法

## Gradle

```
compile 'com.necer.ndialog2:ndialog:1.1.0'
```

**NDialog**
```
基类,可设置的属性
dialogCornersRadius     //弹窗圆角大小
dialogBgColor           //弹窗背景颜色
dialogWidth             //弹窗宽度，建议依屏幕的宽为参考
dialogHeight            //弹窗高度，建议依屏幕的高为参考
cancleable              //弹窗是否可取消
isFromBottom            //弹窗是否底部弹出
windowAnimation         //弹窗弹出动画资源文件
dimAmount               //弹窗弹出时，窗体的灰度 0.0f-1.0f 0.0f为透明 1.0f为全黑


继承自NDialog的Dialog需先调用自身的方法设置属性，再调用NDialog的方法设置Dialog的属性，最后 .create().show();
```

**ConfirmDialog**

```
 确认提示框，可设置的属性
 title     //可设置title的字体大小、颜色、样式、padding等 设置显示，不设置不显示
 message   //可设置message的字体大小、颜色、样式、padding等
 isIos     //ios样式的弹窗 预先设置了ios弹窗的属性，但可以set对应属性改变，可设置分割线宽度，颜色等
 
 完整调用如下
 new ConfirmDialog(this,false)    
                .setTtitle()
                .setTitleSize()
                .setTitleColor()
                .setMessage()
                .setMessageSize()
                .setMessageColor()
                .setPositiveButton()
                .setIosDividerColor()
                .setIosDividerSize()
                .setPositiveButtonSize()
                .setPositiveButtonColor()
                .setNegativeButton()
                .setNegativeButtonSize()
                .setNegativeButtonColor()
                .setDialogWidth()
                .setDialogHeight()
                .setDialogCornersRadius()
                .setWindowAnimation()
                .setIsFromBottom()
                .setCancelable()
                .setDimAmount()
                .create()
                .show();
          
```
**ChoiceDialog**
```
选择框 可设置的属性
title      //可设置title的字体大小、颜色、样式、padding等，设置显示，不设置不显示
items      //Object[]显示toString（）的内容 可设置itemText字体大小、颜色、位置、样式等，可设置item高度、padding等
isIos      //ios样式的选择框 预先设置了ios选择框的属性，但可以set对应属性改变，可设置分割线宽度，颜色等
divider    //分割线颜色 高度等

 完整调用如下
 new ChoiceDialog(this,false)
                .setTtitle()
                .setTitleSize()
                .setTitleColor()
                .setTitleGravity()
                .setTtitlePadding()
                .setTitleTypeface()
                .setTitleMaxLines()
                .setItems()
                .setItemTextSize()
                .setItemTextColor()
                .setItemTextPadding()
                .setItemDividerPadding()
                .setCancleButtonText()
                .setItemTextGravity()
                .setItemTextTypeface()
                .setOnItemClickListener()
                .setItemHeight()
                .setDividerHight()
                .setDividerColor()
                .hasCancleButton()
                .setDialogWidth()
                .setDialogHeight()
                .setDialogCornersRadius()
                .setWindowAnimation()
                .setIsFromBottom()
                .setCancelable()
                .setDimAmount()
                .create()
                .show();
                
```

**自定义Dialog**
```
集成自NDialog 实现setDialogDetails(Context context,AlertDialog alertDialog)方法，调用 alertDialog.setContentView(View v)即可根据自己的需求实现dialog，同时可调用NDialog中的方法设置公有属性,如：
public class CustomDialog extends NDialog {
    public CustomDialog(Context context) {
        super(context);
    }
    @Override
    protected void setDialogDetails(Context context,AlertDialog alertDialog) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_custom, null);
        alertDialog.setContentView(inflate);
    }
}

调用：
 new CustomDialog(this)
                .setDialogCornersRadius(5f)
                .setDialogHeight((int) Util.dp2px(this, 100))
                .setDialogWidth((int) Util.dp2px(this, 100))
                .create().show();

```

### 具体参见 http://blog.csdn.net/y12345654321/article/details/72639106
