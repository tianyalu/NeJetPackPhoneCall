package com.sty.ne.jetpack.phonecall.jetpack_app;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

/**
 * 需要环境：AndroidViewModel
 * 需要环境：ViewModel
 * @Author: tian
 * @UpdateDate: 2020/11/9 10:36 PM
 */
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
