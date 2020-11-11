# 一个应用JetPack的拨打电话号码的综合示例

[TOC]

## 一、理论

本文采取了传统模式和`JetPack`两种方式实现了一个电话拨号器的示例。

### 1.1 传统方式与`JetPack`方式对比

传统方式：

> 1. 违背单一原则（因为职责过多）；
> 2. 代码臃肿，不便管理；
> 3. 非常依赖`Activity`（什么都要`Activity`来管）。

`JetPack`方式：

> 1. 体现了单一原则；
> 2. 分层清晰；
> 3. 不需要非常依赖`Activity`。
> 4. 用`ViewModel`保存数据，可以避免`Activity`横竖屏切换时数据丢失的问题。

### 1.2 `JetPack`层次结构

第一层：管控层`Activity`；

第二层：`UI`数据`Model`层；

第三层：`UI View`布局层；

关联：管控层关联起来

## 二、实操

### 2.1 `JetPack`方式

### 2.1 `View`层

布局文件：

```xml
<?xml version="1.0" encoding="utf-8"?>
<layout xmlns:android="http://schemas.android.com/apk/res/android">

    <!-- 数据区 -->
    <data>
        <variable
            name="vm"
            type="com.sty.ne.jetpack.phonecall.jetpack_app.MainViewModel" />
    </data>

    <LinearLayout
        android:layout_width="fill_parent"
        android:layout_height="fill_parent"
        android:orientation="vertical"
        android:background="@drawable/phone2_bg">
        <LinearLayout
            android:layout_width="fill_parent"
            android:layout_height="0dip"
            android:layout_weight="1" />
        <!-- 电话号码 -->
        <TextView
            android:id="@+id/tv_phone"
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:textSize="@dimen/activity_phone_tv"
            android:text="@{vm.phoneInfo}"
            android:gravity="center"
            android:textStyle="bold" />

        <!-- 表格布局 -->
        <TableLayout
            android:layout_width="fill_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentBottom="true"
            android:layout_marginBottom="16dip">

            <TableRow
                android:layout_width="fill_parent"
                android:layout_height="wrap_content">
                <Button
                    android:id="@+id/bt_number1"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/phone1"
                    android:textSize="@dimen/activity_phone_bt"
                    android:onClick="@{()->vm.appendNumber(String.valueOf(1))}"
                    android:background="@drawable/phone_selector_number" />
                <Button
                    android:id="@+id/bt_number2"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/phone2"
                    android:textSize="@dimen/activity_phone_bt"
                    android:onClick="@{()->vm.appendNumber(String.valueOf(2))}"
                    android:background="@drawable/phone_selector_number" />
                <Button
                    android:id="@+id/bt_number3"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/phone3"
                    android:textSize="@dimen/activity_phone_bt"
                    android:onClick="@{()->vm.appendNumber(String.valueOf(3))}"
                    android:background="@drawable/phone_selector_number" />
            </TableRow>

            <!-- 第二行 -->
            <TableRow
                android:layout_width="fill_parent"
                android:layout_height="wrap_content">
                <Button
                    android:id="@+id/bt_number4"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/phone4"
                    android:textSize="@dimen/activity_phone_bt"
                    android:onClick="@{()->vm.appendNumber(String.valueOf(4))}"
                    android:background="@drawable/phone_selector_number" />
                <Button
                    android:id="@+id/bt_number5"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/phone5"
                    android:textSize="@dimen/activity_phone_bt"
                    android:onClick="@{()->vm.appendNumber(String.valueOf(5))}"
                    android:background="@drawable/phone_selector_number" />
                <Button
                    android:id="@+id/bt_number6"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/phone6"
                    android:textSize="@dimen/activity_phone_bt"
                    android:onClick="@{()->vm.appendNumber(String.valueOf(6))}"
                    android:background="@drawable/phone_selector_number" />
            </TableRow>

            <!-- 第3行 -->
            <TableRow
                android:layout_width="fill_parent"
                android:layout_height="wrap_content">
                <Button
                    android:id="@+id/bt_number7"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/phone7"
                    android:textSize="@dimen/activity_phone_bt"
                    android:onClick="@{()->vm.appendNumber(String.valueOf(7))}"
                    android:background="@drawable/phone_selector_number" />
                <Button
                    android:id="@+id/bt_number8"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/phone8"
                    android:textSize="@dimen/activity_phone_bt"
                    android:onClick="@{()->vm.appendNumber(String.valueOf(8))}"
                    android:background="@drawable/phone_selector_number" />
                <Button
                    android:id="@+id/bt_number9"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/phone9"
                    android:textSize="@dimen/activity_phone_bt"
                    android:onClick="@{()->vm.appendNumber(String.valueOf(9))}"
                    android:background="@drawable/phone_selector_number" />
            </TableRow>

            <!-- 第4行 -->
            <TableRow
                android:layout_width="fill_parent"
                android:layout_height="wrap_content">
                <Button
                    android:id="@+id/bt_number_xin"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/phonexin"
                    android:textSize="@dimen/activity_phone_bt"
                    android:onClick="@{()->vm.appendNumber(@string/phonexin)}"
                    android:background="@drawable/phone_selector_number" />

                <Button
                    android:id="@+id/bt_number0"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/phone0"
                    android:textSize="@dimen/activity_phone_bt"
                    android:onClick="@{()->vm.appendNumber(String.valueOf(0))}"
                    android:background="@drawable/phone_selector_number" />
                <Button
                    android:id="@+id/bt_number_jin"
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:text="@string/phonejin"
                    android:textSize="@dimen/activity_phone_bt"
                    android:onClick="@{()->vm.appendNumber(@string/phonejin)}"
                    android:background="@drawable/phone_selector_number" />
            </TableRow>

            <!-- 第5行 -->
            <TableRow
                android:layout_width="fill_parent"
                android:layout_height="wrap_content"
                android:layout_marginTop="6dip">

                <LinearLayout
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:orientation="vertical">
                    <Button
                        android:id="@+id/bt_number_min"
                        android:layout_width="40dp"
                        android:layout_height="40dp"
                        android:textSize="@dimen/activity_phone_bt"
                        android:background="@drawable/phone_selector_min"
                        android:layout_gravity="center"
                        android:onClick="@{()->vm.clear()}"
                        android:layout_margin="6dip" />
                </LinearLayout>

                <LinearLayout
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:orientation="vertical">
                    <ImageView
                        android:id="@+id/iv_number_call"
                        android:layout_width="46dip"
                        android:layout_height="46dip"
                        android:src="@drawable/phone_selector_call"
                        android:onClick="@{()->vm.callPhone()}"
                        android:layout_gravity="center" />

                </LinearLayout>

                <LinearLayout
                    android:layout_width="fill_parent"
                    android:layout_height="wrap_content"
                    android:layout_weight="1"
                    android:orientation="vertical">
                    <Button
                        android:id="@+id/bt_number_backspace"
                        android:layout_width="60dp"
                        android:layout_height="wrap_content"
                        android:textSize="@dimen/activity_phone_bt"
                        android:background="@drawable/phone_selector_backspace"
                        android:onClick="@{()->vm.backspaceNumber()}"
                        android:layout_gravity="center" />
                </LinearLayout>

            </TableRow>

        </TableLayout>

    </LinearLayout>
</layout>
```

`Activity`:

```java
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        //绑定管理操作
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //没有任何效果的实例化
        //viewModel = new MainViewModel(getApplication());

        //实例化(只适合 extends ViewModel)
        //viewModel = new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory()).get(MainViewModel.class);

        //实例化（适合 extends AndroidViewModel）
        viewModel = new ViewModelProvider(this, new ViewModelProvider.AndroidViewModelFactory(getApplication())).get(MainViewModel.class);
        //绑定ViewModel
        binding.setVm(viewModel);
        //建立感应绑定，如果没有的话就无法感应
        binding.setLifecycleOwner(this);
    }
}
```

### 2.2 `ViewModel`层

`MainViewModel`：

```java
public class MainViewModel extends AndroidViewModel {
    //数据，没有感应，无法更新到控件中
    //private String phoneInfo = "":

    //有感应的
    private MutableLiveData<String> phoneInfo;

    //定义环境
    private Context context;

    public MainViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
    }

    //对外暴露
    public MutableLiveData<String> getPhoneInfo() {
        if(phoneInfo == null) {
            phoneInfo = new MutableLiveData<>();
            phoneInfo.setValue(""); //默认值
        }
        return phoneInfo;
    }

    /**
     * 输入
     * @param number
     */
    public void appendNumber(String number) {
        phoneInfo.setValue(phoneInfo.getValue() + number);
    }

    /**
     * 删除
     */
    public void backspaceNumber() {
        int length = phoneInfo.getValue().length();
        if(length > 0) {
            phoneInfo.setValue(phoneInfo.getValue().substring(0, length - 1));
        }
    }

    /**
     * 清空
     */
    public void clear() {
        phoneInfo.setValue("");
    }

    public void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneInfo.getValue()));
        //非Activity中启动 隐式意图
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
```

