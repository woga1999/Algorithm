class Solution {
    fun solution(numbers: LongArray): LongArray {
        var answer: LongArray = longArrayOf()
        numbers.forEach {
            answer += f(it)
        }
        return answer
    }

    private fun f(x: Long): Long {
        if (x % 2 == 0L) {
            return x + 1
        } else {
            val strTwoBit = "0" +  x.toString(2)
            val zeroLastIndex = strTwoBit.lastIndexOf("0")
            val arrStr = strTwoBit.toCharArray()
            arrStr[zeroLastIndex] = '1'
            arrStr[zeroLastIndex + 1] = '0'
            return arrStr.joinToString("").toLong(2)
        }
    }
}