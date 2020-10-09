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

        val sequence = input.split(" ").filter { it -> it.isNotEmpty() }
        calculate(sequence)
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
        "/help" -> println("The program calculates the result of some arithmetic operations. It supports " +
                "addition and subtraction operators." +
                " Consider that the even number of minuses gives a plus, and the odd number of minuses gives a minus!")
    }
}

fun calculate(sequence: List<String>) {
    var result = sequence[0].toInt()
    for (index in 1..sequence.lastIndex step 2) {
        if (index + 1 <= sequence.lastIndex) {
            result = doArithmeticOperation(result, sequence[index], sequence[index + 1])
        }
    }
    println(result)
}

fun doArithmeticOperation(currentValue: Int, operation: String, value: String): Int {
    when {
        operation[0] == '-' && operation.length % 2 == 1 -> return currentValue - value.toInt()
        operation[0] == '-' || operation[0] == '+' -> return currentValue + value.toInt()
    }
    return currentValue
}