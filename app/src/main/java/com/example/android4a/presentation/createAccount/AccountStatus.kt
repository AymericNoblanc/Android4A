package com.example.android4a.presentation.createAccount

sealed class AccountStatus

data class AccountSuccess(val email: String, val password: String, val latitude: String, val longitude: String) : AccountStatus()
object AccountError : AccountStatus()