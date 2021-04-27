package store.cru.wednesdaysolutions.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import store.cru.intern.models.SongsListResponse
import store.cru.intern.utils.Resource
import store.cru.wednesdaysolutions.repository.SongRepository

class SongViewModel (
    val songRepository: SongRepository
    ) : ViewModel(){

    val songs: MutableLiveData<Resource<SongsListResponse>> = MutableLiveData()

    init {
        getSongs("taylor+swift","US")
    }
    fun getSongs(name:String, countryCode:String) = viewModelScope.launch {
        songs.postValue(Resource.Loading())
        val response = songRepository.getSongs(name,countryCode)
        songs.postValue(handleSongResponse(response))
    }

    private fun handleSongResponse(response: retrofit2.Response<SongsListResponse>): Resource<SongsListResponse> {
        if(response.isSuccessful){
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}