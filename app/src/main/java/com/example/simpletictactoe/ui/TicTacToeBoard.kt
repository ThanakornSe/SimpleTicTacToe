package com.example.simpletictactoe.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.simpletictactoe.R
import kotlin.math.ceil

class TicTacToeBoard(context: Context, attrs: AttributeSet?) :
    View(context, attrs) {

    var boardSize: Int = 3
    private var boardColor: Int = Color.BLACK
    private var xColor: Int = Color.RED
    private var oColor: Int = Color.BLUE
    private var wonLineColor: Int = Color.GREEN
    private val paint = Paint()
    private var cellSize = width / boardSize
    private val game: Game by lazy { Game(boardSize) }
    private var winningLine = false

    init {
        val a: TypedArray =
            context.theme.obtainStyledAttributes(attrs, R.styleable.TicTacToeBoard, 0, 0)
        try {
            boardColor = a.getInteger(R.styleable.TicTacToeBoard_boardColor, 0)
            xColor = a.getInteger(R.styleable.TicTacToeBoard_XColor, 0)
            oColor = a.getInteger(R.styleable.TicTacToeBoard_OColor, 0)
            wonLineColor = a.getInteger(R.styleable.TicTacToeBoard_WonLineColor, 0)
        } finally {
            a.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        //get smallest size of any devices and set the dimension to it
        val dimension = measuredWidth.coerceAtMost(measuredHeight)
        cellSize = dimension / boardSize
        setMeasuredDimension(dimension, dimension)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.style = Paint.Style.STROKE
        paint.isAntiAlias = true
        drawGameBoard(canvas)
        drawMarker(canvas)

        if (winningLine) {
            paint.color = wonLineColor
            drawWinningLine(canvas)
        }
    }

    private fun drawMarker(canvas: Canvas) {
        for (r in 0 until boardSize) {
            for (c in 0 until boardSize) {
                if (game.gameBoard[r][c] != 0) {
                    if (game.gameBoard[r][c] == 1) {
                        drawX(canvas, r, c)
                    } else {
                        drawO(canvas, r, c)
                    }
                }
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event?.x
        val y = event?.y

        val action = event?.action
        if (action == MotionEvent.ACTION_DOWN) {
            if (x != null && y != null) {

                val row = y.div(cellSize).let { ceil(it.toDouble()).toInt() }
                val col = x.div(cellSize).let { ceil(it.toDouble()).toInt() }

                if (!winningLine) {
                    if (game.updateGameBoard(row, col)) {
                        invalidate()
                        when {
                            game.isGameFinished() == 1 -> {
                                winningLine = true
                                invalidate()
                            }
                            game.isGameFinished() == 2 -> {
                                winningLine = true
                                invalidate()
                            }
                            else -> {
                                winningLine = false
                            }
                        }
                        invalidate() // redraw on screen
                    }
                }
            }
            return true
        }
        return false
    }

    private fun drawGameBoard(canvas: Canvas) {
        paint.color = boardColor
        paint.strokeWidth = 16f
        for (c in 1 until boardSize) {
            canvas.drawLine(
                (cellSize * c).toFloat(),
                0f,
                (cellSize * c).toFloat(),
                canvas.width.toFloat(),
                paint
            )
        }
        for (r in 1 until boardSize) {
            canvas.drawLine(
                0f,
                (cellSize * r).toFloat(),
                canvas.width.toFloat(),
                (cellSize * r).toFloat(),
                paint
            )
        }
    }

    private fun drawX(canvas: Canvas, row: Int, col: Int) {
        paint.color = xColor
        canvas.drawLine(
            ((col + 1) * cellSize - cellSize * 0.2).toFloat(), //5.4f start X
            (row * cellSize + cellSize * 0.2).toFloat(),//3.6 start Y
            (col * cellSize + cellSize * 0.2).toFloat(),//3.6 stop X
            ((row + 1) * cellSize - cellSize * 0.2).toFloat(), //5.4 stop Y
            paint
        )
        canvas.drawLine(
            (col * cellSize + cellSize * 0.2).toFloat(), //3.6 start x
            (row * cellSize + cellSize * 0.2).toFloat(), //3.6 start y
            ((col + 1) * cellSize - cellSize * 0.2).toFloat(), //stop x 5.4
            ((row + 1) * cellSize - cellSize * 0.2).toFloat(), //stop y 5.4
            paint
        )
    }

    private fun drawO(canvas: Canvas, row: Int, col: Int) {
        paint.color = oColor

        canvas.drawOval(
            (col * cellSize + cellSize * 0.2).toFloat(), //3.6
            (row * cellSize + cellSize * 0.2).toFloat(), //3.6
            ((col * cellSize) + cellSize - cellSize * 0.2).toFloat(), //5.4
            ((row * cellSize) + cellSize - cellSize * 0.2).toFloat(), //5.4
            paint
        )
    }

    private fun drawHorizontal(canvas: Canvas, row: Int, col: Int) {
        canvas.drawLine(
            col.toFloat(),
            (row * cellSize + cellSize / 2).toFloat(),
            (cellSize * 3).toFloat(),
            (row * cellSize + cellSize / 2).toFloat(),
            paint
        )
    }

    private fun drawVertical(canvas: Canvas, row: Int, col: Int) {
        canvas.drawLine(
            (col * cellSize + cellSize / 2).toFloat(),
            row.toFloat(),
            (col * cellSize + cellSize / 2).toFloat(),
            (cellSize * 3).toFloat(),
            paint
        )
    }

    private fun drawDiagonalLinePos(canvas: Canvas) {
        canvas.drawLine(0f, (cellSize * 3).toFloat(), (cellSize * 3).toFloat(), 0f, paint)
    }

    private fun drawDiagonalLineNeg(canvas: Canvas) {
        canvas.drawLine(0f, 0f, (cellSize * 3).toFloat(), (cellSize * 3).toFloat(), paint)
    }

    private fun drawWinningLine(canvas: Canvas) {
        val row = game.winType[0]
        val col = game.winType[1]

        when (game.winType[2]) {
            1 -> drawHorizontal(canvas, row, col)
            2 -> drawVertical(canvas, row, col)
            3 -> drawDiagonalLineNeg(canvas)
            4 -> drawDiagonalLinePos(canvas)
        }
    }

    fun setUpGame(playAgain: Button, home: Button, playerTurn: TextView, name: Array<String>) {
        game.playAgainBtn = playAgain
        game.homeBtn = home
        game.playerTurn = playerTurn
        game.playerName = name
    }

    fun resetGame() {
        game.resetGame(boardSize)
        winningLine = false
    }
}
