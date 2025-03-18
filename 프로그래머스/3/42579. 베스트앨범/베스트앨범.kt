class Solution {
    fun solution(genres: Array<String>, plays: IntArray): IntArray {
        var answer = intArrayOf()
        val songs = hashMapOf<String, Int>()
        val songLog = hashMapOf<String, List<Song>>()
        genres.forEachIndexed { index, s ->
            songs[s] = (songs[s] ?: 0) + plays[index]
            val logs = songLog[s].orEmpty().toMutableList()
            logs.add(Song(index, plays[index]))
            songLog[s] = logs
        }
        val compare = compareByDescending<Song> { it.count }.thenBy { it.index }
        songs.keys.forEach {
            val temp = songLog[it]?.sortedWith(compare)?.toList()
            songLog[it] = temp.orEmpty()
        }
        val songsList = songs.entries.sortedByDescending { it.value }.map { it.key }
        songsList.forEach {
            val length = if ((songLog[it]?.size ?: 0) > 2) 2 else songLog[it]?.size ?: 0
            for (index in 0 until length) {
                answer += (songLog[it]?.get(index)?.index ?: continue)
            }
        }

        return answer
    }

    data class Song(val index: Int, val count: Int)
}