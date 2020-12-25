package com.example.android4a.presentation.main

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android4a.R
import com.example.android4a.domain.entity.User
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.koin.android.ext.android.inject
import java.util.Observer

class SecondActivity : AppCompatActivity(){

    val secondViewModel : SecondViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val user = intent.getSerializableExtra("key") as User

        findViewById<TextView>(R.id.textView).apply {
            val texte: String = user.email + " - " + user.password + " - " + user.nom + " - " + user.prenom
            text = texte
        }

        /*val email = user.email

        secondViewModel.accountLiveData.observe(this,  {
            when (it) {
                is AccountSuccess -> {
                    findViewById<TextView>(R.id.textView).apply {
                        val texte: String = it.email + " - " + it.password + " - " + it.nom + " - " + it.prenom
                        text = texte
                    }
                }
                AccountError -> {

                }
            }
        })

        secondViewModel.getUser(email)
*/

    }
}
