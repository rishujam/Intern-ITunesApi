package store.cru.intern.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import store.cru.wednesdaysolutions.repository.SongRepository
import store.cru.wednesdaysolutions.ui.SongViewModel

class SongViewModelProviderFactory(
    val songRepo: SongRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SongViewModel(songRepo) as T
    }
}