package com.test.contacts.screens.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.test.contacts.R
import com.test.contacts.screens.Constants.Companion.CODE_PERMISSION_READ_CONTACTS
import com.test.contacts.screens.Constants.Companion.KEY_LIST_PHONE
import com.test.contacts.screens.Constants.Companion.KEY_NAME
import com.test.contacts.screens.Constants.Companion.KEY_URI
import com.test.contacts.screens.details.DetailsActivity
import com.test.contacts.screens.main.adapter.ContactsAdapter
import com.tomash.androidcontacts.contactgetter.entity.ContactData
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment: MvpAppCompatFragment(), MainView {

    @InjectPresenter
    lateinit var presenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkContactPermission()
        initButtons()
        initRecyclerView()
    }

    private fun checkContactPermission() {
        if (ActivityCompat.checkSelfPermission(context!!, Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            getContacts()
        } else {
            requestPermissions(arrayOf(Manifest.permission.READ_CONTACTS), CODE_PERMISSION_READ_CONTACTS)
        }
    }

    private fun initButtons() {
        contactsRefreshButton.setOnClickListener {
            contactsProgressBar.visibility = View.VISIBLE
            contactsRefreshButton.visibility = View.GONE
            checkContactPermission()
        }
    }

    private fun initRecyclerView() {
        contactsRecyclerView.layoutManager = LinearLayoutManager(context)
        contactsRecyclerView.setHasFixedSize(true)
        contactsRecyclerView.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL).also {
            ContextCompat.getDrawable(context!!, R.drawable.divider)?.let { drawable ->
                it.setDrawable(drawable)
            }
        })
        contactsRecyclerView.adapter = ContactsAdapter(context!!, ::onClickContact)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        when (requestCode) {
            CODE_PERMISSION_READ_CONTACTS -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getContacts()
                } else {
                    showProgressBar(false)
                    contactsRefreshButton.visibility = View.VISIBLE
                }
                return
            }
        }
    }

    private fun getContacts() {
        presenter.getContacts()
    }

    override fun showProgressBar(show: Boolean) {
        contactsProgressBar.visibility = if (show) View.VISIBLE else View.GONE
    }

    override fun showContacts(contacts: List<ContactData>) {
        (contactsRecyclerView.adapter as ContactsAdapter).contacts = contacts
        showProgressBar(false)
    }

    private fun onClickContact(contactData: ContactData, position: Int) {
        startActivity(Intent(context, DetailsActivity::class.java).also {
            it.putExtra(KEY_NAME, contactData.compositeName)
            it.putExtra(KEY_URI, contactData.photoUri)
            val phones = ArrayList<String>()
            contactData.phoneList?.forEach { phone -> phones.add(phone.mainData) }
            it.putStringArrayListExtra(KEY_LIST_PHONE, phones)
        })
    }

}