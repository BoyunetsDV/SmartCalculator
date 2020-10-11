fun main() {
    val letter = readLine()!!.toLowerCase()
    val vowels = mapOf<String, Int>("a" to 1, "e" to 5, "i" to 9, "o" to 15, "u" to 21)
    if (vowels.containsKey(letter)) {
        println(vowels[letter])
    } else {
        println(0)
    }
}