package com.devventure.whatdidilearn.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.devventure.whatdidilearn.R
import com.devventure.whatdidilearn.data.LearnedItem
import com.devventure.whatdidilearn.data.UnderstandingLevel

class LearnedItemAdapter: RecyclerView.Adapter<LearnedItemAdapter.LearnedItemViewHolder>() {
    var learnedItems = listOf<LearnedItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LearnedItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.learned_item, parent, false)

        return LearnedItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: LearnedItemViewHolder, position: Int) {
        val learnedItem = learnedItems[position]
        holder.bind(learnedItem)
    }

    override fun getItemCount(): Int {
        return learnedItems.size
    }

    inner class LearnedItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private var titleContainer = itemView.findViewById<TextView>(R.id.learnedItemTitle)
        private val descriptionContainer = itemView.findViewById<TextView>(R.id.learnedItemDescription)
        private val levelContainer = itemView.findViewById<View>(R.id.view)

        fun bind(learnedItem: LearnedItem) {
            titleContainer.text = learnedItem.name
            descriptionContainer.text = learnedItem.description
            levelContainer.setBackgroundResource(
                when(learnedItem.understandingLevel) {
                    UnderstandingLevel.LOW -> R.color.low_understanding_color
                    UnderstandingLevel.MEDIUM -> R.color.medium_understanding_color
                    UnderstandingLevel.HIGH -> R.color.high_understanding_color
                }
            )
        }

    }
}