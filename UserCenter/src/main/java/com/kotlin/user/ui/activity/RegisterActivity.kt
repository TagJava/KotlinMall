package com.kotlin.user.ui.activity

import android.os.Bundle
import com.kotlin.base.common.AppManager
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.model.UserModule
import com.kotlin.user.presenter.view.RegisterPresenter
import com.kotlin.user.presenter.view.RegisterView
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterView{
    private var pressTime:Long = 0

    override fun onRegisterResult(result: String) {
        toast(result)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initInjection()

        btn_register.setOnClickListener{
           mPresenter.register(mMobileEt.text.toString(),mVerifyCodeEt.text.toString(),
                   mPwdEt.text.toString())
        }
    }

    private fun initInjection() {

        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build()
                .inject(this)
        mPresenter.mView = this
    }


    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if(time - pressTime > 2000){
            toast("再按一次退出程序")
            pressTime = time
        }else{
            AppManager.instance.exitApp(this)
        }
    }


}
