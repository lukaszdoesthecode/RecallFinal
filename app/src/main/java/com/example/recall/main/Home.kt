package com.example.recall.main

import android.media.MediaPlayer
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.recall.R
import com.example.recall.bnt.StartBNT
import com.example.recall.databinding.ActivityHomeBinding
import com.example.recall.dst.StartDST
import com.example.recall.results.PastResults
import com.example.recall.sst.StartSST
import com.example.recall.st.StartST

/**
 * Home activity that contains the bottom navigation menu and the fragments for each test.
 * The activity also plays a sound when the user navigates to a different fragment.
 */
class Home : AppCompatActivity() {
    private lateinit var mediaPlayerBNT: MediaPlayer
    private lateinit var mediaPlayerDST: MediaPlayer
    private lateinit var mediaPlayerSST: MediaPlayer
    private lateinit var mediaPlayerST: MediaPlayer
    private lateinit var mediaPlayerRES: MediaPlayer

    private lateinit var binding: ActivityHomeBinding

    /**
     * Initializes the activity and the bottom navigation menu.
     * The activity also plays a sound when the user navigates to a different fragment.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mediaPlayerBNT = MediaPlayer.create(this, R.raw.bnt)
        mediaPlayerDST = MediaPlayer.create(this, R.raw.dst)
        mediaPlayerSST = MediaPlayer.create(this, R.raw.sst)
        mediaPlayerST = MediaPlayer.create(this, R.raw.st)
        mediaPlayerRES = MediaPlayer.create(this, R.raw.res)

        val fragmentToLoad = intent.getStringExtra("fragmentToLoad")
        if (fragmentToLoad != null) {
            when (fragmentToLoad) {
                "StartSST" -> {
                    replaceFragment(StartSST())
                    binding.menu.selectedItemId = R.id.nav_sst
                }
                "StartDST" -> {
                    replaceFragment(StartDST())
                    binding.menu.selectedItemId = R.id.nav_dst
                }
                "StartBNT" -> {
                    replaceFragment(StartBNT())
                    binding.menu.selectedItemId = R.id.nav_bnt
                }
                "StartST" -> {
                    replaceFragment(StartST())
                    binding.menu.selectedItemId = R.id.nav_st
                }
                "PastResults" -> {
                    replaceFragment(PastResults())
                    binding.menu.selectedItemId = R.id.nav_res
                }
            }
        } else {
            replaceFragment(StartSST())
            binding.menu.selectedItemId = R.id.nav_sst
        }

        binding.menu.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_bnt -> {
                    replaceFragment(StartBNT())
                    mediaPlayerBNT.start()
                }
                R.id.nav_dst -> {
                    replaceFragment(StartDST())
                    mediaPlayerDST.start()
                }
                R.id.nav_sst -> {
                    replaceFragment(StartSST())
                    mediaPlayerSST.start()
                }
                R.id.nav_st -> {
                    replaceFragment(StartST())
                    mediaPlayerST.start()
                }
                R.id.nav_res -> {
                    replaceFragment(PastResults())
                    mediaPlayerRES.start()
                }
            }
            true
        }
    }

    /**
     * Replaces the current fragment with the new fragment.
     */
    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

    /**
     * Releases the media players when the activity is destroyed.
     */
    override fun onDestroy() {
        super.onDestroy()
        mediaPlayerBNT.release()
        mediaPlayerDST.release()
        mediaPlayerSST.release()
        mediaPlayerST.release()
        mediaPlayerRES.release()
    }
}
