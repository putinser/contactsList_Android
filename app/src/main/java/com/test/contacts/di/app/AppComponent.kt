package com.test.contacts.di.app

import com.test.contacts.data.repository.contacts.IContactsRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        RepositoryModule::class]
)
interface AppComponent {

    fun provideContactsRepository(): IContactsRepository

}