package com.example.dailytodo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Todo::class], version = 1, exportSchema = false)
abstract class TodoDatabase:RoomDatabase() {

    abstract fun todoDao():TodoDao


    companion object{

        @Volatile
        private var INSTANCE:TodoDatabase?=null

        fun getDatabase(context: Context):TodoDatabase{

            val tempInstace = INSTANCE

            if (tempInstace!=null){
                return tempInstace
            }else{
                synchronized(this){
                    val instance = Room.databaseBuilder(context.applicationContext,TodoDatabase::class.java,"Todo_database").build()
                    INSTANCE  = instance
                    return instance
                }
            }

        }
    }

}