package com.example.mars_kilaa.Home.pertemuan10

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mars_kilaa.R

class TabAFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout fragment_tab_a
        return inflater.inflate(R.layout.fragment_tab_a, container, false)
    }
}