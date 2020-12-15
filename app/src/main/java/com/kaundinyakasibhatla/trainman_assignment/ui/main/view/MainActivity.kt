package com.kaundinyakasibhatla.trainman_assignment.ui.main.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.lifecycle.Observer
import com.kaundinyakasibhatla.trainman_assignment.ui.main.viewmodel.MainActivityViewModel
import com.kaundinyakasibhatla.trainman_assignment.R
import com.kaundinyakasibhatla.trainman_assignment.databinding.ActivityMainBinding
import com.kaundinyakasibhatla.trainman_assignment.ui.detail.view.GifDetailActivity
import com.kaundinyakasibhatla.trainman_assignment.ui.main.adapter.MainAdapter
import com.kaundinyakasibhatla.trainman_assignment.ui.main.helper.EndlessRecyclerViewScrollListener
import com.kaundinyakasibhatla.trainman_assignment.utils.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var adapter: MainAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupUI()
        setupObserver()
    }

    override fun onStart() {
        super.onStart()
        if (mainActivityViewModel.gifs.value?.data.isNullOrEmpty()) {
            mainActivityViewModel.fetchGifs(0)
        }
    }

    private fun setupUI() {
        val llm = GridLayoutManager(this, 2)
        llm.orientation = RecyclerView.VERTICAL
        binding.recyclerView.layoutManager = llm
        adapter = MainAdapter(arrayListOf(), ::showDialog)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(object : EndlessRecyclerViewScrollListener(llm) {
            override fun onLoadMore(page: Int, totalItemsCount: Int) {
                mainActivityViewModel.fetchGifs(totalItemsCount)
            }
        })

    }

    private fun showDialog(url: String) {
       val intent = Intent(this,GifDetailActivity::class.java)
        intent.putExtra("imageUrl",url)
        startActivity(intent)
    }

    private fun setupObserver() {
        mainActivityViewModel.gifs.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    it.data?.let { icons -> renderList(icons) }
                    binding.recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    //Handle Error
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        })


    }


    private fun renderList(icons: List<String>) {

           adapter.setData(icons)
           adapter.notifyDataSetChanged()


    }

}