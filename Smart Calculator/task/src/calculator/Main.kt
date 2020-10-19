package calculator

import java.lang.Exception
import java.util.*
import kotlin.math.pow

val variables = mutableMapOf<String, Int>()
val priorities = mutableMapOf("+" to 1, "-" to 1, "*" to 2, "/" to 2, "^" to 3)
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

fun getVariable(value: String): String {
    return when {
        variables.containsKey(value) -> variables[value]!!.toString()
        isValidValue(value) -> value
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
        println(calculatePostfixQueue(getPostfixFormInputQueue()))
    } catch (exp: Exception) {
        println(exp.message)
    }
}

fun getPostfixFormInputQueue(): List<String> {
    try {
        val seq = input.trim()
                .split("(?= )|(?<= )|(?=[()\\^])|(?<=[()\\^])".toRegex())
                .toMutableList()
        seq.removeAll { el -> el.isEmpty() || el.isBlank() }

        for (i in seq.indices) {
            if (seq[i].length > 1 && !seq[i][1].isLetterOrDigit() && seq[i][0] in "+-*/^") {
                seq[i] = getReplaceValue(seq[i])
            }
        }

        val result = mutableListOf<String>()
        val stack = Stack<String>()

        for (el in seq) {
            if (el[0].isLetterOrDigit() || el.length > 1 && el[1].isLetterOrDigit()) {
                result.add(el)
            } else if (el == ")") {
                while (stack.peek() != "(") {
                    result.add(stack.pop())
                }
                if (stack.isNotEmpty()) {
                    stack.pop()
                }
            } else if (stack.isEmpty() ||
                    stack.peek() == "(" ||
                    el == "(" ||
                    priorities[stack.peek()]!! < priorities[el]!!) {
                stack.push(el)
            } else if (priorities[stack.peek()]!! >= priorities[el]!!) {
                while (stack.isNotEmpty() && (stack.peek() != "(" && priorities[stack.peek()]!! >= priorities[el]!!)) {
                    result.add(stack.pop())
                }
                stack.push(el)
            }
        }

        while (stack.isNotEmpty()) {
            result.add(stack.pop())
        }
        if (result.contains("(") || result.contains(")")) {
            throw InputMismatchException("Invalid expression")
        }
        return result
    } catch (exp: Exception) {
        throw InputMismatchException("Invalid expression")
    }
}

fun getReplaceValue(value: String): String {
    var result = value[0]
    for (i in 1 until value.length) {
        result = if (value[i] == '+' && result == '+' || value[i] == '-' && result == '-') {
            '+'
        } else if (value[i] == '-' && result == '+' || value[i] == '+' && result == '-') {
            '-'
        } else {
            throw InputMismatchException("Invalid expression")
        }
    }
    return result.toString()
}

fun calculatePostfixQueue(queue: List<String>): Int {
    try {
        val stack = Stack<String>()
        for (el in queue) {
            if (el[0].isLetterOrDigit() || el.length > 1 && el[1].isLetterOrDigit()) {
                stack.push(getVariable(el))
            } else if (el[0] in "+-/*^") {
                val a2 = stack.pop().toInt()
                val a1 = stack.pop().toInt()
                stack.push(doArithmeticOperation(a1, a2, el[0]).toString())
            }
        }
        if (stack.size != 1) {
            throw InputMismatchException("Invalid expression")
        }
        return stack.pop().toInt()
    } catch (exp: Exception) {
        throw InputMismatchException("Invalid expression")
    }
}

fun doArithmeticOperation(v1: Int, v2: Int, operator: Char): Int {
    return when (operator) {
        '-' -> v1 - v2
        '+' -> v1 + v2
        '*' -> v1 * v2
        '/' -> v1 / v2
        '^' -> v1.toDouble().pow(v2.toDouble()).toInt()
        else -> throw ArithmeticException("Invalid operation")
    }
}