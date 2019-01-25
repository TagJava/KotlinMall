package com.kotlin.user.presenter.view

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.view.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.service.UserServer
import com.kotlin.user.service.impl.UserServerImpl
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.net.ssl.HostnameVerifier

open class ResetPwdPresenter @Inject constructor(): BasePresenter<ResetPwdView>() {

    @Inject
    lateinit var userService:UserServer

    fun forgetPwd(mobile: String, verifyCode: String) {
        /**
         * 业务逻辑
         */
        if (!checkNetWork()){
            return
        }
        mView.showLoading()
        userService.forgetPwd(mobile, verifyCode)
                .execute(object : BaseSubscriber<Boolean>(mView) {
                    override fun onNext(t: Boolean) {
                        if(t){
                            mView.resetPwdResult("验证成功")
                        }
                    }
                },lifecycleProvider)
    }

}