package store.cru.wednesdaysolutions.db

import androidx.lifecycle.LiveData
import androidx.room.*
import store.cru.wednesdaysolutions.models.Song

@Dao
interface SongDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(response: Song):Long

    @Query("SELECT * FROM songs")
    fun getAllSongs():LiveData<List<Song>>

    @Delete
    suspend fun deleteSong(response:Song)
}