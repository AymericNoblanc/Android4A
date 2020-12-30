package com.example.android4a.data.local.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.android4a.domain.entity.User

@Entity
data class UserLocal(
        @ColumnInfo(name = "email") val email: String,
        @ColumnInfo(name = "password") val password: String,
        @ColumnInfo(name = "latitude") val latitude: String,
        @ColumnInfo(name = "longitude") val longitude: String
){
    @PrimaryKey(autoGenerate = true) var uid: Int? = null
}

fun User.toData(): UserLocal {
    return UserLocal(email = email, password = password, latitude = latitude, longitude = longitude)
}

fun UserLocal.toEntity(): User {
    return User(email = email,  password = password, latitude = latitude,  longitude = longitude)
}