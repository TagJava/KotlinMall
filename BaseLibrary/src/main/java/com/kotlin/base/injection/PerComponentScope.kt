package com.kotlin.base.injection

import javax.inject.Scope
import java.lang.annotation.*
import java.lang.annotation.RetentionPolicy.RUNTIME

@Scope
@Documented
@java.lang.annotation.Retention(RUNTIME)
annotation class PerComponentScope