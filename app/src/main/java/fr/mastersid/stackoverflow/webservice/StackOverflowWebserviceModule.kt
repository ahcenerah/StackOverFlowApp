package fr.mastersid.stackoverflow.webservice

import androidx.compose.ui.layout.ScaleFactor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.mastersid.stackoverflow.data.QuestionMoshiAdapter
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create


private const val BASE_URL = "https://api.stackexchange.com/2.3/"
@Module
@InstallIn(SingletonComponent::class)
object StackOverflowWebserviceModule {
    @Provides
    fun provideMoshi():Moshi{
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(QuestionMoshiAdapter())
            .build()
    }
    @Provides
    fun provideRetrofit(moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }
    @Provides
    fun provideStackOverflowWebservice(retrofit: Retrofit): StackOverflowService{
        return retrofit.create(StackOverflowService::class.java)
    }
}