package com.kotlin.base.presenter.view

open class BasePresenter<T:BaseView> {
    lateinit var mView:T

}