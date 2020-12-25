package com.example.android4a.presentation.main

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4a.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondViewModel (
        private val getUserUseCase: GetUserUseCase
) : ViewModel(){

    val accountLiveData : MutableLiveData<AccountStatus> = MutableLiveData()

    fun getUser(mail: String){
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(mail)

            val accountStatus = if(user != null){
                AccountSuccess(user.email, user.password, user.nom, user.prenom)
            }else{
                AccountError
            }

            withContext(Dispatchers.Main){
                accountLiveData.value = accountStatus
            }
        }
    }
}