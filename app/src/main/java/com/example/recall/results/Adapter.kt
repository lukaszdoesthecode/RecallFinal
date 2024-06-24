package com.example.recall.results

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recall.R
import java.text.SimpleDateFormat
import java.util.*

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

        holder.gameType.text = item.gameType

        bindTextView(holder.totalCorrect, "Total Correct: ${item.totalCorrect}", item.totalCorrect)
        bindTextView(holder.totalErrors, "Total Errors: ${item.totalErrors}", item.totalErrors)
        bindTextView(holder.phoneticErrors, "Phonetic Errors: ${item.phoneticErrors}", item.phoneticErrors)
        bindTextView(holder.semanticErrors, "Semantic Errors: ${item.semanticErrors}", item.semanticErrors)
        bindTextView(holder.perseverativeErrors, "Perseverative Errors: ${item.perseverativeErrors}", item.perseverativeErrors)
        bindTextView(holder.cueUtilization, "Cue Utilization: ${item.cueUtilization}", item.cueUtilization)
        bindTextView(holder.averageTime, "Average Time: ${item.averageTime} s", item.averageTime)
        bindTextView(holder.spanTime, "Span Time: ${item.spanTime} s", item.spanTime)
        bindTextView(holder.correctAnswers, "Correct Answers: ${item.correctAnswers}", item.correctAnswers)
        bindTextView(holder.incorrectAnswers, "Incorrect Answers: ${item.incorrectAnswers}", item.incorrectAnswers)

        val formattedDate = formatDate(item.date)
        bindTextView(holder.date, formattedDate, item.date)
    }

    /**
     * Helper function to bind the text to the TextView and set visibility.
     */
    private fun bindTextView(textView: TextView, text: String, data: Any?) {
        if (data != null) {
            textView.text = text
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
        }
    }

    /**
     * Helper function to format the date.
     */
    private fun formatDate(date: Date?): String {
        return if (date != null) {
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            sdf.format(date)
        } else {
            "N/A"
        }
    }

    /**
     * Returns the number of items in the list.
     */
    override fun getItemCount(): Int {
        return list.size
    }
}
