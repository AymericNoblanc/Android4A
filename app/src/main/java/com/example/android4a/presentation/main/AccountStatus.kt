package com.example.android4a.presentation.main

sealed class AccountStatus

data class AccountSuccess(val email: String, val password: String, val nom: String, val prenom: String) : AccountStatus()
object AccountError : AccountStatus()