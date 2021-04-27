package store.cru.intern.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import store.cru.intern.databinding.ItemSongBinding
import store.cru.wednesdaysolutions.models.Song

class SongAdapter : RecyclerView.Adapter<SongAdapter.SongViewHolder>() {

    inner class SongViewHolder(val binding: ItemSongBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallback = object :DiffUtil.ItemCallback<Song>(){
        override fun areItemsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem.trackId == newItem.trackId
        }

        override fun areContentsTheSame(oldItem: Song, newItem: Song): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SongViewHolder {
        return SongViewHolder(ItemSongBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: SongViewHolder, position: Int) {
        val song = differ.currentList[position]
        holder.binding.apply {
            Glide.with(holder.binding.root).load(song.artworkUrl60).into(imageView)
            textView.text = song.trackName
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }
}