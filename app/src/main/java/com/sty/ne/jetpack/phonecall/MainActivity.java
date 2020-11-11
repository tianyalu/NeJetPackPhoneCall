package com.sty.ne.jetpack.phonecall;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sty.ne.jetpack.phonecall.utils.PermissionUtils;

/**
 * TODO 传统方式的  Activity做的事情太多了，什么都要Activity来管，后期维护很痛苦
 *
 * 功能越多，下面的缺点就越大
 *
 * 1.Activity 或 Fragment 是大管家，代码脓肿
 *
 * 2.Activity 要处理逻辑
 *
 * 3.Activity 要处理Model数据  UI 数据， 不仅管理了 却又管不好（横竖屏切换 数据丢失）
 *
 * 4.Activity 要实时刷新UI，例如：更新TextView
 *
 * 5.Activity 如果横竖屏切换 会丢失数据（号码数据一定会丢失的）
 *
 * 6.焊死
 *
 * ...... 等等
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private final static String TAG = MainActivity.class.getSimpleName();
    private String[] needPermissions = { Manifest.permission.CALL_PHONE};

    private TextView phone; // 显示的号码  UI的　数据
    private Button number1; // 键盘按键
    private Button number2; // 键盘按键

    private Button number3; // 键盘按键
    private Button number4;// 键盘按键
    private Button number5; // 键盘按键

    private Button number6; // 键盘按键
    private Button number7; // 键盘按键
    private Button number8; // 键盘按键

    private Button number9; // 键盘按键
    private Button numberXin; // 键盘按键 *
    private Button number0; // 键盘按键0

    private Button numberJin; // 键盘按键 #
    private Button numberMin; // 键盘按键 清空
    private ImageView numberCall; // call

    private Button numberBackspace; // 删除

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        requestPermissions();
    }

    private void initView() {
        phone = findViewById(R.id.tv_phone);
        number1 = findViewById(R.id.bt_number1);
        number2 = findViewById(R.id.bt_number2);

        number3 = findViewById(R.id.bt_number3);
        number4 = findViewById(R.id.bt_number4);
        number5 = findViewById(R.id.bt_number5);

        number6 = findViewById(R.id.bt_number6);
        number7 = findViewById(R.id.bt_number7);
        number8 = findViewById(R.id.bt_number8);

        number9 = findViewById(R.id.bt_number9);
        numberXin = findViewById(R.id.bt_number_xin); // *
        number0 = findViewById(R.id.bt_number0);

        numberJin = findViewById(R.id.bt_number_jin);
        numberMin = findViewById(R.id.bt_number_min); // 清空
        numberCall = findViewById(R.id.iv_number_call); // 拨打

        numberBackspace = findViewById(R.id.bt_number_backspace); // 删除

        number1.setOnClickListener(this);
        number2.setOnClickListener(this);
        number3.setOnClickListener(this);
        number4.setOnClickListener(this);
        number5.setOnClickListener(this);
        number6.setOnClickListener(this);
        number7.setOnClickListener(this);
        number8.setOnClickListener(this);
        number9.setOnClickListener(this);
        numberXin.setOnClickListener(this);
        number0.setOnClickListener(this);

        numberJin.setOnClickListener(this);
        numberMin.setOnClickListener(this);
        numberCall.setOnClickListener(this);

        numberBackspace.setOnClickListener(this);
    }

    /**
     * 点击
     * @param view
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.bt_number1:
                appendNumber("1");
                break;

            case R.id.bt_number2:
                appendNumber("2");
                break;

            case R.id.bt_number3:
                appendNumber("3");
                break;

            case R.id.bt_number4:
                appendNumber("4");
                break;

            case R.id.bt_number5:
                appendNumber("5");
                break;

            case R.id.bt_number6:
                appendNumber("6");
                break;

            case R.id.bt_number7:
                appendNumber("7");
                break;

            case R.id.bt_number8:
                appendNumber("8");
                break;

            case R.id.bt_number9:
                appendNumber("9");
                break;

            case R.id.bt_number_xin:
                appendNumber("*");
                break;

            case R.id.bt_number0:
                appendNumber("0");
                break;

            case R.id.bt_number_jin:
                appendNumber("#");
                break;

            case R.id.bt_number_min:
                clear();
                break;

            case R.id.iv_number_call: // 拨打
                callPhone();
                break;

            case R.id.bt_number_backspace:
                backspaceNumber();
                break;

            default:
                break;
        }

    }

    // 传统方式的 数据
    private String phoneInfo = "";

    /**
     * 输入
     * @param number
     */
    private void appendNumber(String number) {

        if (null != phone) {
            phoneInfo += number; // 弊端 性能 sb去完成
            Log.e(TAG, "phoneInfo:" + phoneInfo);
            phone.setText(phoneInfo + "");
        }
    }

    /**
     * 删除
     */
    private void backspaceNumber() {
        int length = phoneInfo.length();
        if (length > 0) {
            phoneInfo = phoneInfo.substring(0, phoneInfo.length() - 1);
            phone.setText(phoneInfo + "");
        }
    }

    /**
     * 清空
     */
    private void clear() {
        phoneInfo = "";
        phone.setText("");
    }

    /**
     * 拨打
     */
    public void callPhone() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneInfo));
        this.startActivity(intent);
    }

    private void requestPermissions() {
        if (!PermissionUtils.checkPermissions(this, needPermissions)) {
            PermissionUtils.requestPermissions(this, needPermissions);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PermissionUtils.REQUEST_PERMISSIONS_CODE) {
            if (!PermissionUtils.verifyPermissions(grantResults)) {
                PermissionUtils.showMissingPermissionDialog(this);
            } else {

            }
        }
    }

    // 9000行代码
    // ...
}