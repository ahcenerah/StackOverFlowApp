package fr.mastersid.stackoverflow.webservice

import androidx.compose.ui.layout.ScaleFactor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create


private const val BASE_URL = "https://api.stackexchange.com/2.3/"
@Module
@InstallIn(SingletonComponent::class)
object StackOverflowWebserviceModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }
    @Provides
    fun provideStackOverflowWebservice(retrofit: Retrofit): StackOverflowService{
        return retrofit.create(StackOverflowService::class.java)
    }
}