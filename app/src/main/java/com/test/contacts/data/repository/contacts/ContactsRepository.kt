package com.test.contacts.data.repository.contacts

import android.content.Context
import com.tomash.androidcontacts.contactgetter.entity.ContactData
import com.tomash.androidcontacts.contactgetter.main.FieldType
import com.tomash.androidcontacts.contactgetter.main.contactsGetter.ContactsGetterBuilder

class ContactsRepository(private val context: Context): IContactsRepository {

    private var listOfContacts: List<ContactData>? = null

    override fun getContacts(): List<ContactData> {
        if (listOfContacts == null) {
            val builder = ContactsGetterBuilder(context).addField(FieldType.EMAILS, FieldType.PHONE_NUMBERS)
            listOfContacts = builder.buildList()
        }
        return listOfContacts ?: listOf()
    }
}