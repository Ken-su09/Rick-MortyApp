package com.suonk.rickmortyapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suonk.rickmortyapp.databinding.ItemCharacterBinding
import com.suonk.rickmortyapp.models.data.Result
import com.suonk.rickmortyapp.ui.activity.MainActivity

class CharactersListAdapter(
    private val activity: MainActivity,
    private val onClickedCallback: (Int) -> Unit
) :
    ListAdapter<Result, CharactersListAdapter.ViewHolder>(CharacterComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCharacterBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val character = getItem(position)
        holder.onBind(character, onClickedCallback)
    }

    inner class ViewHolder(private val binding: ItemCharacterBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(character: Result, onClicked: (Int) -> Unit) {
            binding.apply {
                characterName.text = character.name
                characterStatus.text = character.status
                genderValue.text = character.gender
                raceValue.text = "${character.species},"

                characterPicture.let { img ->
                    Glide.with(activity)
                        .load(character.image)
                        .centerCrop()
                        .into(img)
                }

                root.setOnClickListener {
                    onClicked(character.id)
                }
            }
        }
    }

    class CharacterComparator : DiffUtil.ItemCallback<Result>() {
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.gender == newItem.gender &&
                    oldItem.name == newItem.name &&
                    oldItem.image == newItem.image &&
                    oldItem.location == newItem.location &&
                    oldItem.status == newItem.status &&
                    oldItem.species == newItem.species
        }

    }
}