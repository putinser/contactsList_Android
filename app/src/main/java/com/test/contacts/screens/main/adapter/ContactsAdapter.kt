package com.test.contacts.screens.main.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.test.contacts.R
import com.test.contacts.utils.showInImageView
import com.tomash.androidcontacts.contactgetter.entity.ContactData
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactsAdapter(private val context: Context, private val onClickContact: (ContactData, Int) -> Unit):
    RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    var contacts: List<ContactData>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false)
        val vh = ContactViewHolder(view)
        vh.itemView.setOnClickListener {
            contacts?.get(vh.adapterPosition)?.let { contact ->
                onClickContact.invoke(contact, vh.adapterPosition)
            }
        }
        return vh
    }

    override fun getItemCount(): Int {
        return contacts?.size ?: 0
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        contacts?.get(position)?.let {
            holder.setContactData(it)
        }
    }

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun setContactData(contactData: ContactData) {
            itemView.contactNameTextView.text = contactData.compositeName
            contactData.photoUri.showInImageView(itemView.contactImageView)
            if (contactData.phoneList.isNotEmpty()) {
                itemView.contactMainNumberTextView.text = contactData.phoneList.first().mainData
            } else {
                itemView.contactMainNumberTextView.text = ""
            }
        }
    }

}