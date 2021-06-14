package com.example.uaenewsapp.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.uaenewsapp.data.adapter.ArticlesAdapter
import com.example.uaenewsapp.data.api.Resource
import com.example.uaenewsapp.data.api.Status
import com.example.uaenewsapp.data.beans.ApiResponse
import com.example.uaenewsapp.data.beans.Article
import com.example.uaenewsapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.Flow

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {


    private val viewModel: MainViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    lateinit var articlesAdapter: ArticlesAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         articlesAdapter = ArticlesAdapter()

        binding.apply {
            recyclerView.apply {
                adapter = articlesAdapter
                layoutManager = LinearLayoutManager(this@MainActivity)
            }

            /* viewModel.news.observe(this@MainActivity) { result ->
                 restaurantAdapter.submitList(result.)

                 progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
                 //textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
                // textViewError.text = result.error?.localizedMessage
             }*/
        }

        /*viewModel.news.observe(this, Observer {
            it.let {resutl->
                articlesAdapter.submitList(resutl.data)

            }
        })*/
        setupObservers()
    }

    private fun setupObservers() {
        viewModel.onlineData().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        showData(resource.data!!)
                    }
                    Status.ERROR -> {
                        binding.recyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun showData(data: ApiResponse) {
        Log.wtf("DDDDDDDDDD",data.toString())
        articlesAdapter.submitList(data.articles)


    }


}



