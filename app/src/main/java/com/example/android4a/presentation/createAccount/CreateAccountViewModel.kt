package com.example.android4a.presentation.createAccount

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android4a.domain.entity.User
import com.example.android4a.domain.usecase.CreateUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateAccountViewModel(private val createUserUseCase: CreateUserUseCase) : ViewModel(){

    fun onClicked(user: User){
        viewModelScope.launch(Dispatchers.IO){
            createUserUseCase.invoke(user)
        }
    }
}