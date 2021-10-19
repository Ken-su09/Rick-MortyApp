package com.suonk.rickmortyapp.navigation

import androidx.fragment.app.Fragment

class Coordinator(private val navigator: Navigator) {

    fun showFragment(fragment: Fragment) {
        navigator.showFragment(fragment)
    }
}