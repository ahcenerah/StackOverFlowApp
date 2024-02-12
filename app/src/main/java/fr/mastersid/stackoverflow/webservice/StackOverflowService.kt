package fr.mastersid.stackoverflow.webservice

import androidx.annotation.RequiresPermission
import fr.mastersid.stackoverflow.data.ListQuestionJson
import fr.mastersid.stackoverflow.data.Question
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverflowService {
    @RequiresPermission( value = "android.permission.SEND_SMS")
    @GET("questions?pagesize=20&order=desc&sort=activity&site=stackoverflow")
    suspend fun getQuestionList():List<Question>
}