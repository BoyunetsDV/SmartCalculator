package calculator

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    do {
        val input = scanner.nextLine()
        if (input.isEmpty() || input == "/exit") {
            continue
        }

        val numbers = input.split(" ").map { it -> it.toInt() }
        if (numbers.size == 1) {
            println(numbers[0])
            continue
        }
        calculate(numbers)
    } while (input != "/exit")
    println("Bye!")
}

fun calculate(numbers: List<Int>) {
    println(numbers[0] + numbers[1])
}
