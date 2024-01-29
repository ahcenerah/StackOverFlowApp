package fr.mastersid.stackoverflow.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object StackDatabaseModule {
    @Provides
    fun provideQuestionDao(stackRoomDatabase: StackRoomDatabase):QuestionDao{
        return stackRoomDatabase.questionDao()
    }


    @Provides
    @Singleton
    fun provideStackDatabaseModule(@ApplicationContext appContext:Context):StackRoomDatabase{
        return  Room.databaseBuilder(
            appContext.applicationContext,
            StackRoomDatabase::class.java,
            "question_database"
        ).build()
    }
}