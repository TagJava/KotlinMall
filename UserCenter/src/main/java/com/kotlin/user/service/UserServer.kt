package com.kotlin.user.service

import com.kotlin.user.data.protocol.UserInfo
import rx.Observable

interface UserServer {
    fun register(mobile:String,pwd:String,veriftyCode:String):Observable<Boolean>

    fun login(mobile:String,pwd:String,pushId:String):Observable<UserInfo>

    fun forgetPwd(mobile:String,verifyCode:String):Observable<Boolean>

    fun resetPwd(mobile:String,pwd:String):Observable<Boolean>
}