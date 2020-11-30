package com.test.contacts.di.app

import android.content.Context
import com.test.contacts.data.repository.contacts.ContactsRepository
import com.test.contacts.data.repository.contacts.IContactsRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideContactsRepository(): IContactsRepository = ContactsRepository(context)

}