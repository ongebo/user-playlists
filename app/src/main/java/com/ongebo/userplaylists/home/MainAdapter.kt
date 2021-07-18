package com.ongebo.userplaylists.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ongebo.userplaylists.databinding.UserPlaylistsBinding
import com.ongebo.userplaylists.db.relationships.UserAndPlaylist
import com.ongebo.userplaylists.utils.AppViewHolder

class MainAdapter(private val userPlaylists: List<UserAndPlaylist>) :
    RecyclerView.Adapter<AppViewHolder>() {

    private lateinit var binding: UserPlaylistsBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppViewHolder {
        binding =
            UserPlaylistsBinding.inflate(LayoutInflater.from(parent.context))
        return AppViewHolder(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        binding.apply {
            userPlaylists[position].apply {
                tvUsername.text = "${user.firstName} ${user.lastName}"
                tvPlaylists.text = getPlaylistString()
            }
        }
    }

    private fun UserAndPlaylist.getPlaylistString(): String {
        var playlistString = ""
        for (playlist in playlists) {
            playlistString += "${playlist.playlistName}, "
        }
        return playlistString.substring(0, playlistString.length - 2)
    }

    override fun getItemCount(): Int = userPlaylists.size
}
