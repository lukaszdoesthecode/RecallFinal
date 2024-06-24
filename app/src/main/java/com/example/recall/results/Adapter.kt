package com.example.recall.results

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recall.R

/**
 * Adapter for the RecyclerView in the PastGamesFragment.
 *
 * @param list List of DataGames objects to be displayed in the RecyclerView.
 */
class Adapter(private val list: List<DataGames>) :
    RecyclerView.Adapter<Adapter.UpcomingViewHolder>() {

    /**
     * Inner class for the ViewHolder.
     */
    inner class UpcomingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gameType: TextView = itemView.findViewById(R.id.gameType)
        val totalCorrect: TextView = itemView.findViewById(R.id.totalCorrect)
        val totalErrors: TextView = itemView.findViewById(R.id.totalErrors)
        val phoneticErrors: TextView = itemView.findViewById(R.id.phoneticErrors)
        val semanticErrors: TextView = itemView.findViewById(R.id.semanticErrors)
        val perseverativeErrors: TextView = itemView.findViewById(R.id.perseverativeErrors)
        val cueUtilization: TextView = itemView.findViewById(R.id.cueUtilization)
        val averageTime: TextView = itemView.findViewById(R.id.averageTime)
        val spanTime: TextView = itemView.findViewById(R.id.spanTime)
        val correctAnswers: TextView = itemView.findViewById(R.id.correctAnswers)
        val incorrectAnswers: TextView = itemView.findViewById(R.id.incorrectAnswers)
        val date: TextView = itemView.findViewById(R.id.date)
    }

    /**
     * Inflates the layout for the ViewHolder.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpcomingViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_past_games, parent, false)
        return UpcomingViewHolder(view)
    }

    /**
     * Binds the data to the ViewHolder.
     */
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: UpcomingViewHolder, position: Int) {
        val item = list[position]
        holder.gameType.text = "Game Type: ${item.gameType}"
        holder.totalCorrect.text = "Total Correct: ${item.totalCorrect ?: "N/A"}"
        holder.totalErrors.text = "Total Errors: ${item.totalErrors ?: "N/A"}"
        holder.phoneticErrors.text = "Phonetic Errors: ${item.phoneticErrors ?: "N/A"}"
        holder.semanticErrors.text = "Semantic Errors: ${item.semanticErrors ?: "N/A"}"
        holder.perseverativeErrors.text = "Perseverative Errors: ${item.perseverativeErrors ?: "N/A"}"
        holder.cueUtilization.text = "Cue Utilization: ${item.cueUtilization ?: "N/A"}"
        holder.averageTime.text = "Average Time: ${item.averageTime ?: "N/A"}"
        holder.spanTime.text = "Span Time: ${item.spanTime ?: "N/A"}"
        holder.correctAnswers.text = "Correct Answers: ${item.correctAnswers ?: "N/A"}"
        holder.incorrectAnswers.text = "Incorrect Answers: ${item.incorrectAnswers ?: "N/A"}"
        holder.date.text = "Date: ${item.date}"
    }

    /**
     * Returns the number of items in the list.
     */
    override fun getItemCount(): Int {
        return list.size
    }
}
