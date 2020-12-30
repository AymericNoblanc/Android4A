package com.example.android4a.presentation.listActivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4a.domain.usecase.GetUserUseCase
import com.example.android4a.presentation.createAccount.AccountError
import com.example.android4a.presentation.createAccount.AccountStatus
import com.example.android4a.presentation.createAccount.AccountSuccess
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
                AccountSuccess(user.email, user.password, user.latitude, user.longitude)
            }else{
                AccountError
            }

            withContext(Dispatchers.Main){
                accountLiveData.value = accountStatus
            }
        }
    }
}