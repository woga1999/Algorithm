class Solution {
    fun solution(elements: IntArray): Int {
         val numbers = mutableSetOf<Int>()
        var size = 1
        while(size <= elements.size) {
            var startIndex = 0
            while (startIndex < elements.size) {
                var sum = 0
                for (index in startIndex until startIndex + size) {
                    val nowIndex = index % elements.size
                    sum += elements[nowIndex]
                }
                numbers.add(sum)
                startIndex++
            }
            size++
        }
        return numbers.size
    }
}