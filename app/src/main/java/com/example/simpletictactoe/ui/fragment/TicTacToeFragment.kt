package com.example.simpletictactoe.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.simpletictactoe.databinding.FragmentTicTacToeBinding
import com.example.simpletictactoe.model.History
import com.example.simpletictactoe.ui.TicTacToeBoard
import com.example.simpletictactoe.viewmodel.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class TicTacToeFragment : Fragment() {

    private val binding: FragmentTicTacToeBinding by lazy {
        FragmentTicTacToeBinding.inflate(layoutInflater)
    }

    private val viewModel: HistoryViewModel by viewModel()

    private lateinit var tic: TicTacToeBoard

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val playerName = TicTacToeFragmentArgs.fromBundle(requireArguments()).playerName
        val boardSize = TicTacToeFragmentArgs.fromBundle(requireArguments()).boardSize

        tic = binding.ticTacToeBoard

        binding.apply {
            btnHome.visibility = View.GONE
            btnPlayAgain.visibility = View.GONE
        }

        tic.boardSize = boardSize
        tic.invalidate()

        tic.setUpGame(binding.btnPlayAgain, binding.btnHome, binding.txtPlayerTurn, playerName)

        binding.btnPlayAgain.setOnClickListener {
            tic.resetGame()
            tic.invalidate()
            viewModel.insert(
                History(
                    player1 = playerName[0],
                    player2 = playerName[1],
                    playerWon = binding.txtPlayerTurn.text.toString(),
                    boardSize = boardSize.toString()
                )
            )
        }

        binding.btnHome.setOnClickListener {
            this.findNavController().navigate(
                TicTacToeFragmentDirections.actionTicTacToeFragmentToTitleFragment()
            )
            viewModel.insert(
                History(
                    player1 = playerName[0],
                    player2 = playerName[1],
                    playerWon = binding.txtPlayerTurn.text.toString(),
                    boardSize = boardSize.toString()
                )
            )
        }


        return binding.root
    }


}