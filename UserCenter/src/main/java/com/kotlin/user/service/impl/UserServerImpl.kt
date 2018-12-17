package com.kotlin.user.service.impl

import com.kotlin.base.ext.convert
import com.kotlin.base.ext.convertBoolean
import com.kotlin.base.rx.BaseFuncBoolean
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.data.repository.UserRepository
import com.kotlin.user.service.UserServer
import rx.Observable
import javax.inject.Inject

class UserServerImpl @Inject constructor() : UserServer {



    @Inject
    lateinit var repository: UserRepository

    /**
     * 注册
     */
    override fun register(mobile: String, pwd: String, veriftyCode: String): Observable<Boolean> {
        val repository = UserRepository()

        return repository.register(mobile, pwd, veriftyCode)
                .convertBoolean()
    }

    /**
     * 登录
     */
    override fun login(mobile: String, pwd: String, pushId: String): Observable<UserInfo> {
        val repository = UserRepository()

        return repository.login(mobile, pwd, pushId).convert()
    }

    /**
     * 重置密码
     */
    override fun resetPwd(mobile: String, pwd: String): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return repository.resetPwd(mobile,pwd).convertBoolean()
    }

    /**
     * 忘记
     */
    override fun forgetPwd(mobile: String, verifyCode: String): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return repository.forgetPwd(mobile,verifyCode).convertBoolean()
    }
}