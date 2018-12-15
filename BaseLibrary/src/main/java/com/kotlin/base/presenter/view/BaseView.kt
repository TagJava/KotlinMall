package com.kotlin.base.presenter.view

import android.os.Bundle

interface BaseView{
    fun showLoading()
    fun hideLoading()
    fun onError(text:String)
}