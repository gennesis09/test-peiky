package com.example.testpeiky.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testpeiky.R
import com.example.testpeiky.databinding.ActivityMainBinding
import com.example.testpeiky.viewmodel.MoviesViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MoviesViewModel
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        bindView()
        initRecycler()
        viewModel.fetchData()
    }

    private fun initRecycler() {
        moviesAdapter = MoviesAdapter(this)
        binding.list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moviesAdapter
        }
    }

    private fun bindView() {
        viewModel.moviesLiveData.observe(this, Observer {
            moviesAdapter.updateItems(it)
        })
        viewModel.errorLiveData.observe(this, Observer {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        })
    }


}
