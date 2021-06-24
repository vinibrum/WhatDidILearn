package com.devventure.whatdidilearn.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.devventure.whatdidilearn.data.LearnedItemRepository
import com.devventure.whatdidilearn.entities.LearnedItem

class MainViewModel(
    repository: LearnedItemRepository
): ViewModel() {
    val learnedItems: LiveData<List<LearnedItem>> = repository.learnedItems
}