package com.gugut.base_character_sdk.data.remote

import com.gugut.base_character_sdk.data.remote.dto.CharactersDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CharactersApi {

    @GET("/")
    suspend fun getCharacters(
        @Query("q") q: String,
        @Query("format") format: String = FORMAT
    ): CharactersDto

    companion object {
        //http://api.duckduckgo.com/?q=simpsons+characters&format=json
        const val BASE_URL = "http://api.duckduckgo.com/"
        const val IMAGE_BASE_URL = "https://duckduckgo.com/"
        private const val FORMAT = "json"
    }

}