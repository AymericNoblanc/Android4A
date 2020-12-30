package com.example.android4a.injection

import android.content.Context
import androidx.room.Room
import com.example.android4a.data.local.AppDatabase
import com.example.android4a.data.local.DatabaseDao
import com.example.android4a.data.repository.UserRepository
import com.example.android4a.domain.usecase.CreateUserUseCase
import com.example.android4a.domain.usecase.GetUserUseCase
import com.example.android4a.presentation.createAccount.CreateAccountViewModel
import com.example.android4a.presentation.main.MainViewModel
import com.example.android4a.presentation.listActivity.SecondViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val presentationModule = module {
    factory { MainViewModel(get(), get()) }
    factory { CreateAccountViewModel(get()) }
    factory { SecondViewModel(get()) }
}

val domainModule = module {
    factory { CreateUserUseCase(get()) }
    factory { GetUserUseCase(get())}
}

val dataModule = module {
    single { UserRepository(get()) }
    single { createDatabase(androidContext())}
}

fun createDatabase(context: Context): DatabaseDao{
    val appDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    ).build()
    return appDatabase.databaseDao()
}
