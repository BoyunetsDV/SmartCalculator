import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val letters = mutableListOf<String>()
    do {
        val letter = scanner.nextLine()
        letters.add(letter)
    } while (letter != "z")

    if (letters.size < 4) {
        println("null")
    } else {
        println(letters[3])
    }
}