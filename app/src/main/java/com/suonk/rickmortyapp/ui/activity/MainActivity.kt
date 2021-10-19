package com.suonk.rickmortyapp.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suonk.rickmortyapp.R
import com.suonk.rickmortyapp.databinding.ActivityMainBinding
import com.suonk.rickmortyapp.navigation.Coordinator
import com.suonk.rickmortyapp.navigation.Navigator
import com.suonk.rickmortyapp.ui.fragments.SplashScreenFragment
import com.suonk.rickmortyapp.ui.fragments.main_pages.MainFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    private lateinit var navigator: Navigator
    private lateinit var coordinator: Coordinator
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigator.activity = this
        coordinator = Coordinator(navigator)

        coordinator.showFragment(SplashScreenFragment())

        CoroutineScope(Dispatchers.Main).launch {
            delay(500)
            coordinator.showFragment(MainFragment())
        }
    }
}