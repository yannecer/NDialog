# NDialog
链式调用设置Dialog字体大小、颜色、位置等属性


## 效果图
![](https://github.com/yannecer/NDialog/blob/master/app/dialog2.gif)

## 使用方法

## Gradle

```
implementation 'com.necer.ndialog2:ndialog:1.1.2'
```

## 具体用法：
[直达MainActivity](https://github.com/yannecer/NDialog/blob/master/app/src/main/java/com/necer/ndialogsample/MainActivity.java)

## demo：
[下载demo](https://github.com/yannecer/NDialog/releases/download/1.1.2/app-debug.apk)


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


继承自 NDialog 的 Dialog 需先调用自身的方法设置属性，再调用 NDialog 的方法设置 Dialog 的属性，最后 .create().show();

```

**ConfirmDialog**

```
确认提示框，可设置的属性
 
title     //可设置 title 的字体大小、颜色、样式、padding 等 设置显示，不设置不显示
message   //可设置 message 的字体大小、颜色、样式、padding 等
isIos     //ios 样式的弹窗 预先设置了 ios 弹窗的属性，但可以 set 对应属性改变，可设置分割线宽度，颜色等
 
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

title      //可设置 title 的字体大小、颜色、样式、padding 等，设置显示，不设置不显示
items      //Object[] 显示 toString（）的内容 可设置 itemText 字体大小、颜色、位置、样式等，可设置 item 高度、padding 等
isIos      //ios样式的选择框 预先设置了 ios 选择框的属性，但可以 set 对应属性改变，可设置分割线宽度，颜色等
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
继承自 NDialog 实现 setDialogDetails(Context context,AlertDialog alertDialog) 方法，
调用 alertDialog.setContentView(View v) 即可根据自己的需求实现 dialog ，
同时可调用 NDialog 中的方法设置公有属性,如：

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
