package com.ongebo.userplaylists.home

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ongebo.userplaylists.R
import com.ongebo.userplaylists.databinding.DialogUserBinding
import com.ongebo.userplaylists.databinding.FragmentHomeBinding
import com.ongebo.userplaylists.db.AppDatabase
import com.ongebo.userplaylists.db.entities.User
import com.ongebo.userplaylists.utils.showToast

class HomeFragment : Fragment() {
    private val db by lazy { AppDatabase.getDatabase(requireContext()) }
    private val viewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }
    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.setObserversAndGetData()
        handleAddButtonClick()
    }

    private fun HomeViewModel.setObserversAndGetData() {
        usersAndPlaylists.observe(viewLifecycleOwner) {
            binding!!.rvUserPlaylists.apply {
                layoutManager = LinearLayoutManager(requireContext())
                setHasFixedSize(true)
                adapter = UsersAdapter(it)
            }
        }

        getUsersAndPlaylists(db.usersDao())
    }

    private fun handleAddButtonClick() {
        binding!!.fabAddUser.setOnClickListener {
            Dialog(requireContext()).apply {
                setCancelable(false)
                setTitle(R.string.label_enter_user_details)
                val dialogBinding = DialogUserBinding.inflate(layoutInflater)
                setContentView(dialogBinding.root)
                handleDialogClicks(dialogBinding)
                show()
            }
        }
    }

    private fun Dialog.handleDialogClicks(dialogBinding: DialogUserBinding) {
        dialogBinding.apply {
            btnAddUser.setOnClickListener {
                val firstName = inputFirstName.text.toString()
                val lastName = inputLastName.text.toString()
                val sex = inputSex.text.toString()
                if (firstName.isNotBlank() && lastName.isNotBlank() && sex.isNotBlank()) {
                    viewModel.addUser(
                        User(firstName, lastName, sex),
                        db.usersDao()
                    )
                    hide()
                } else {
                    requireContext().showToast(getString(R.string.prompt_valid_info))
                }
            }

            btnCancel.setOnClickListener {
                hide()
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}
