import java.math.BigInteger
import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val number = scanner.nextInt()
    val exabyte = BigInteger("9223372036854775808")
    println(exabyte * number.toBigInteger())

}