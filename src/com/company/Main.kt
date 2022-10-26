package com.company

import java.util.*
import kotlin.jvm.JvmStatic

object Main {
    var input = Scanner(System.`in`)
    @JvmStatic
    fun main(args: Array<String>) {
        startGame()
    }

    fun printBoard(board: Array<CharArray>) {
        println("Game board:")
        for (i in 0..2) {
            for (j in 0..2) {
                print(" " + board[i][j] + " ")
            }
            println()
        }
    }

    fun playerHasWon(board: Array<CharArray>): Char {
        for (i in 0..2) {
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != '-') {
                return board[i][0]
            }
        }
        for (j in 0..2) {
            if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != '-') {
                return board[0][j]
            }
        }
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != '-') {
            return board[0][0]
        }
        return if (board[2][0] == board[1][1] && board[1][1] == board[0][2] && board[2][0] != '-') {
            board[2][0]
        } else ' '
    }

    fun boardIsFull(board: Array<CharArray>): Boolean {
        for (i in 0..2) {
            for (j in 0..2) {
                if (board[i][j] == '-') {
                    return false
                }
            }
        }
        return true
    }

    fun game(cBoard: Array<CharArray>, playerOne: String, playerTwo: String) {
        var isPlayerOne = true
        var gameFinished = false
        while (!gameFinished) {
            printBoard(cBoard)
            if (isPlayerOne) {
                println("It's your turn $playerOne")
            } else {
                println("It's your turn $playerTwo")
            }
            var symbol: Char
            symbol = if (isPlayerOne) {
                'X'
            } else {
                'O'
            }
            var row = 0
            var col = 0
            while (true) {
                print("Move: ")
                val move = input.nextInt()
                when (move) {
                    1 -> {
                        row = 0
                        col = 0
                    }
                    2 -> {
                        row = 0
                        col = 1
                    }
                    3 -> {
                        row = 0
                        col = 2
                    }
                    4 -> {
                        row = 1
                        col = 0
                    }
                    5 -> {
                        row = 1
                        col = 1
                    }
                    6 -> {
                        row = 1
                        col = 2
                    }
                    7 -> {
                        row = 2
                        col = 0
                    }
                    8 -> {
                        row = 2
                        col = 1
                    }
                    9 -> {
                        row = 2
                        col = 2
                    }
                }
                if (cBoard[row][col] != '-') {
                    println("Invalid Move!")
                } else {
                    break
                }
            }
            cBoard[row][col] = symbol
            if (playerHasWon(cBoard) == 'X') {
                printBoard(cBoard)
                println("YOU WIN!! $playerOne")
                gameFinished = true
            } else if (playerHasWon(cBoard) == 'O') {
                printBoard(cBoard)
                println("YOU WIN!! $playerTwo")
                gameFinished = true
            } else {
                printBoard(cBoard)
                if (boardIsFull(cBoard)) {
                    println("It's a tie!")
                    gameFinished = true
                } else {
                    isPlayerOne = !isPlayerOne
                }
            }
        }
    }

    private fun startGame() {
        println("Game board:")
        val cBoard = Array(3) { CharArray(3) }
        for (i in 0..2) {
            for (j in 0..2) {
                cBoard[i][j] = '-'
                print(" " + cBoard[i][j] + " ")
            }
            println()
        }
        print("Enter your name, player 1: ")
        val playerOne = input.nextLine()
        print("Enter your name, player 2: ")
        val playerTwo = input.nextLine()
        var replay: Char
        do {
            game(cBoard, playerOne, playerTwo)
            print("Do you want to play again (Y/N): ")
            replay = input.next()[0]
        } while (replay == 'Y' || replay == 'y')
    }
}