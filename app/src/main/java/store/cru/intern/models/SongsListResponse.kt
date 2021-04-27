package store.cru.intern.models

import store.cru.wednesdaysolutions.models.Song

data class SongsListResponse(
    val resultCount: Int,
    val results: List<Song>
)