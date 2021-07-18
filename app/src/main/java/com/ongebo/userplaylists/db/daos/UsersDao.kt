package com.ongebo.userplaylists.db.daos

import androidx.room.*
import com.ongebo.userplaylists.db.entities.User
import com.ongebo.userplaylists.db.relationships.UserAndPlaylist

@Dao
interface UsersDao {

    @Insert
    suspend fun addUsers(vararg user: User)

    @Update
    suspend fun updateUsers(vararg user: User)

    @Delete
    suspend fun deleteUsers(vararg user: User)

    @Query("SELECT * FROM users")
    suspend fun getUsers(): List<User>

    @Query("SELECT * FROM users WHERE user_id = :userId")
    suspend fun getUser(userId: Int): User

    @Transaction
    @Query("SELECT * FROM users")
    suspend fun getUsersAndPlaylists(): List<UserAndPlaylist>

    @Transaction
    @Query("SELECT * FROM users WHERE user_id = :userId")
    suspend fun getUserAndPlaylist(userId: Int): UserAndPlaylist
}
