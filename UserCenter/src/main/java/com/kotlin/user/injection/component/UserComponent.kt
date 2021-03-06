package com.kotlin.user.injection.component

import com.kotlin.base.injection.PerComponentScope
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.user.injection.model.UserModule
import com.kotlin.user.ui.activity.*
import dagger.Component

@PerComponentScope
@Component(dependencies = arrayOf(ActivityComponent::class),
        modules = arrayOf(UserModule::class))
interface UserComponent{
    fun inject(activity: RegisterActivity)
    fun inject(activity: LoginActivity)
    fun inject(forgetPwdActivity: ForgetPwdActivity)
    fun inject(userInfoActivity: UserInfoActivity)
    fun inject(userInfoActivity: ResetPwdActivity)
}