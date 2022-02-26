package com.example.simpletictactoe.adapter

import android.text.format.DateUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.simpletictactoe.databinding.ItemHistoryBinding
import com.example.simpletictactoe.model.History
import java.text.SimpleDateFormat
import java.util.*
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

class HistoryAdapter : ListAdapter<History, HistoryAdapter.ViewHolder>(HistoryDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    class ViewHolder(private val binding: ItemHistoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: History) {
            binding.txtPlayer1.text = "(X): ${item.player1}"
            binding.txtPlayer2.text = "(O): ${item.player2}"
            binding.txtBoardSize.text = "${item.boardSize}x${item.boardSize}"
            binding.txtPlayerWon.text = item.playerWon
            binding.txtDate.text = formatDate(item.date)
        }

        private fun formatDate(dueDate: Date): String {
            val simpleDateFormat: SimpleDateFormat =
                SimpleDateFormat.getDateInstance() as SimpleDateFormat
            simpleDateFormat.applyPattern("EEE, MMM d : HH.mm")
            return simpleDateFormat.format(dueDate)
        }
    }


}

class HistoryDiffUtil : DiffUtil.ItemCallback<History>() {
    override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
        return oldItem == newItem
    }
}