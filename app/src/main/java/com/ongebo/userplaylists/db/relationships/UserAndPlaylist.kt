package com.ongebo.userplaylists.db.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.ongebo.userplaylists.db.entities.Playlist
import com.ongebo.userplaylists.db.entities.User

data class UserAndPlaylist(
    @Embedded val user: User,

    @Relation(
        parentColumn = "user_id",
        entityColumn = "owner_id"
    )
    val playlists: List<Playlist>
)
