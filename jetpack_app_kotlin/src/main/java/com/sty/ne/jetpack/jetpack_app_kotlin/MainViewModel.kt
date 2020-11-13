package com.sty.ne.jetpack.jetpack_app_kotlin

import android.app.Application
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

/**
 * @Author: tian
 * @UpdateDate: 2020/11/12 10:03 PM
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {
    //懒加载，用到的时候再实例化
    // 只读的 val 只赋值一次
    // var 变量，可赋值多次
    private val phoneInfo by lazy { MutableLiveData<String>() }

    //初始化默认值 ""
    init {
        //当赋值的时候，Kotlin就知道是setValeu
        phoneInfo.value = ""

        //没有赋值的时候，Kotlin就知道是getValue
        //phoneInfo.value
    }

    //对外暴露
    fun getPhoneInfoMethod() : MutableLiveData<String> {
        return phoneInfo
    }

    //定义环境
    val context : Context = application

    /**
     * 输入
     */
    fun appendNumber(number: String) : Unit {
        phoneInfo.value = phoneInfo.value + number //+=number
    }

    /**
     * 删除
     */
    fun backspaceNumber() : Unit {
        //phoneInfo.value.length 报错的原因： 这个value有可能为null，需要补救一下，补救的方式？（如果为null,就不执行后面的代码）
        //补救后为什么还报错？ 因为这个length可能没有值，也需要补救一下，可以给个默认值：0
        val len = phoneInfo.value?.length ?: 0

        if(len > 0) {
            //!!强制性 表示能够保证第二个phoneInfo.value?.length能够拿得到
            phoneInfo.value = phoneInfo.value?.substring(0, phoneInfo.value?.length!! - 1)
        }
    }

    /**
     * 清空
     */
    fun clear() : Unit {
        phoneInfo.value = ""
    }

    /**
     * 拨打
     */
    fun callPhone() : Unit {
        val intent : Intent = Intent()
        intent.action = Intent.ACTION_CALL
        intent.data = Uri.parse("tel:${phoneInfo.value}")
        //非Activity中启动隐式意图 Service广播
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }
}