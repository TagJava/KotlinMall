package com.kotlin.base.common

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import java.util.*

class AppManager private constructor(){
    private val activityStack:Stack<Activity> = Stack()

    /**类内部的对象可以用companion关键字标记*/
    companion object {
        val instance:AppManager by lazy { AppManager() }
    }

    /**
     * 入栈
     */
    fun addActivity(activity: Activity){
        activityStack.add(activity)
    }

    /**
     * 出栈
     */
    fun finishActivty(activity: Activity){
        activity.finish()
        activityStack.remove(activity)
    }

    /**
     * 获取当前栈顶
     */
    fun currentActivity():Activity{
        return activityStack.lastElement()
    }

    /***
     * 清理栈
     */
    fun finishAllActivity(){
        for (activity in activityStack){
            activity.finish()
        }
        activityStack.clear()
    }

    /**
     * 退出App
     */
    fun exitApp(context:Context){
        finishAllActivity()
        val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE)
                as ActivityManager
        activityManager.killBackgroundProcesses(context.packageName)
        System.exit(0)

    }

}