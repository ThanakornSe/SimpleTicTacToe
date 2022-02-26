package com.example.simpletictactoe.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.simpletictactoe.databinding.FragmentPlayerBinding

class PlayerFragment : Fragment() {

    private lateinit var binding: FragmentPlayerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPlayerBinding.inflate(layoutInflater)

        binding.btnStart.setOnClickListener {
            val name1 = binding.edtPlayer1.text.toString()
            val name2 = binding.edtPlayer2.text.toString()
            val boardSize = binding.edtBoardSize.text.toString()

            if (name1.isNotEmpty() && name2.isNotEmpty() && boardSize.isNotEmpty() && boardSize.toInt() in 1..9) {
                this.findNavController().navigate(
                    PlayerFragmentDirections.actionPlayerFragmentToTicTacToeFragment(
                        arrayOf(
                            name1,
                            name2
                        ), boardSize.toInt()
                    )
                )
            }
        }

        return binding.root
    }

}