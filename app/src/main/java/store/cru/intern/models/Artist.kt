package store.cru.intern.models

import store.cru.wednesdaysolutions.models.Song

data class Artist(
    val resultCount: Int,
    val results: List<Song>
)