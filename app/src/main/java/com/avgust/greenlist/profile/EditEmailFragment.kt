package com.avgust.greenlist.profile

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.avgust.greenlist.R
import com.avgust.greenlist.databinding.FragmentEditEmailBinding
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class EditEmailFragment : Fragment(R.layout.fragment_edit_email) {
    // view binding
    lateinit var binding: FragmentEditEmailBinding

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    // FirebaseUser
    private lateinit var user: FirebaseUser

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout
        binding = FragmentEditEmailBinding.inflate(inflater, container, false)

        // init firebase auth
        firebaseAuth = FirebaseAuth.getInstance()
        user = firebaseAuth.currentUser!!

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // toolbar return button
        binding.toolBarMain.setNavigationOnClickListener {
            // navigate back to bottom activity
            val action = EditEmailFragmentDirections.actionEditEmailToBottomActivity("EditEmailFragment")
            findNavController().navigate(action)
        }

        binding.editEmail.setOnClickListener {
            // get credential from user
            val credential = EmailAuthProvider
                .getCredential(
                    binding.editTextEmail.text.toString(),
                    binding.editTextPassword.text.toString()
                )
            // call edit email function
            editUserEmail(credential)

            // navigate to bottom activity
            val action = EditEmailFragmentDirections.actionEditEmailToBottomActivity("EditEmailFragment")
            findNavController().navigate(action)
        }
    }

    // authenticate with credential and edit to new email
    private fun editUserEmail(credential: AuthCredential) {
        user.reauthenticate(credential)
            .addOnCompleteListener {
                Log.d(ContentValues.TAG, "User re-authenticated.")
                user.updateEmail(binding.editTextNewEmail.text.toString())
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(activity,"Email updated",Toast.LENGTH_SHORT).show()
                        }else{
                            Toast.makeText(activity,"Email update failed",Toast.LENGTH_SHORT).show()
                        }
                    }
            }
    }
}