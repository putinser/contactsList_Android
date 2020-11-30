package com.test.contacts.data.repository.contacts

import com.tomash.androidcontacts.contactgetter.entity.ContactData

interface IContactsRepository {

    fun getContacts(): List<ContactData>

}