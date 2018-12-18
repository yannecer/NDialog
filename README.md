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
dialogCornersRadius  //弹窗圆角大小
dialogBgColor  //弹窗背景颜色
dialogWidth  //弹窗宽度，建议依屏幕的宽为参考
dialogHeight  //弹窗高度，建议依屏幕的高为参考
cancleable  //弹窗是否可取消
isFromBottom  //弹窗是否底部弹出
windowAnimation  //弹窗弹出动画资源文件
dimAmount  //弹窗弹出时，窗体的灰度 0.0f-1.0f 0.0f为透明 1.0f为全黑
```

**输入框**

```
  new NDialog(this).setTitle("请输入。。。")
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
```
**选择框**
```
new NDialog(this)
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
                }).create(NDialog.CHOICE).show();
```


支持的属性：


| 属性| 描述|
|:---|:---|
| NDialog.CONFIRM| 构建确认提示弹窗 |
| NDialog.INPUT| 构建输入弹窗 |
| NDialog.CHOICE|构建选择弹窗 |
| positiveButtonText| positive按钮文本 |
| positiveTextColor| positive按钮文本颜色 |
| negativeButtonText| negative按钮文本 |
| negativeTextColor| negative按钮文本颜色 |
| buttonSize| positive和negative按钮大小 |
| isButtonCenter| positive和negative按钮是否居中 |
| message| 确认提示弹窗message文本 |
| messageSize| 确认提示弹窗message字体大小 |
| messageColor| 确认提示弹窗message字体颜色|
| isMessageCenter| 确认提示弹窗message是否居中 |
| title| title文本 |
| titleSize| positive文本大小 |
| titleColor| title文本颜色  |
| isTitleCenter| title是否居中|
| inputText| 输入框预输入文本 |
| inputTextSize| 输入框预输入文本字体大小 |
| inputTextColor| 输入框预输入文本字体颜色 |
| inputHintText| 输入框hint |
| inputHintTextColor| 输入框hint文本字体颜色 |
| inputType| 输入框输入类型 |
| hasDivider| 选择框是否要分割线|
| dividerHeigh| 选择框分割线高度|
| dividerColor| 选择框分割线颜色|
| items| 选择框选择的item|
| itemColor| 选择框item字体颜色|
| itemSize| 选择框item字体大小|
| itemGravity| 选择框item位置|
| itemHeigh| 选择框item高度|
| cancleable| 弹窗是否可取消|

### 具体参见 http://blog.csdn.net/y12345654321/article/details/72639106
