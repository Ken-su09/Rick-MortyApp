package com.suonk.rickmortyapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.suonk.rickmortyapp.R
import com.suonk.rickmortyapp.databinding.FragmentCharacterDetailsBinding
import com.suonk.rickmortyapp.ui.adapters.CharactersListAdapter
import com.suonk.rickmortyapp.viewmodels.AllCharactersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment : BaseFragment() {

    private var binding: FragmentCharacterDetailsBinding? = null

    private val viewModel: AllCharactersViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCharacterFromViewModel()
    }

    private fun getCharacterFromViewModel() {
        viewModel.characterSelectedLiveData.observe(viewLifecycleOwner, { character ->
            binding?.apply {
                character.apply {
                    characterName.text = name
                    characterStatus.text = status
                    genderValue.text = gender
                    raceValue.text = species
                    originLocationValue.text = origin.name
                    locationValue.text = location.name

                    characterPicture.let { img ->
                        Glide.with(contextActivity)
                            .load(character.image)
                            .centerCrop()
                            .into(img)
                    }
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}