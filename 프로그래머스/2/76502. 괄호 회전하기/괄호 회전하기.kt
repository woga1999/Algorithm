import java.util.*

class Solution {
    fun solution(s: String): Int {
        var answer: Int = 0
        var targetSliceIndex = 0
        while (targetSliceIndex < s.length) {
            val first = s.slice(0..targetSliceIndex)
            val end = s.slice(targetSliceIndex+1..s.length - 1)
            if (isRightSentence(end+first)) {
                answer++
            }
            targetSliceIndex++
        }
        return answer
    }

    fun isRightSentence(str: String): Boolean {
        val charStack = Stack<Char>()
        str.forEach {
            if (charStack.isEmpty()) {
                charStack.push(it)
            } else {
                val topValue = charStack.peek()
                if ((it == ')' && topValue == '(') || (it == '}' && topValue == '{') || (it == ']' && topValue == '[')){
                    charStack.pop()
                } else {
                    charStack.push(it)
                }
            }
        }
        return charStack.isEmpty()
    }
}