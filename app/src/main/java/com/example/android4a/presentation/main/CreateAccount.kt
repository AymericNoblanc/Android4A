package com.example.android4a.presentation.main

import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4a.R
import com.example.android4a.data.repository.UserRepository
import com.example.android4a.domain.entity.User
import com.example.android4a.domain.usecase.CreateUserUseCase
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.android.ext.android.inject


internal class CreateAccount(Email: String, Password: String) : DialogFragment() {

    val createAccountViewModel : CreateAccountViewModel by inject()

    private var toolbar: Toolbar? = null

    private var button: Button? = null

    private lateinit var email_edit: TextInputEditText
    private var email: String? = Email

    private lateinit var password_edit: TextInputEditText
    private var password: String? = Password

    private lateinit var lastname_edit: TextInputEditText

    private lateinit var firstname_edit: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window!!.setLayout(width, height)
            dialog.window!!.setWindowAnimations(R.style.AppTheme_Slide)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.activity_creation_account, container, false)

        toolbar = view.findViewById(R.id.toolbar)
        button = view.findViewById(R.id.creation_button)

        email_edit = view.findViewById(R.id.email_edit)
        if(email != null){
            email_edit.setText(email)
        }

        password_edit = view.findViewById(R.id.password_edit)
        if(password != null){
            password_edit.setText(password)
        }

        lastname_edit = view.findViewById(R.id.lastname_edit)
        firstname_edit = view.findViewById(R.id.firstname_edit)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar!!.setNavigationOnClickListener { dismiss() }
        toolbar!!.title = "Création de compte"
        toolbar!!.setTitleTextColor(Color.WHITE)
        button?.setOnClickListener {
            val user = User(email_edit.text.toString().trim(), password_edit.text.toString().trim(), lastname_edit.text.toString().trim(), firstname_edit.text.toString().trim())
            createAccountViewModel.onClicked(user)
            dismiss()
        }
    }
}