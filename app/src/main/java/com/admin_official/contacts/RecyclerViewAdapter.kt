package com.admin_official.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.admin_official.contacts.databinding.ContactDetailBinding

class RVViewHolder(private val binding: ContactDetailBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(contact: Contact) {
        binding.Name.text =  contact.name;
        binding.Number.text = contact.phoneNumber
    }
}


class RecyclerViewAdapter(var contacts: List<Contact>, val listener: RVListener): RecyclerView.Adapter<RVViewHolder>() {
    interface RVListener {
        fun onItemClicked(contest: Contact)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RVViewHolder {
        val binding = ContactDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RVViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RVViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    fun setsContact(ncontact: List<Contact>) {
        this.contacts = ncontact
        notifyDataSetChanged()
    }
}