package com.kotlin.base.ui.activity

import android.os.Bundle
import com.kotlin.base.common.BaseApplication
import com.kotlin.base.injection.component.ActivityComponent
import com.kotlin.base.injection.component.DaggerActivityComponent
import com.kotlin.base.injection.module.ActivityModule
import com.kotlin.base.injection.module.LifecycleProviderModule
import com.kotlin.base.presenter.view.BasePresenter
import com.kotlin.base.presenter.view.BaseView
import com.kotlin.base.widgets.ProgressLoading
import javax.inject.Inject

open class BaseMvpActivity<T:BasePresenter<*>>:BaseActivity(),BaseView {
    override fun showLoading() {
        mLoadingDialog.showLoading()
    }

    override fun hideLoading() {
        mLoadingDialog.hideLoading()
    }

    override fun onError() {

    }

    lateinit var activityComponent: ActivityComponent

    @Inject
    lateinit var mPresenter: T

    lateinit var mLoadingDialog:ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initActivityInjection()
        mLoadingDialog = ProgressLoading.create(this)
    }

    private fun initActivityInjection() {
        activityComponent = DaggerActivityComponent.builder().appComponent((application
        as BaseApplication).appComponent).activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this)).build()
    }
}