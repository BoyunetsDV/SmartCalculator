package calculator

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    do {
        val input = scanner.nextLine()
        if (isAdditionalCommand(input)) {
            executeAdditionalCommand(input)
            continue
        }

        if (isTheInputStringInvalid(input)) {
            continue
        }

        val numbers = input.split(" ")
                .filter { it -> it.isNotEmpty() }
                .map { it -> it.toInt() }
        if (numbers.size == 1) {
            println(numbers[0])
            continue
        }
        calculate(numbers)
    } while (input != "/exit")
    println("Bye!")
}

fun isTheInputStringInvalid(input: String): Boolean {
    return input.isEmpty()
}

fun isAdditionalCommand(input: String): Boolean {
    val commands = listOf("/exit", "/help")
    return commands.contains(input)
}

fun executeAdditionalCommand(input: String) {
    when (input) {
        "/help" -> println("The program calculates the sum of numbers")
    }
}

fun calculate(numbers: List<Int>) {
    var result = 0
    for (number in numbers) {
        result += number
    }
    println(result)
}