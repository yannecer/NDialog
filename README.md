# NDialog
使用Builder模式设置字体大小、颜色、位置的属性


## 效果图
|MaterialDesign确认|Ios确认|MaterialDesign选择1|
|:---:|:---:|:---:|
|![](https://github.com/yannecer/NDialog/blob/master/app/png/MaterialDesign%E7%A1%AE%E8%AE%A4.png)|![](https://github.com/yannecer/NDialog/blob/master/app/png/ios%E7%A1%AE%E8%AE%A4.png)||![](https://github.com/yannecer/NDialog/blob/master/app/png/MaterialDesign%E9%80%89%E6%8B%A91.png)|


## 使用方法

## Gradle

```
compile 'com.necer.ndialog2:ndialog:1.0.1'
```

**确认提示框**
```
new NDialog(this)
                .setTitle("我是标题")
                .setTitleColor(Color.parseColor("#00c8aa"))
                .setTitleSize(18)
                .setTitleCenter(false)
                .setMessageCenter(false)
                .setMessage("我是meaasge")
                .setMessageSize(16)
                .setMessageColor(Color.parseColor("#00ff00"))
                .setNegativeTextColor(Color.parseColor("#000000"))
                .setPositiveTextColor(Color.parseColor("#ff0000"))
                .setButtonCenter(false)
                .setButtonSize(14)
                .setCancleable(true)
                .setOnConfirmListener(new NDialog.OnConfirmListener() {
                    @Override
                    public void onClick(int which) {
                        //which,0代表NegativeButton，1代表PositiveButton

                        Toast.makeText(MainActivity.this, "点击了：：" + which, Toast.LENGTH_SHORT).show();

                    }
                }).create(NDialog.CONFIRM).show();

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
