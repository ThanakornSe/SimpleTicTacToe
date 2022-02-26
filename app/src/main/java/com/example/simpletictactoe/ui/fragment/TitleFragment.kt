package com.example.simpletictactoe.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.simpletictactoe.databinding.FragmentTitleBinding


class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTitleBinding.inflate(layoutInflater)

        binding.btnPlay.setOnClickListener {
            this.findNavController()
                .navigate(TitleFragmentDirections.actionTitleFragmentToPlayerFragment())
        }

        binding.btnHistory.setOnClickListener {
            this.findNavController()
                .navigate(TitleFragmentDirections.actionTitleFragmentToHistoryFragment())
        }

        return binding.root
    }

}