package fr.mastersid.stackoverflow.webservice

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StackOverflowService {
    @GET("questions?pagesize=20&order=desc&sort=activity&site=stackoverflow")
    fun getQuestionList():Call<String>
}