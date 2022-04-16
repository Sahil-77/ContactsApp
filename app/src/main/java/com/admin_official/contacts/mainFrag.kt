package com.admin_official.contacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.admin_official.contacts.databinding.FragmentMainBinding
import java.util.*

class mainFrag : Fragment(), RecyclerViewAdapter.RVListener {


    private val adapter = RecyclerViewAdapter(Collections.emptyList(), this)
    lateinit var binding: FragmentMainBinding
    private val viewModel: AppViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(hasPermission) {
            viewModel.contacts.observe(this) {
                adapter.setsContact(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.mainfragRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.mainfragRecyclerView.adapter = adapter
    }

    override fun onItemClicked(contest: Contact) {
        // todo
    }
}