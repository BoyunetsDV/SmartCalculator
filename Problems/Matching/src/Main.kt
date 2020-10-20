import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val n1 = scanner.nextBigInteger()
    val n2 = scanner.nextBigInteger()
    val n3 = scanner.nextBigInteger()
    println(when {
        n1 == n2 && n1 == n3 -> 3
        n1 == n2 || n1 == n3 || n2 == n3 -> 2
        else -> 0
    })
}