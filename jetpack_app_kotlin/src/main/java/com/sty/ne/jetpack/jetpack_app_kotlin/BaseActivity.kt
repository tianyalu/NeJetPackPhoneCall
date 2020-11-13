package com.sty.ne.jetpack.jetpack_app_kotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

/**
 * @Author: tian
 * @UpdateDate: 2020/11/12 9:52 PM
 */
abstract class BaseActivity : AppCompatActivity(), ICallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(getLayoutID())
        initViewAction()
        initDataAction()
    }

    abstract fun getLayoutID() : Int

    abstract fun initViewAction() : Unit

    abstract fun initDataAction() : Unit

    abstract fun initListenerAction() : Unit

}