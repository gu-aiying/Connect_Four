package connectfour

class WinGame(private val user: String, private val char: Char, private val body: MutableList<MutableList<String>>) {
    var gameOver: Boolean

    init {
        gameOver =
            horizontalWin() || verticalWin() || diagonalWin() // if the user wins through any method, then game over
    }

    // the player wins by placing four discs in a horizontal row
    private fun horizontalWin(): Boolean {
        for (line in body) {
            var i = 0
            var count = 0
            // while the index isn't out of bound;
            // index i should be less than (column - 1), line.size == column + 1, so index i should be less than (line.size - 2)
            while (i < line.size - 2) {
                while (line[i] == line[i + 1] && line[i + 1] == "║$char") {
                    count += 2
                    i += 2
                    when (count) {
                        4 -> return true // gameOver = true
                    }
                }
                count = 0 // clear the count when it doesn't connect 4
                i++
            }
        }
        return false // gameOver = false
    }


    // the player wins by placing four discs in a vertical row
    private fun verticalWin(): Boolean {
        var count = 0
        for (i in 0..<body[0].size) {
            for (line in body.reversed()) {
                if (line[i] == "║$char") {
                    count++
                } else {
                    count = 0 // clear the count when it doesn't connect 4
                }
                when (count) {
                    4 -> return true // gameOver = true
                }
            }
        }
        return false
    }

    // the player wins by placing four discs in a diagonal row
    private fun diagonalWin(): Boolean {
        val bodyReversed = body.reversed()
        for ((index, line) in body.reversed().withIndex()) {
            if (index <= body.size - 4) { // if index <= body.size - 4, then it still has chance to connect four
                var i = 0
                while (i <= line.size - 4) { // if i <= line.size - 4, then it still has chance to connect four
                    if (bodyReversed[index][i] == "║$char" && bodyReversed[index + 1][i + 1] == "║$char" && bodyReversed[index + 2][i + 2] == "║$char" && bodyReversed[index + 3][i + 3] == "║$char") {
                        return true // gameOver = true
                    }
                    i++
                }
            }
        }
        return false
    }

}