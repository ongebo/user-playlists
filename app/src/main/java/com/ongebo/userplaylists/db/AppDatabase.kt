package com.ongebo.userplaylists.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ongebo.userplaylists.db.daos.PlaylistsDao
import com.ongebo.userplaylists.db.daos.SongsDao
import com.ongebo.userplaylists.db.daos.UsersDao
import com.ongebo.userplaylists.db.entities.Playlist
import com.ongebo.userplaylists.db.entities.PlaylistSongCrossRef
import com.ongebo.userplaylists.db.entities.Song
import com.ongebo.userplaylists.db.entities.User
import com.ongebo.userplaylists.utils.DATABASE_NAME

@Database(
    entities = [User::class, Playlist::class, Song::class, PlaylistSongCrossRef::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
    abstract fun playlistsDao(): PlaylistsDao
    abstract fun songsDao(): SongsDao

    companion object {

        @JvmStatic
        private var appDatabase: AppDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): AppDatabase {
            if (appDatabase == null) {
                appDatabase = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
            return appDatabase!!
        }
    }
}
