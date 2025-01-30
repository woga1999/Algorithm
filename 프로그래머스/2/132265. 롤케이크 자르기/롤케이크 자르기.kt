class Solution {
    fun solution(topping: IntArray): Int {
        var answer: Int = 0
        var toppingA = HashMap<Int, Int>()
        var toppingB = mutableSetOf<Int>()
        topping.forEach {
            toppingA[it] = toppingA[it].orZero() + 1
        }
        var totalTopplingACount = toppingA.size
        
        topping.forEach { 
            toppingB.add(it)
            toppingA[it] = toppingA[it].orZero() - 1
            
            if (toppingA[it].orZero() <= 0) {
                totalTopplingACount--
            }
            if (totalTopplingACount == toppingB.size) {
                answer++
            }
        }
        
        return answer
    }
    
    fun Int?.orZero() = this ?: 0
}