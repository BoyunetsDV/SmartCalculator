import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val n1 = scanner.nextBigInteger()
    val n2 = scanner.nextBigInteger()
    println((n1 * n2).abs() / n1.gcd(n2))
}