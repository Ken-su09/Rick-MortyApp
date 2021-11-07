package com.suonk.rickmortyapp.ui.fragments.main_pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suonk.rickmortyapp.R
import com.suonk.rickmortyapp.databinding.FragmentAllCharactersBinding
import com.suonk.rickmortyapp.models.data.Result
import com.suonk.rickmortyapp.ui.activity.MainActivity
import com.suonk.rickmortyapp.ui.adapters.CharactersListAdapter
import com.suonk.rickmortyapp.ui.fragments.BaseFragment
import com.suonk.rickmortyapp.viewmodels.AllCharactersViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class AllCharactersFragment : BaseFragment() {

    companion object {
        const val QUERY_PAGE_SIZE = 20
    }

    private var binding: FragmentAllCharactersBinding? = null
    private val viewModel: AllCharactersViewModel by activityViewModels()
    private lateinit var charactersAdapter: CharactersListAdapter

    private var listOfCharacters = mutableListOf<Result>()

    private var isLoading = false
    private var isLastPage = false
    private var isScrolling = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllCharactersBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        charactersAdapter = CharactersListAdapter(contextActivity) { id ->
            val navDir = MainFragmentDirections.actionMainFragmentToCharacterDetailsFragment()
            navController.navigate(navDir)

            setCharacterSelectedInViewModel(id)
        }
        initRecyclerView()
        observeSearchBarTextFromMainFragment()
    }

    private fun initRecyclerView() {
        binding?.charactersRecyclerView?.apply {
            adapter = charactersAdapter
            getAllCharactersFromViewModel()
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(contextActivity)
            recyclerViewScrollListener()
        }
    }

    private fun getAllCharactersFromViewModel() {
        charactersAdapter.submitList(null)
        viewModel.getAllCharacters()
        viewModel.allCharactersLiveData.observe(viewLifecycleOwner, { characters ->
            CoroutineScope(Dispatchers.Main).launch {
                launchProgressBarSpin(2000)
                binding?.totalCharactersValue?.text = characters.info.count.toString()
            }
            charactersAdapter.submitList(characters.results.toList())
        })
    }

    private fun setCharacterSelectedInViewModel(characterSelectedId: Int) {
        viewModel.allCharactersLiveData.observe(viewLifecycleOwner, { characters ->
            for (character in characters.results) {
                if (character.id == characterSelectedId) {
                    viewModel.setCharacterSelected(character)
                }
            }
        })
    }

    private fun observeSearchBarTextFromMainFragment() {
        viewModel.allCharactersLiveData.observe(viewLifecycleOwner, { characters ->
            viewModel.searchBarText.observe(viewLifecycleOwner, { searchBarText ->
                if (searchBarText.isNotEmpty()) {
                    binding?.totalCharactersValue?.text = characters.info.count.toString()
                    val listOfCharacters = mutableListOf<Result>()
                    characters.results.forEach { character ->
                        if (character.name.contains(searchBarText) ||
                            character.name.contains(searchBarText.lowercase()) ||
                            character.name.contains(searchBarText.uppercase())
                        ) {
                            listOfCharacters.add(character)
                        }
                    }
                    binding?.totalCharactersValue?.text = listOfCharacters.size.toString()
                    charactersAdapter.submitList(null)
                    charactersAdapter.submitList(listOfCharacters.toList())
                }
            })
        })
    }

    private fun recyclerViewScrollListener() {
        binding?.charactersRecyclerView?.addOnScrollListener(object :
            RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount

                val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
                val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
                val isNotAtBeginning = firstVisibleItemPosition >= 0
                val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
                val shouldPaginate =
                    isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning && isTotalMoreThanVisible && isScrolling

                if (shouldPaginate) {
                    viewModel.getAllCharacters()
                    isScrolling = false
                }
            }
        })
    }

    private suspend fun launchProgressBarSpin(time: Long) {
        binding?.progressBar?.isVisible = true
        delay(time)
        binding?.progressBar?.isVisible = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}