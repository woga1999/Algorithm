class Solution {
    fun solution(queue1: IntArray, queue2: IntArray): Int {
        var answer: Int = 0
        val dq1 = ArrayDeque<Int>()
        val dq2 = ArrayDeque<Int>()
        queue1.forEach {
            dq1.addLast(it)
        }
        queue2.forEach {
            dq2.addLast(it)
        }
        var result1 = dq1.sumWithLong()
        var result2 = dq2.sumWithLong()
        val total = queue1.size * 4

        while (result1 != result2) {
            if (total < answer) return -1
            if (result1 < result2) {
                val temp = dq2.removeFirst()
                dq1.addLast(temp)
                result1 += temp
                result2 -= temp
            } else {
                val temp = dq1.removeFirst()
                dq2.addLast(temp)
                result1 -= temp
                result2 += temp
            }
            answer++
        }

        return answer
    }

    private fun ArrayDeque<Int>.sumWithLong() : Long {
        var sum: Long = 0
        this.forEach {
            sum += it
        }
        return sum
    }
}