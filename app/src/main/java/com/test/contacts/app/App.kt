package com.test.contacts.app

import android.app.Application
import android.content.Context
import com.test.contacts.di.DaggerUtils

class App: Application() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerUtils.buildComponents(this)
    }
}