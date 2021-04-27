package store.cru.intern.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import store.cru.intern.models.SongsListResponse

interface ITunesApi {

    @GET("search?")
    suspend fun getSongs(
            @Query("term")
            name:String = "jack+johnson",
            @Query("country")
            countryCode:String = "US"
    ): Response<SongsListResponse>

}