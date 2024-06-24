package com.example.recall.results

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recall.R

/**
 * Fragment for displaying the past results of the games played by the user.
 */
class PastResults : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: Adapter
    private val gameDataList = mutableListOf<DataGames>()

    /**
     * Inflates the layout for the fragment.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_past_results, container, false)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = Adapter(gameDataList)
        recyclerView.adapter = adapter

        fetchGameData { data ->
            gameDataList.clear()
            gameDataList.addAll(data)
            adapter.notifyDataSetChanged()
        }

        return view
    }
}
