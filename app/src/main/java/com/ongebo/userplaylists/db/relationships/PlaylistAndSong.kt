package com.ongebo.userplaylists.db.relationships

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.ongebo.userplaylists.db.entities.Playlist
import com.ongebo.userplaylists.db.entities.PlaylistSongCrossRef
import com.ongebo.userplaylists.db.entities.Song

data class PlaylistAndSong(
    @Embedded val playlist: Playlist,

    @Relation(
        parentColumn = "playlist_id",
        entityColumn = "song_id",
        associateBy = Junction(PlaylistSongCrossRef::class)
    )
    val songs: List<Song>
)
