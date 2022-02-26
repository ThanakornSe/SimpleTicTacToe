package com.example.simpletictactoe.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.simpletictactoe.adapter.HistoryAdapter
import com.example.simpletictactoe.databinding.FragmentHistoryBinding
import com.example.simpletictactoe.viewmodel.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HistoryFragment : Fragment() {

    private val binding: FragmentHistoryBinding by lazy {
        FragmentHistoryBinding.inflate(layoutInflater)
    }

    private val historyAdapter: HistoryAdapter by lazy {
        HistoryAdapter()
    }

    private val viewModel: HistoryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding.rvHistory.adapter = historyAdapter

        viewModel.getAllHistory.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                historyAdapter.submitList(it)
                binding.rvHistory.visibility = View.VISIBLE
                binding.btnClear.visibility = View.VISIBLE
                binding.tvNoDataAvailable.visibility = View.GONE
            } else {
                binding.rvHistory.visibility = View.GONE
                binding.btnClear.visibility = View.GONE
                binding.tvNoDataAvailable.visibility = View.VISIBLE
            }
        }

        binding.btnClear.setOnClickListener {
            viewModel.clearHistory()
        }

        return binding.root
    }


}