package connectfour

import kotlin.system.exitProcess

class PlayGame(
    private val user1: String,
    private val user2: String,
    private val column: Int,
    private var body: MutableList<MutableList<String>>,
    private val totalGames: Int,
) {
    private var gameOver = false
    private var startToCheckUser = 3
    private var totalStep = body.size * column
    private var user1Sum = 0
    private var user2Sum = 0
    private var gameCount = 1
    private var firstGo = user1
    private var secondGo = user2
    private val initialBoardBody = body.map { it.toMutableList() }.toMutableList() // 使用深拷贝，保证内外层均使用复制，而不是引用拷贝

    init {
        while (gameCount <= totalGames) {
            gameOver = false // game starts again
            body = initialBoardBody.map { it.toMutableList() }.toMutableList() // clear previous body content
            totalStep = body.size * column // total step refreshes

            while (!gameOver) {
                // determine disc symbol for the firstGo user
                val symbol = when (firstGo) {
                    user1 -> 'ο'
                    user2 -> '*'
                    else -> {
                        throw Exception("Something went wrong")
                    }
                }
                oneUserPlay(firstGo, symbol, secondGo)
                firstGo = when (firstGo) {
                    user1 -> user2
                    user2 -> user1
                    else -> throw Exception("Something went wrong")
                }
                secondGo = when (secondGo) {
                    user1 -> user2
                    user2 -> user1
                    else -> throw Exception("Something went wrong")
                }
            }
        }

    }

    private fun makeStep(user: String, char: Char) {
        var boardSet = false
        loop@ while (true) {
            println("$user's turn:")
            val input = readln().trim()
            if (input == "end") {
                println("Game over!")
                gameOver = true
                exitProcess(0)
            }
            try {
                when (val place = input.toInt()) {
                    in 1..column -> {
                        // change the char in the board if it is empty
                        for (line in body.reversed()) {
                            if (line[place - 1] == "║ ") {
                                line[place - 1] = "║$char"
                                // draw board
                                DrawBoard(column, body)
                                boardSet = true
                                break@loop
                            }
                        }
                        if (!boardSet) { // if there isn't any empty place in the column
                            println("Column $place is full")
                        }
                    }

                    else -> println("The column number is out of range (1 - $column)")
                }

            } catch (e: NumberFormatException) {
                println("Incorrect column number")
            }
        }
    }

    private fun oneUserPlay(
        firstUser: String,
        char: Char,
        secondUser: String
    ) {
        if (totalStep > 0) {
            makeStep(firstUser, char)
            totalStep--

            if (startToCheckUser > 0) {
                startToCheckUser--
            } else {
                // check if user1 wins after he makes four steps
                val checkIfUserWins = WinGame(firstUser, char, body)
                gameOver = checkIfUserWins.gameOver
                if (gameOver) {
                    when (totalGames) {
                        1 -> {
                            println(
                                "Player $firstUser won\n" +
                                "Game over!"
                            )
                            exitProcess(0)
                        }

                        else -> {
                            gameCount++
                            println("Player $firstUser won\n" +
                                    "Score")
                            when (firstUser) {
                                user1 -> {
                                    user1Sum += 2
                                    println("$firstUser: $user1Sum $secondUser: $user2Sum")
                                }
                                user2 -> {
                                    user2Sum += 2
                                    println("$firstUser: $user2Sum $secondUser: $user1Sum")
                                }
                            }
                            println("Game #$gameCount")
                        }
                    }
                }
            }
        } else {
            gameOver = true // if there is no empty place, then game over
            when (totalGames) {
                1 -> {
                    println("It is a draw\n" +
                            "Game over!"
                    )
                    exitProcess(0)
                }
                else -> {
                    user1Sum++
                    user2Sum++
                    gameCount++
                    println(
                        "It is a draw\n" +
                        "Score"
                    )
                    when (firstUser) {
                        user1 -> {
                            println("$firstUser: $user1Sum $secondUser: $user2Sum")
                        }
                        user2 -> {
                            println("$firstUser: $user2Sum $secondUser: $user1Sum")
                        }
                    }
                    println("Game #$gameCount")
                }
            }
        }
    }
}




