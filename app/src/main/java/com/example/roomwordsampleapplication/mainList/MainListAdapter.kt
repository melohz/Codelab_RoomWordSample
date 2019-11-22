package com.example.roomwordsampleapplication.mainList

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomwordsampleapplication.R
import com.example.roomwordsampleapplication.data.TrainingContents

class MainListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<MainListAdapter.TrainingViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var trainings = emptyList<TrainingContents>()

    inner class TrainingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val trainingDetailTitle: TextView = itemView.findViewById(R.id.detail_title)
        val trainingDetailRep: TextView = itemView.findViewById(R.id.detail_rep)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrainingViewHolder {
        val itemView = inflater.inflate(R.layout.main_list_item, parent, false)
        return TrainingViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TrainingViewHolder, position: Int) {
        val current = trainings[position]
        holder.trainingDetailTitle.text = current.name
        holder.trainingDetailRep.text = current.rep
    }

    internal fun setTrainingContents(trainingContents: List<TrainingContents>) {
        this.trainings = trainingContents
        notifyDataSetChanged()
    }

    override fun getItemCount() = trainings.size
}