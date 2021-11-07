package com.suonk.rickmortyapp.ui.fragments.main_pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.suonk.rickmortyapp.R
import com.suonk.rickmortyapp.databinding.FragmentAllCharactersBinding
import com.suonk.rickmortyapp.databinding.FragmentAllEpisodesBinding
import com.suonk.rickmortyapp.ui.activity.MainActivity
import com.suonk.rickmortyapp.ui.adapters.CharactersListAdapter
import com.suonk.rickmortyapp.ui.adapters.EpisodesListAdapter
import com.suonk.rickmortyapp.ui.fragments.BaseFragment
import com.suonk.rickmortyapp.viewmodels.AllCharactersViewModel
import com.suonk.rickmortyapp.viewmodels.AllEpisodesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllEpisodesFragment : BaseFragment() {

    private var binding: FragmentAllEpisodesBinding? = null
    private val viewModel: AllEpisodesViewModel by activityViewModels()
    private lateinit var episodeAdapter: EpisodesListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllEpisodesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        episodeAdapter = EpisodesListAdapter(contextActivity) { id ->
//            val navDir = MainFragmentDirections.actionMainFragmentToCharacterDetailsFragment()
//            navController.navigate(navDir)
//
//            setCharacterSelectedInViewModel(id)
        }
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding?.episodesRecyclerView?.apply {
            adapter = episodeAdapter
            getAllCharactersFromViewModel()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(contextActivity)
        }
    }

    private fun getAllCharactersFromViewModel() {
        viewModel.getAllEpisodes(null)
        viewModel.allEpisodesLiveData.observe(viewLifecycleOwner, { episodes ->
            episodeAdapter.submitList(null)
            episodeAdapter.submitList(episodes.results)
        })
    }

    private fun setCharacterSelectedInViewModel(characterSelectedId: Int) {
//        viewModel.allCharactersLiveData.observe(viewLifecycleOwner, { characters ->
//            for (character in characters.results) {
//                if (character.id == characterSelectedId) {
//                    viewModel.setCharacterSelected(character)
//                }
//            }
//        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}