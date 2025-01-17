package com.soul.taskbreeze.core.local_db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.soul.taskbreeze.pre_auth.pre_auth_loading.data.local.AppTourDao
import com.soul.taskbreeze.pre_auth.pre_auth_loading.data.local.AppTourEntity

@Database(
    entities = [AppTourEntity::class],
    version = 1,
    exportSchema = false
)
abstract class TaskBreezeDatabase: RoomDatabase() {
    abstract val dao: AppTourDao
}