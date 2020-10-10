package calculator

import java.lang.Exception
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
        try {
            val sequence = input.split(" ").filter { it -> it.isNotEmpty() }
            calculate(sequence)
        } catch (e: Exception) {
            println("Invalid expression")
        }
    } while (input != "/exit")
    println("Bye!")
}

fun isAdditionalCommand(input: String): Boolean {
    return input.startsWith('/')
}

fun executeAdditionalCommand(input: String) {
    when (input) {
        "/help" -> println("The program calculates the result of some arithmetic operations. It supports " +
                "addition and subtraction operators." +
                " Consider that the even number of minuses gives a plus, and the odd number of minuses gives a minus!")
        "/exit" -> return
        else -> println("Unknown command")
    }
}

fun isTheInputStringInvalid(input: String): Boolean {
    return input.isEmpty()
}

fun calculate(sequence: List<String>) {
    if (sequence.size == 1) {
        println(sequence[0].toInt())
        return
    }

    var result = sequence[0].toInt()
    for (index in 1..sequence.lastIndex step 2) {
            result = doArithmeticOperation(result, sequence[index], sequence[index + 1])
    }
    println(result)
}

fun doArithmeticOperation(currentValue: Int, operation: String, value: String): Int {
    return when {
        operation[0] == '-' && operation.length % 2 == 1 -> currentValue - value.toInt()
        operation[0] == '-' || operation[0] == '+' -> currentValue + value.toInt()
        else -> throw ArithmeticException("Invalid operation")
    }
    return currentValue
}