package com.kotlin.mall.ui.activity

import android.os.Bundle
import com.kotlin.base.ui.activity.BaseActivity

import com.kotlin.mall.R
import rx.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

import rx.Observable


class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* mBottomNavBar.checkMsgBadge(false)
        mBottomNavBar.checkMsgBadge(20)
*/


        Observable.timer(2,TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
    }
}
