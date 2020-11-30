package com.test.contacts.screens.details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.test.contacts.R
import com.test.contacts.screens.Constants.Companion.KEY_LIST_PHONE
import com.test.contacts.screens.Constants.Companion.KEY_NAME
import com.test.contacts.screens.Constants.Companion.KEY_URI
import com.test.contacts.utils.showInImageView
import kotlinx.android.synthetic.main.fragment_details.*
import kotlinx.android.synthetic.main.item_phone.view.*

class DetailsFragment: MvpAppCompatFragment(), DetailsView {

    @InjectPresenter
    lateinit var presenter: DetailsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        contactNameTextView.text = arguments?.getString(KEY_NAME)
        arguments?.getParcelable<Uri?>(KEY_URI).showInImageView(contactImageView)
        arguments?.getStringArrayList(KEY_LIST_PHONE)?.forEach { ph ->
            val view = layoutInflater.inflate(R.layout.item_phone, null)
            view.phoneTextView.text = ph
            phonesLayout.addView(view)
        }
    }
}