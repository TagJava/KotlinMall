package com.kotlin.user.injection.model

import com.kotlin.user.service.UserServer
import com.kotlin.user.service.impl.UserServerImpl
import dagger.Module
import dagger.Provides

@Module
class UserModule{

    @Provides
    fun providesUserService(service:UserServerImpl):UserServer{
        return service
    }
}