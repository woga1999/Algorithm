import kotlin.math.min

class Solution {
    fun solution(x: Int, y: Int, n: Int): Int {
        val resultHashMap = hashMapOf<Int, Int>()

        resultHashMap[x] = 0
        for (number in x+1..y) {
            resultHashMap[number] = Int.MAX_VALUE
        }
        for (number in x..y) {
            if (resultHashMap[number] == Int.MAX_VALUE) continue

            if (number + n <= y) {
                resultHashMap[number + n] = min(resultHashMap[number + n]!!, (resultHashMap[number] ?: 0) + 1)
            }
            if (number * 2 <= y) {
                resultHashMap[number * 2] = min(resultHashMap[number * 2]!!, (resultHashMap[number] ?: 0) + 1)
            }
            if (number * 3 <= y) {
                resultHashMap[number * 3] = min(resultHashMap[number * 3]!!, (resultHashMap[number] ?: 0) + 1)
            }
        }

        return if (resultHashMap[y] == Int.MAX_VALUE) -1 else resultHashMap[y] ?: -1
    }
}