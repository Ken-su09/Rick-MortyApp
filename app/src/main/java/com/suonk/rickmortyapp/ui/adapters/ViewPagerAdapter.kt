package com.suonk.rickmortyapp.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.suonk.rickmortyapp.ui.fragments.main_pages.AllCharactersFragment
import com.suonk.rickmortyapp.ui.fragments.main_pages.AllEpisodesFragment
import com.suonk.rickmortyapp.ui.fragments.main_pages.AllLocationsFragment

class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AllCharactersFragment()
            }
            1 -> {
                AllEpisodesFragment()
            }
            else -> {
                AllCharactersFragment()
            }
        }
    }
}