package com.akingyin.androidjetpackdemo.ui.main


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.akingyin.androidjetpackdemo.databinding.MainTestFragmentBinding

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

        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)



    }

}
