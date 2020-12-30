package com.example.android4a.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android4a.R
import com.example.android4a.presentation.listActivity.SecondActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    val mainViewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.onCreate()

        mainViewModel.loginLiveData.observe(this, {
            when (it) {
                is LoginSuccess -> {
                    val intent = Intent(this, SecondActivity::class.java)
                    intent.putExtra("key", it.email)
                    startActivity(intent)
                }
                LoginError -> {
                    MaterialAlertDialogBuilder(this)
                        .setTitle("Erreur")
                        .setMessage("Compte inconnu")
                        .setPositiveButton("OK") { dialog, _ ->
                            dialog.dismiss()
                        }
                        .show()
                }
            }
        })

        login_button.setOnClickListener {
            mainViewModel.onClickedLogin(login_edit.text.toString().trim(), password_edit.text.toString() )
        }

        create_account_button.setOnClickListener {
            mainViewModel.onClickedCreateAccount(login_edit.text.toString().trim(), password_edit.text.toString(), getSupportFragmentManager())
        }
    }
}