package com.example.recall.sst

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.recall.R

/**
 * Fragment to start the SST game
 */
class StartSST : Fragment() {
    /**
     * Function to create the fragment
     * @param inflater: LayoutInflater - The layout inflater
     * @param container: ViewGroup? - The view group
     * @param savedInstanceState: Bundle? - The saved instance state
     * @return View? - The created view
     */
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_sst, container, false)

        val startTextView: TextView = view.findViewById(R.id.start)
        startTextView.setOnClickListener {
            val intent = Intent(activity, SST::class.java)
            startActivity(intent)
        }

        return view
    }
}
