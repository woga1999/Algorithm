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

```c++
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
```


- 추석 트래픽 (안품)

## N으로 표현

아래와 같이 5와 사칙연산만으로 12를 표현할 수 있습니다.

12 = 5 + 5 + (5 / 5) + (5 / 5)

12 = 55 / 5 + 5 / 5

12 = (55 + 5) / 5

5를 사용한 횟수는 각각 6,5,4 입니다. 그리고 이중 가장 작은 경우는 4입니다.이처럼 숫자 N과 number가 주어질 때, N과 사칙연산만 사용해서 표현 할 수 있는 방법 중 N 사용횟수의 최솟값을 return 하도록 solution 함수를 작성하세요.

### 제한사항

- N은 1 이상 9 이하입니다.
- number는 1 이상 32,000 이하입니다.
- 수식에는 괄호와 사칙연산만 가능하며 나누기 연산에서 나머지는 무시합니다.
- 최솟값이 8보다 크면 -1을 return 합니다.

<C++을 무기로, 코딩테스트 통과하자> 라는 강의에서 나온 문제 유형

강의 보고 풀었다.

```c++
#include <string>
#include <vector>
#include <unordered_set>

using namespace std;

int solution(int N, int number) {
    int answer = -1;
    unordered_set<int> s[9];
    int base =0;
    for(int i=1; i<=8; i++){
        base = 10 * base +1;
        s[i].insert(base*N);
    }
    for(int i=2; i<=8; i++){
        for(int j=1; j<i; j++){
            for(auto& op1 : s[j]){
                for(auto& op2 : s[i-j]){
                    s[i].insert(op1 + op2);
                    s[i].insert(op1 - op2);
                    s[i].insert(op1 * op2);
                    if(op2 !=0) s[i].insert(op1/op2);
                }
            }
        }
        if(s[i].count(number) > 0) {
            answer = i;
            break;
        }
    }
    return answer;
}
```