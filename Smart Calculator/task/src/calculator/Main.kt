package calculator

import java.lang.Exception
import java.util.*

val variables = mutableMapOf<String, Int>()
var input = ""
fun main() {
    val scanner = Scanner(System.`in`)
    do {
        input = scanner.nextLine()
        when (recognizePattern()) {
            "command" -> executeAdditionalCommand()
            "assignVariable" -> assignVariable()
            "printVariable" -> printVariable()
            "expression" -> calculate()
            else -> continue
        }
    } while (input != "/exit")
    println("Bye!")
}

fun recognizePattern(): String {
    return when {
        input.isEmpty() -> "emptyString"
        input.startsWith('/') -> "command"
        input.contains('=') -> "assignVariable"
        input.split(" ").filter { it.isNotEmpty() }.size == 1 &&
                (isValidValue(input) || isValidIdentifier(input)) -> "printVariable"
        else -> "expression"
    }
}

fun executeAdditionalCommand() {
    when (input) {
        "/help" -> println("The program calculates the result of some arithmetic operations. It supports " +
                "addition and subtraction operators." +
                " Consider that the even number of minuses gives a plus, and the odd number of minuses gives a minus!")
        "/exit" -> return
        else -> println("Unknown command")
    }
}

fun assignVariable() {
    try {
        val valuesInInput = input.split("=").filter { it.isNotEmpty() }.map { it.trim() }
        if (!isValidIdentifier(valuesInInput[0])) {
            println("Invalid identifier")
            return
        }

        if (valuesInInput.size != 2 || (!isValidValue(valuesInInput[1])) && !isValidIdentifier(valuesInInput[1])) {
            println("Invalid assignment")
            return
        }

        if (isValidIdentifier(valuesInInput[1]) && !variables.containsKey(valuesInInput[1])) {
            println("Unknown variable")
            return
        }

        if (variables.containsKey(valuesInInput[1])) {
            variables[valuesInInput[0]] = variables[valuesInInput[1]]!!
        } else {
            variables[valuesInInput[0]] = valuesInInput[1].toInt()
        }
    } catch (exp: Exception) {
        println("Invalid assignment")
    }
}

fun printVariable() {
    when {
        variables.containsKey(input) -> {
            println(variables[input])
        }
        isValidValue(input) -> {
            println(input)
        }
        else -> {
            println("Unknown variable")
        }
    }
}

fun getVariable(value: String): Int {
    return when {
        variables.containsKey(value) -> variables[value]!!
        isValidValue(value) -> value.toInt()
        else -> throw Exception("Invalid expression value")
    }
}

fun isValidIdentifier(identifier: String): Boolean {
    return identifier.isNotEmpty() && identifier.all { it.toLowerCase() in 'a'..'z' }
}

fun isValidValue(value: String): Boolean {
    return value.isNotEmpty() &&
            (value.startsWith('-') && value.subSequence(1, value.lastIndex).all { it.isDigit() }
                    || value.all { it.isDigit() })
}

fun calculate() {
    try {
        val sequence = input.split(" ").filter { it.isNotEmpty() }

        var result = getVariable(sequence[0])
        for (index in 1..sequence.lastIndex step 2) {
            result = doArithmeticOperation(result, sequence[index], getVariable(sequence[index + 1]))
        }
        println(result)
    } catch (exp: Exception) {
        println("Invalid expression")
    }
}

fun doArithmeticOperation(currentValue: Int, operation: String, value: Int): Int {
    return when {
        operation[0] == '-' && operation.length % 2 == 1 -> currentValue - value
        operation[0] == '-' || operation[0] == '+' -> currentValue + value
        else -> throw ArithmeticException("Invalid operation")
    }
}