package com.admin_official.contacts

import android.annotation.SuppressLint
import android.app.Application
import android.database.Cursor
import android.provider.ContactsContract
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData


class AppViewModel(application: Application): AndroidViewModel(application) {

    val contacts = MutableLiveData<List<Contact>>()
//    private val context = getApplication<Application>().applicationContext

    init {

        val projection = arrayOf(ContactsContract.Contacts._ID,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.HAS_PHONE_NUMBER)

        val cursor = application.contentResolver.query(
            ContactsContract.Contacts.CONTENT_URI,
            projection,
            null,
            null,
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY)

        var newContacts = mutableListOf<Contact>()

        cursor.use {crsr ->
            if(crsr != null) {
                while(crsr.moveToNext()) {
                    val id = crsr.getString(0);
                    val nContact = Contact(crsr.getString(1))
                    val hasphone = crsr.getString(2)

                    if(!hasphone.endsWith("0")) {
                        nContact.phoneNumber = getPhoneNumber(id)
                    }

                    newContacts.add(nContact);
                }
            }
        }
    }


    private fun getPhoneNumber(id: String): String {
        var number = ""

        val phones: Cursor? = getApplication<Application>().applicationContext.contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER),
            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
            null,
            null
        )

        phones.use {crsr ->
            if(crsr != null) {
                while (crsr.moveToNext()) {
                    number = crsr.getString(0)
                }
                return number
            }

        }

        return "Not Saved"
    }
}