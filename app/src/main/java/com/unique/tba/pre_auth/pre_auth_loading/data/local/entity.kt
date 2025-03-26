package com.unique.tba.pre_auth.pre_auth_loading.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppTourEntity(
    val image: String?,
    val subtitle: String?,
    val title: String?,
    @PrimaryKey val id: Int? = null
)