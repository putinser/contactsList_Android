package com.test.contacts.screens.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.test.contacts.screens.base.BaseFragmentActivity

class DetailsActivity: BaseFragmentActivity() {

    override fun getFragment(): Fragment {
        return DetailsFragment().also {
            it.arguments = intent.extras
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}