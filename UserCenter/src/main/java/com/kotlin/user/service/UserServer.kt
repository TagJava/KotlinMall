package com.kotlin.user.service

import rx.Observable

interface UserServer {
    fun register(mobile:String,pwd:String,veriftyCode:String):Observable<Boolean>
}