package com.example.simpletictactoe.ui

import android.view.View
import android.widget.Button
import android.widget.TextView

class Game(private val boardSize: Int) {

    var gameBoard: Array<IntArray> = Array(boardSize) { IntArray(boardSize) }
    var player = 1
    var playAgainBtn: Button? = null
    var homeBtn: Button? = null
    var playerTurn: TextView? = null
    var playerName: Array<String> = arrayOf("Player 1", "Player 2")

    //1st Element = row, 2nd Element = col, 3rd element = line type
    var winType = intArrayOf(-1, -1, -1)

    init {
        insertDefaultValue(boardSize)
    }

    private fun insertDefaultValue(size: Int) {
        for (r in 0 until size) {
            for (c in 0 until size) {
                gameBoard[r][c] = 0
            }
        }
    }

    fun updateGameBoard(row: Int, col: Int): Boolean {
        //this to check whether we already click it or not
        //if it clicked gameBoard[row][col] = 1 then will return false
        return if (gameBoard[row - 1][col - 1] == 0) {
            gameBoard[row - 1][col - 1] = player

            //updating player turn
            if (player % 2 == 0) {
                player -= 1
            } else {
                player += 1
            }

            if (player == 1) {
                playerTurn?.text = "${playerName[0]}'s Turn (X)"
            } else {
                playerTurn?.text = "${playerName[1]}'s Turn (O)"
            }
            true
        } else {
            false
        }
    }

    fun isGameFinished(): Int {
        when {
            isDraw() -> {
                playAgainBtn?.visibility = View.VISIBLE
                homeBtn?.visibility = View.VISIBLE
                playerTurn?.text = "Tie Game!!!!"
                return DRAW
            }
            winnerCheck(boardSize, 1) -> {
                playAgainBtn?.visibility = View.VISIBLE
                homeBtn?.visibility = View.VISIBLE
                playerTurn?.text = "${playerName[0]} Won!!!!"
                return PLAYER1
            }
            winnerCheck(boardSize, 2) -> {
                playAgainBtn?.visibility = View.VISIBLE
                homeBtn?.visibility = View.VISIBLE
                playerTurn?.text = "${playerName[1]} Won!!!!"
                return PLAYER2
            }
            // can't be 0 || 1 || 2
            else -> return -1
        }
    }

    private fun winnerCheck(size: Int, playerMark: Int): Boolean {

        // horizontal check
        for (r in 0 until size) {
            if (gameBoard[r][0] == playerMark) {
                var c = 1
                while (c < size) {
                    if (gameBoard[r][c] != playerMark) {
                        break
                    }
                    c++
                }
                if (c == size) {
                    winType = intArrayOf(r, 0, 1)
                    return true
                }
            }
        }

        // vertical check
        for (c in 0 until size) {
            if (gameBoard[0][c] == playerMark) {
                var r = 1
                while (r < size) {
                    if (gameBoard[r][c] != playerMark) {
                        break
                    }
                    r++
                }
                if (r == size) {
                    winType = intArrayOf(0, c, 2)
                    return true
                }
            }
        }

        // diagonals check Negative
        var i = 0
        while (i < size) {
            if (gameBoard[i][i] != playerMark) {
                break
            }
            i++
        }
        if (i == size) {
            winType = intArrayOf(0, i - 1, 3)
            return true
        }
        //diagonals check positive
        i = 0
        while (i < size) {
            if (gameBoard[i][size - 1 - i] != playerMark) {
                break
            }
            i++
        }
        winType = intArrayOf(i - 1, i - 1, 4)
        return i == size
    }

    private fun isDraw(): Boolean {
        for (r in 0 until boardSize) {
            for (c in 0 until boardSize) {
                if (gameBoard[r][c] == 0) {
                    return false
                }
            }
        }
        return true
    }

    fun resetGame(size: Int) {
        for (r in 0 until size) {
            for (c in 0 until size) {
                gameBoard[r][c] = 0
            }
        }

        playAgainBtn?.visibility = View.GONE
        homeBtn?.visibility = View.GONE
        //playerTurn?.text = "${playerName[0]}'s Turn"
        player = 1
    }

    companion object {
        const val DRAW = 0 // game ends as a draw
        const val PLAYER1 = 1 // player1 wins
        const val PLAYER2 = 2// player2 wins
    }
}