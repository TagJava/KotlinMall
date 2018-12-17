package com.kotlin.user.ui.activity

import android.os.Bundle
import android.view.View
import com.kotlin.base.common.AppManager
import com.kotlin.base.ext.enable
import com.kotlin.base.ext.onClick
import com.kotlin.base.ui.activity.BaseMvpActivity
import com.kotlin.user.R
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.model.UserModule
import com.kotlin.user.presenter.view.ForgetPwdPresenter
import com.kotlin.user.presenter.view.ForgetPwdView
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.toast

/**
 * 忘记密码
 *
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdView, View.OnClickListener {

    fun isBtnEnable(): Boolean {
        return mMobileForgetEt.text.isNullOrEmpty().not() &&
                mVerifyCodeForgetPwdEt.text.isNullOrEmpty().not()
    }

    private var pressTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()
    }

    fun initView() {
        mNextBtn.enable(mMobileForgetEt,{isBtnEnable()})
        mNextBtn.enable(mVerifyCodeForgetPwdEt,{isBtnEnable()})

        mNextBtn.onClick(this)
        mVerifyCodeForgetPwdBtn.onClick(this)
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerUserComponent.builder().activityComponent(activityComponent)
                .userModule(UserModule()).build().inject(this)
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

    /*
        点击事件
     */
    override fun onClick(view: View) {
        when(view.id){
            R.id.mVerifyCodeForgetPwdBtn -> {
                mVerifyCodeForgetPwdBtn.requestSendVerifyNumber()
                toast("发送验证成功")
            }

            R.id.mNextBtn -> {
                mPresenter.forgetPwd(mMobileForgetEt.text.toString(),
                        mVerifyCodeForgetPwdEt.text.toString())
            }
        }
    }

    override fun onForgetPwdResult(result: String) {
        toast(result)
    }


}
