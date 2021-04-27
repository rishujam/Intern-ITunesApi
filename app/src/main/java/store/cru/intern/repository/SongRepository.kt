package store.cru.wednesdaysolutions.repository

import store.cru.intern.api.RetrofitInstance
import store.cru.wednesdaysolutions.db.SongDatabase

class SongRepository(
    val db: SongDatabase
) {
    suspend fun getSongs(name:String, countryCode:String) =
        RetrofitInstance.api.getSongs(name,countryCode)
}