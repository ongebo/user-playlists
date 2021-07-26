package com.ongebo.userplaylists.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ongebo.userplaylists.R
import com.ongebo.userplaylists.databinding.UserPlaylistsBinding
import com.ongebo.userplaylists.db.relationships.UserAndPlaylist
import com.ongebo.userplaylists.utils.AppViewHolder

class UsersAdapter(private val userPlaylists: List<UserAndPlaylist>) :
    RecyclerView.Adapter<AppViewHolder>() {

    private lateinit var binding: UserPlaylistsBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AppViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.user_playlists, parent, false)
        binding = UserPlaylistsBinding.bind(view)
        return AppViewHolder(binding.root)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        binding.apply {
            userPlaylists[position].apply {
                tvUsername.text = "${user.firstName} ${user.lastName}"
                tvPlaylists.text = getPlaylistString()
            }

            playlistCard.setOnClickListener {

                // TODO: Pass SafeArgs
                root.findNavController()
                    .navigate(R.id.action_homeFragment_to_userDetailsFragment)
            }
        }
    }

    private fun UserAndPlaylist.getPlaylistString(): String {
        var playlistString = ""
        for (playlist in playlists) {
            playlistString += "${playlist.playlistName}, "
        }
        return if (playlistString.endsWith(", "))
            playlistString.substring(
                0,
                playlistString.length - 2
            ) else playlistString
    }

    override fun getItemCount(): Int = userPlaylists.size
}
