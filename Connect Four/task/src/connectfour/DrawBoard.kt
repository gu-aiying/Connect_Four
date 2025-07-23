package connectfour

class DrawBoard(column: Int, body: MutableList<MutableList<String>>) {

    init {
        // print the first line
        var start = " "
        for (i in 1..column) {
            start += "$i "
        }
        println(start)
        // print body
        for (line in body) {
            println(line.joinToString("").trim())
        }
        // print the last line
        var last = "═"
        repeat(column - 1){ last += "╩═"}
        print("╚$last╝\n")
    }

}