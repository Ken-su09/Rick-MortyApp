package com.suonk.rickmortyapp.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.suonk.rickmortyapp.R
import javax.inject.Inject

class Navigator @Inject constructor(var activity: FragmentActivity?) {

    fun showFragment(fragment: Fragment) {
        activity!!.supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container_view, fragment)
            .commit()
    }
}