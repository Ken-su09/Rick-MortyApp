package com.suonk.rickmortyapp.ui.fragments.main_pages

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.google.android.material.tabs.TabLayoutMediator
import com.suonk.rickmortyapp.databinding.FragmentMainBinding
import com.suonk.rickmortyapp.ui.adapters.ViewPagerAdapter
import com.suonk.rickmortyapp.ui.fragments.BaseFragment
import com.suonk.rickmortyapp.viewmodels.AllCharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : BaseFragment() {

    private var binding: FragmentMainBinding? = null
    private val viewModel: AllCharactersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewPager()
        searchBarTextListener()
    }

    private fun searchBarTextListener() {
        binding?.searchEditText?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if (text?.toString() != null) {
                    viewModel.setSearchText(text.toString())
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    private fun setupViewPager() {
        binding?.viewPager?.adapter = ViewPagerAdapter(contextActivity)

        TabLayoutMediator(
            binding?.tabLayout!!,
            binding?.viewPager!!
        ) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Characters"
                }
                1 -> {
                    tab.text = "Episodes"
                }
            }
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}