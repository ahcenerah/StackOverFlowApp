package fr.mastersid.stackoverflow.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Question::class],
    version = 1,
    exportSchema = false

)
abstract class StackRoomDatabase :RoomDatabase(){
    abstract fun questionDao():QuestionDao

}