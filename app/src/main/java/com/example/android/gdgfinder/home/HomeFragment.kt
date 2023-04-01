package com.example.android.gdgfinder.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.android.gdgfinder.R
import com.example.android.gdgfinder.databinding.HomeFragmentBinding

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private lateinit var viewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /**
         * (06) Create a binding to the home_fragment layout and tell the binding
         * about the viewModel.
         * */
        val binding = HomeFragmentBinding.inflate(inflater)
        binding.setLifecycleOwner(this)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        binding.viewModel = viewModel

        val view = binding.root//inflater.inflate(R.layout.home_fragment, container, false)

        /**
         * (07) Register an observer on navigateToSearch, and have it navigate
         * to gdgListFragment if shouldNavigate is true.
         * */
        viewModel.navigateToSearch.observe(
            viewLifecycleOwner,
            Observer<Boolean> { shouldNavigate ->
                if (shouldNavigate == true) {
                    val navController = binding.root.findNavController()
                    navController.navigate(R.id.action_homeFragment_to_gdgListFragment)
                    viewModel.onNavigatedToSearch()
                }
            }
        )

        return view
    }
}
