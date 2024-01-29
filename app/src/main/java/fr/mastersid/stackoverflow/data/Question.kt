package fr.mastersid.stackoverflow.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class Question(@PrimaryKey val id:Int, val title:String, val answerCount:Int)
