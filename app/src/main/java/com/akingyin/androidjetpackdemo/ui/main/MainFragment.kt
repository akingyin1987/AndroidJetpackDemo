package com.akingyin.androidjetpackdemo.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.akingyin.androidjetpackdemo.R
import com.akingyin.androidjetpackdemo.databinding.MainTestFragmentBinding
import com.akingyin.androidjetpackdemo.entity.User
import com.akingyin.androidjetpackdemo.ui.adapter.UserListAdapter

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val binding = MainTestFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        binding.user = viewModel.user
        binding.clickListener=viewModel
        binding.recycler.layoutManager = LinearLayoutManager(context)
        binding.recycler.itemAnimator = DefaultItemAnimator()
        var adapter =  UserListAdapter(R.layout.item_user)
        adapter.onItemClickListener = viewModel
        adapter.onItemLongClickListener = viewModel
        viewModel.adapter = adapter
        binding.recycler.adapter =adapter
        adapter.setNewData(viewModel.users)
        viewModel.mutableLiveData.observe(this, Observer<MutableList<User>> {
            println("adapter${it?.size}")
           adapter.setNewData(it)
        })
        viewModel.postionLiveData.observe(this,Observer<Int>{
            adapter.remove(it)
        })

        viewModel.userLiveData.observe(this,Observer<User>{
            adapter.addData(0,it)
            adapter.notifyDataSetChanged()
        })
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }

}
