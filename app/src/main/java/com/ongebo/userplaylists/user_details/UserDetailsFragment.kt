package com.ongebo.userplaylists.user_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ongebo.userplaylists.R

class UserDetailsFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this).get(
            UserDetailsViewModel::class.java
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(
            R.layout.fragment_user_details,
            container,
            false
        )
    }
}
