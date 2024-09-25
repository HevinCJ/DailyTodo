package com.example.dailytodo.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dailytodo.helper.Priority
import kotlinx.parcelize.Parcelize


@Entity(tableName = "todo_table")
@Parcelize
data class Todo(
    @PrimaryKey(autoGenerate = true)
    val id:Int?=null,
    val title:String,
    val priority:Priority
):Parcelable
