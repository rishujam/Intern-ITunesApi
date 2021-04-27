package store.cru.intern.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import store.cru.intern.databinding.ActivityMainBinding
import store.cru.intern.adapters.SongAdapter
import store.cru.wednesdaysolutions.db.SongDatabase
import store.cru.wednesdaysolutions.repository.SongRepository
import store.cru.intern.utils.Resource
import store.cru.wednesdaysolutions.ui.SongViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: SongViewModel
    lateinit var songAdapter: SongAdapter
    private val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRV()

        val songRepository = SongRepository(SongDatabase(this))
        val  viewModelProviderFactory = SongViewModelProviderFactory(songRepository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(SongViewModel::class.java)

        viewModel.songs.observe(this, Observer { response ->
            when(response){
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let {
                        songAdapter.differ.submitList(it.results)
                    }
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message.let {
                        Log.e(TAG, "An error occured ${it}")
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar(){
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar(){
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun setupRV(){
        songAdapter = SongAdapter()
        binding.rvSongs.apply {
            adapter = songAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}