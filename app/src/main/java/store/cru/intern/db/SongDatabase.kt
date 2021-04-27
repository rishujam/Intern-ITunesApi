package store.cru.wednesdaysolutions.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import store.cru.wednesdaysolutions.models.Song

@Database(
    entities = [Song::class],
    version = 1
)
abstract class SongDatabase :RoomDatabase(){

    abstract  fun getSongDao():SongDao

    companion object{
        @Volatile
        private var instance:SongDatabase?=null
        private val LOCK = Any()

        operator  fun invoke(context: Context) = instance?: synchronized(LOCK){
            instance?:createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                SongDatabase::class.java,
                "song_db.db"
            ).build()
    }
}