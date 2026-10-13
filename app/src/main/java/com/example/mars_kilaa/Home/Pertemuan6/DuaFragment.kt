package com.example.mars_kilaa.Home.Pertemuan6

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mars_kilaa.R

class DuaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate layout untuk fragment_dua
        return inflater.inflate(R.layout.fragment_dua, container, false)
    }
}