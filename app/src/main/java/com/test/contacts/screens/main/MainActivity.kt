package com.test.contacts.screens.main

import androidx.fragment.app.Fragment
import com.test.contacts.screens.base.BaseFragmentActivity

class MainActivity: BaseFragmentActivity() {

    override fun getFragment(): Fragment {
        return MainFragment()
    }

}