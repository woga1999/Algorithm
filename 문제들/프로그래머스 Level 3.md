# 프로그래머스 - Level 3

## 종이접기

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/62049?language=cpp)

첨에 규칙을 찾다보니

n-1대로 00 11이 나오고(가운데 피해서,

n-2 대로 다시 바꿔서 1 0 이 나오고 가운데 0은 공식으로 생각해서 코드를 짰는데 테스트케이스가 다 틀렸다

그래서 고민하다가 다른 정답 풀이를 봤고

전 종이 접기 한 것의 0 1이 추가된 것이다

n=2

0 0 1

n=3

0 0 1 0 0 1 1

이런 식... 정답 풀이 안봤으면 모를 뻔 규칙 찾는 것도 모르다니ㅠ

    #include <string>
    #include <vector>
    
    using namespace std;
    
    vector<int> solution(int n) {
    	vector<int> ans;
    	for (int i = 1; i <= n; i++) {
    		vector<int> tmp;
    		int index = 0;
    		int size = ans.size();
            bool flag = true;
    		while (index <= size) {
    			if(flag){
                    tmp.push_back(0);
                    flag = false;
                }
                else{
                    tmp.push_back(1);
                    flag = true;
                }
                if(size>0 && index < size) tmp.push_back(ans[index]);
    			index++;
    		}
    		ans = tmp;
    	}
    	return ans;
    }