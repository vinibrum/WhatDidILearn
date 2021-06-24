package com.devventure.whatdidilearn.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.devventure.whatdidilearn.WhatDidILearnApplication
import com.devventure.whatdidilearn.databinding.ActivityMainBinding
import com.devventure.whatdidilearn.viewmodel.MainViewModel
import com.devventure.whatdidilearn.viewmodel.ViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler = binding.learnedItemsRecyclerView
        val adapter = LearnedItemAdapter()
        recycler.adapter = adapter
      /*
        val repository = (application as WhatDidILearnApplication).repository

        val viewModelFactory = ViewModelFactory(repository)
        val viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
       */

        val items = viewModel.learnedItems
        items.observe(this, Observer{
            adapter.learnedItems = it
        })

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, RegisterLearnedItemActivity::class.java)
            startActivity(intent)
        }

    }
}