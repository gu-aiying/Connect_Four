package connectfour

fun main() {
    // print game name
    println("Connect Four")
    // ask for players' names
    println("First player's name:")
    val user1 = readln().trim() // trim()去除开头和结尾的任意空白
    println("Second player's name:")
    val user2 = readln().trim()
    val initialBoard = InitBoard(user1, user2)
    val boardBody = initialBoard.body
    val col = initialBoard.column
    val totalGames = initialBoard.totalGames
    PlayGame(user1, user2, col, boardBody, totalGames)
}