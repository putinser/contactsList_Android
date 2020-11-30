package com.test.contacts.screens.main

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.test.contacts.di.DaggerUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@InjectViewState
class MainPresenter: MvpPresenter<MainView>() {

    private val contactsRepository = DaggerUtils.appComponent.provideContactsRepository()

    fun getContacts() {
        CoroutineScope(Dispatchers.IO).launch {
            val contacts = contactsRepository.getContacts()
            withContext(Dispatchers.Main) {
                viewState.showContacts(contacts)
            }
        }
    }
}