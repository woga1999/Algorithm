class Solution {
    var count = 0
    var hashMap = hashMapOf<String, Int>()
    fun solution(word: String): Int {
        var vowels = charArrayOf('A', 'E', 'I', 'O', 'U')
        vowels.forEach {
            hashMap[it.toString()] = ++count
            checkCount(word, it.toString(), vowels)
        }
        return hashMap[word] ?: 0
    }

    fun checkCount(targetWord: String, makeWord: String, vowels: CharArray) {
        if (targetWord == makeWord) {
            hashMap[targetWord] = count
            return
        }
        if (makeWord.length == 5 || (hashMap[targetWord] ?: 0) > 0) return
        vowels.forEach {
            if ((hashMap[targetWord] ?: 0) > 0) return
            val result = makeWord + it
            if ((hashMap[result] ?: 0) == 0) {
                count++
                hashMap[result] = count
                checkCount(targetWord, result, vowels)
            }
        }
    }
}