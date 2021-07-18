package com.ongebo.userplaylists

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ongebo.userplaylists.db.AppDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = AppDatabase.getDatabase(this)
        // TODO: Use the database
    }
}
