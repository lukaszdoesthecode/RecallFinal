package com.example.recall.dst

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
 * Fragment for the Data Structure Test
 */
class StartDST : Fragment() {

    /**
     * Called to have the fragment instantiate its user interface view
     */
    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_dst, container, false)

        val startTextView: TextView = view.findViewById(R.id.start)
        startTextView.setOnClickListener {
            val intent = Intent(activity, DST::class.java)
            startActivity(intent)
        }

        return view
    }
}
