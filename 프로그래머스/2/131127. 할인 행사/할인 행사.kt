class Solution {
    fun solution(want: Array<String>, number: IntArray, discount: Array<String>): Int {
        var answer: Int = 0
        val wantMap = hashMapOf<String, Int>()
        want.forEachIndexed { index, s ->
            wantMap[s] = number[index]
        }
        for (day in 0 until 10) {
            if (discount[day] in wantMap.keys) {
                wantMap[discount[day]] = (wantMap[discount[day]] ?: 0) - 1
            }
        }
        if (isImpossibleAllBuying(wantMap).not()) answer += 1

        for (day in 10 until discount.size) {
            if (discount[day-10] in wantMap.keys) {
                wantMap[discount[day-10]] = (wantMap[discount[day-10]] ?: 0) + 1
            }
            if (discount[day] in wantMap.keys) {
                wantMap[discount[day]] = (wantMap[discount[day]] ?: 0) - 1
            }
            if (isImpossibleAllBuying(wantMap).not()) {
                answer += 1
            }
        }
        return answer
    }

    private fun isImpossibleAllBuying(hashMap: HashMap<String, Int>): Boolean {
        return hashMap.values.any { it > 0 }
    }
}