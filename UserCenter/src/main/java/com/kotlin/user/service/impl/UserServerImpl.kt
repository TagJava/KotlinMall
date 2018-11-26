package com.kotlin.user.service.impl

import com.kotlin.base.rx.BaseFuncBoolean
import com.kotlin.user.data.repository.UserRepository
import com.kotlin.user.service.UserServer
import rx.Observable
import javax.inject.Inject

class UserServerImpl @Inject constructor(): UserServer {
    @Inject
    lateinit var repository: UserRepository

    override fun register(mobile: String, pwd: String, veriftyCode: String): Observable<Boolean> {
        val repository = UserRepository()

        return repository.register(mobile, pwd, veriftyCode)
                .flatMap(BaseFuncBoolean())
    }
}