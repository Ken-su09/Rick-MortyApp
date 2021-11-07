package com.suonk.rickmortyapp.ui.fragments

import androidx.fragment.app.Fragment
import com.suonk.rickmortyapp.ui.activity.MainActivity

abstract class BaseFragment : Fragment() {

    protected val navController by lazy {
        (activity as MainActivity).navController
    }

    protected val contextActivity by lazy {
        activity as MainActivity
    }
}