package com.kotlin.user.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.kotlin.base.common.AppManager
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.model.UserModule
import com.kotlin.user.presenter.view.LoginPresenter
import com.kotlin.user.presenter.view.LoginView
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

/**
 * 登录界面
 */
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginView, View.OnClickListener {


    fun isBtnEnable(): Boolean {
        return mLoginMobileEt.text.isNullOrEmpty().not() &&
                mLoginPwdEt.text.isNullOrEmpty().not()
    }

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    fun initView() {
        mLoginBtn.enable(mLoginMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mLoginPwdEt, { isBtnEnable() })
        mLoginBtn.onClick (this)

        mHeaderLoginBar.getRightView().onClick(this)
        mForgetPwdTv.onClick(this)
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent).userModule(UserModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onBackPressed() {
        val time = System.currentTimeMillis()
        if (time - pressTime > 2000) {
            toast("再按一次退出程序")
            pressTime = time
        } else {
            AppManager.instance.exitApp(this)
        }
    }

    /*override fun onClick(view: View) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        when (view.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证成功")
            }
            R.id.mRegisterBtn -> {
                mPresenter.register(mMobileEt.text.toString(), mVerifyCodeEt.text.toString(),
                        mPwdEt.text.toString())
            }
        }
    }*/

    /*
        点击事件
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.mRightTv -> {
                val intent = Intent()
                intent.setClass(this,RegisterActivity::class.java)
                startActivity(intent)
            }
            R.id.mLoginBtn -> {
                mPresenter.login(mLoginMobileEt.text.toString(), mLoginPwdEt.text.toString(), "")
            }
            R.id.mForgetPwdTv ->{
                val intent = Intent()
                intent.setClass(this,ForgetPwdActivity::class.java)
                startActivity(intent)
            }
        }
    }

    override fun onLoginResult(result: UserInfo) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        toast("登录成功")
    }

}
