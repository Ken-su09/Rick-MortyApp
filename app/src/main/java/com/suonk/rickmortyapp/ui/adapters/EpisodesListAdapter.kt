package com.suonk.rickmortyapp.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suonk.rickmortyapp.databinding.ItemCharacterBinding
import com.suonk.rickmortyapp.databinding.ItemEpisodeBinding
import com.suonk.rickmortyapp.models.data.Result
import com.suonk.rickmortyapp.models.data.ResultX
import com.suonk.rickmortyapp.ui.activity.MainActivity

class EpisodesListAdapter(
    private val activity: MainActivity,
    private val onClickedCallback: (Int) -> Unit
) :
    ListAdapter<ResultX, EpisodesListAdapter.ViewHolder>(EpisodeComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemEpisodeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val episode = getItem(position)
        holder.onBind(episode, onClickedCallback)
    }

    inner class ViewHolder(private val binding: ItemEpisodeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(episode: ResultX, onClicked: (Int) -> Unit) {
            binding.apply {
                episodeName.text = episode.name
                episodeEpisode.text = episode.episode
                episodeDate.text = episode.air_date

                root.setOnClickListener {
                    onClicked(episode.id)
                }
            }
        }
    }

    class EpisodeComparator : DiffUtil.ItemCallback<ResultX>() {
        override fun areItemsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ResultX, newItem: ResultX): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.name == newItem.name &&
                    oldItem.air_date == newItem.air_date &&
                    oldItem.created == newItem.created &&
                    oldItem.url == newItem.url
        }
    }
}