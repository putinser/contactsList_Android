package com.test.contacts.di

import android.content.Context
import com.test.contacts.di.app.AppComponent
import com.test.contacts.di.app.AppModule
import com.test.contacts.di.app.DaggerAppComponent
import com.test.contacts.di.app.RepositoryModule

object DaggerUtils {

    lateinit var appComponent: AppComponent

    @JvmStatic
    fun buildComponents(context: Context) {
        appComponent = buildAppComponent(context)
    }

    private fun buildAppComponent(context: Context) = DaggerAppComponent.builder()
        .appModule(AppModule())
        .repositoryModule(RepositoryModule(context))
        .build()

}