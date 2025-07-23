package connectfour

class InitBoard(user1: String, user2: String, private var row: Int = 6, var column: Int = 7) {
    var body = MutableList(row) { MutableList(column + 1) { "║ " } }
    var totalGames = 0
    init {
        while (true) {
            // determine dimensions
            println(
                "Set the board dimensions (Rows x Columns)\n" +
                        "Press Enter for default (6 x 7)"
            )

            val input = readln().trim()
            if (input.isNotBlank()) {
                // check dimensions format
                val regex = Regex("\\d+\\s*[xX]\\s*\\d+") // \\d+匹配连续数字(一个或多个); \\s*匹配0个或多个任意空白

                if (!regex.matches(input)) {
                    println("Invalid input")
                } else {
                    // check if numbers are in range [5, 9]
                    val (rowStr, columnStr) = input.lowercase().split("x")
                    row = rowStr.trim().toInt()
                    column = columnStr.trim().toInt()
                    when {
                        row !in 5..9 -> println("Board rows should be from 5 to 9")
                        column !in 5..9 -> println("Board columns should be from 5 to 9")
                        else -> {
                            body = MutableList(row) { MutableList(column + 1) { "║ " } }
                            break
                        }
                    }
                }
            } else break // if the input is empty, then end the loop
        }

        println("Do you want to play single or multiple games?\n" +
                "For a single game, input 1 or press Enter\n" +
                "Input a number of games:")
        val input = readln().trim()
        println("$user1 VS $user2")
        println("$row X $column board")
        if (input.isEmpty()) {
            totalGames = 1
            println("Single game")
        } else {
            while (true) {
                try {
                    val gameTurn = input.toInt()
                    if (gameTurn <= 0) {
                        println("Invalid input")
                        continue
                    }
                    when (gameTurn) {
                        1 -> {
                            totalGames = 1
                            println("Single game")
                            break
                        }
                        else -> {
                            totalGames = gameTurn
                            println("Total $totalGames games")
                            println("Game #1")
                            break
                        }

                    }
                } catch (e: NumberFormatException) {
                    println("Invalid input")
                }

            }
        }
        DrawBoard(column, body)
    }

}