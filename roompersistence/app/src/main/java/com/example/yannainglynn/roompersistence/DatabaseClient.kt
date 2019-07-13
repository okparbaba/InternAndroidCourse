package com.example.yannainglynn.roompersistence

import android.arch.persistence.room.Room
import android.content.Context

class DatabaseClient private constructor(mCtx: Context) {
    //our app database object
    val appDatabase: AppDatabase = Room.databaseBuilder(mCtx, AppDatabase::class.java, "MyToDos").build()
    companion object {
        private var mInstance: DatabaseClient? = null
        @Synchronized
        fun getInstance(mCtx: Context): DatabaseClient {
            if (mInstance == null) {
                mInstance = DatabaseClient(mCtx)
            }
            return mInstance!!
        }
    }
}