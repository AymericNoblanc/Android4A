package com.example.android4a.presentation.main

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4a.domain.entity.User
import com.example.android4a.domain.usecase.CreateUserUseCase
import com.example.android4a.domain.usecase.GetUserUseCase
import com.example.android4a.presentation.createAccount.CreateAccount
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel (
    private val getUserUseCase: GetUserUseCase,
    private val createUserUseCase: CreateUserUseCase
) : ViewModel(){

    fun onCreate(){
        viewModelScope.launch(Dispatchers.IO){
            val user = User("test@test.test","test","48.8563740","2.3249920")
            createUserUseCase.invoke(user)
        }
    }

    val loginLiveData : MutableLiveData<LoginStatus> = MutableLiveData()

    fun onClickedLogin(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(emailUser)
            val loginStatus = if(user != null && user.password == password){
                LoginSuccess(user.email, user.password, user)
            }else{
                LoginError
            }

            withContext(Dispatchers.Main){
                loginLiveData.value = loginStatus
            }
         }
    }

    fun onClickedCreateAccount(emailUser: String, password: String, fragmentManager: FragmentManager) {
        val createAccount = CreateAccount(emailUser, password)
        createAccount.show(fragmentManager, "activity_dialog")
    }
}