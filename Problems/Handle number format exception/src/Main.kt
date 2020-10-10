fun parseCardNumber(cardNumber: String): Long {
    val numberParts = cardNumber.split(" ")
    if (numberParts.size != 4 && !numberParts.all { it.length == 4 }) {
        throw Exception("Invalid card number")
    }
    return numberParts.joinToString("").toLong()
}