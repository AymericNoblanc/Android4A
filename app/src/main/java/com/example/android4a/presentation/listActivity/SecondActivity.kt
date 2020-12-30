package com.example.android4a.presentation.listActivity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android4a.R
import com.example.android4a.domain.entity.User
import com.example.android4a.presentation.createAccount.AccountError
import com.example.android4a.presentation.createAccount.AccountSuccess
import org.koin.android.ext.android.inject

class SecondActivity : SingleFragmentActivity(){

    override fun createFragment() = MainFragment.newInstance(intent.getStringExtra("key"))

    /*val secondViewModel : SecondViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.row_layout)

        val email = intent.getStringExtra("key")

        var user = User("","","","")

        secondViewModel.accountLiveData.observe(this,  {
            when (it) {
                is AccountSuccess -> {

                    user.email = it.email
                    user.password = it.password
                    user.latitude = it.latitude
                    user.longitude = it.longitude

                }
                AccountError -> {

                }
            }
        })

        secondViewModel.getUser(email)

    }*/
}