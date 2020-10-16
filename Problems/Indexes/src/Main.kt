fun solution(products: List<String>, product: String) {
    var result = ""
    for (index in products.indices) {
        if (products[index] == product) {
            result += " $index"
        }
    }
    println(result.trim())
}