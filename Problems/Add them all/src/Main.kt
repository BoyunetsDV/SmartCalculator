fun solution(first: List<Int>, second: List<Int>): MutableList<Int> {
    val sequence = first.toMutableList()
    sequence.addAll(second)
    return sequence
}