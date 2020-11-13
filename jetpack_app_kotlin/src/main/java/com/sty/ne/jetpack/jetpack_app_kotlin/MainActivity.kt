package com.sty.ne.jetpack.jetpack_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.sty.ne.jetpack.jetpack_app_kotlin.databinding.ActivityMainBinding

/**
 * : 继承 实现
 * () 构造的概念
 * fun function函数
 * ？ 全体注意，有可能为null
 * Unit void
 */
class MainActivity : BaseActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var vm: MainViewModel

//    override fun getLayoutID(): Int {
//        return R.layout.activity_main;
//    }

    override fun getLayoutID() : Int = R.layout.activity_main


    override fun initViewAction() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        vm = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(MainViewModel::class.java)

        //建立绑定 vm + binding
        binding.vm = vm

        //建立感应关系
        binding.lifecycleOwner = this
    }

    override fun initDataAction() {
        Log.e("sty", "${method(1, 2)}")
    }

    //可以这样写函数
    val method = {number1: Int, number2: Int -> number1 + number2}

    override fun initListenerAction() {

    }

    override fun testCallbackMethod(name: String) {

    }
}
