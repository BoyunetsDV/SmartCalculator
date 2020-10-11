import java.util.*

fun main() {
    val words = mutableMapOf<String, Int>()
    var maxWordCount = 0
    var maxWord = ""

    val scanner = Scanner(System.`in`)
    do {
        val word = scanner.nextLine()

        if (word == "stop") {
            continue
        }

        if (!words.containsKey(word)) {
            words[word] = 1
        } else {
            words[word] = words[word]!! + 1
        }

        if (words[word]!! > maxWordCount) {
            maxWordCount = words[word]!!
            maxWord = word
        }
    } while (word != "stop")

    if (maxWord.isEmpty()) {
        println("null")
    } else {
        println(maxWord)
    }
}