fun main() {
    val input = readLine()!!
    if (input.startsWith('i')) {
        println(input.drop(1).toInt() + 1)
    } else if (input.startsWith('s')) {
        println(input.drop(1).reversed())
    } else {
        println(input)
    }
}