package com.kotlin.user.presenter.view

import com.kotlin.base.ext.execute
import com.kotlin.base.presenter.view.BasePresenter
import com.kotlin.base.rx.BaseSubscriber
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.service.UserServer
import com.kotlin.user.service.impl.UserServerImpl
import rx.Subscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject
import javax.net.ssl.HostnameVerifier

open class LoginPresenter @Inject constructor() : BasePresenter<LoginView>() {

    @Inject
    lateinit var userService: UserServer

    fun login(mobile: String, pwd: String, pushId: String) {
        /**
         * 业务逻辑
         */
        userService.login(mobile, pwd, pushId)
                .execute(object : BaseSubscriber<UserInfo>(mView) {
                    override fun onNext(t: UserInfo) {
                        mView.onLoginResult(t)
                    }
                }, lifecycleProvider)
    }
}