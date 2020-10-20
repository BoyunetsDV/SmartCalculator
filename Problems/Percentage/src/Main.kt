import java.math.BigInteger
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val n1 = scanner.nextBigInteger()
    val n2 = scanner.nextBigInteger()
    val sum = n1 + n2
    println("${n1 * 100.toBigInteger() / sum}% ${n2 * 100.toBigInteger() / sum}%")
}