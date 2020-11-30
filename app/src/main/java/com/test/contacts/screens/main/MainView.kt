package com.test.contacts.screens.main

import com.arellomobile.mvp.MvpView
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.tomash.androidcontacts.contactgetter.entity.ContactData

interface MainView: MvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showContacts(contacts: List<ContactData>)

    @StateStrategyType(AddToEndStrategy::class)
    fun showProgressBar(show: Boolean)
}