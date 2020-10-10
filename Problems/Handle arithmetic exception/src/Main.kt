import java.util.*

fun main(args: Array<String>) {
    val scanner = Scanner(System.`in`)
    val num1 = scanner.nextInt()
    val num2 = scanner.nextInt()
    if (num2 == 0) {
        println("Division by zero, please fix the second argument!")
    } else {
        println(num1 / num2)
    }
}