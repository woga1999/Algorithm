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



## 브라이언의 고민(못품)

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/1830)

```c++
#include <string>
#include <unordered_set>

using namespace std;

// 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
string solution(string sen) {
    string answer = "";
    unordered_set<char> used;
    char cur =' ';
    bool second = false;
    int cnt=0;
    for(int i=0; i<sen.size(); i++){
        if(sen[i]>='A' && sen[i] <='Z'){
            cnt++;
            if(cnt>1 && !second) answer += " ";
            answer += sen[i];
        }
        else{
            cnt--;
            if(cur != sen[i]) {
                cur = sen[i];
                if(i>3) cnt -= 2;
                if(used.count(cur)>0) {
                    return "invalid";
                }else used.insert(cur);
            }
            if(cnt < 0){
                second = true;
                answer += " ";
            }else if(cnt> 0 && second){
                second = false;
            }
        }
    }
    if (cnt < 0) return "invalid";
    return answer;
}
```

이런 식으로 풀었다.

### **예제에 대한 설명**

첫 번째 테스트 케이스는 문제 설명에 제시된 데이터와 같다.두 번째 테스트 케이스에서, 기호 `q`는 규칙 1, 혹은 규칙 2에 의해 추가된 기호일 수 있다. 규칙 1에 해당하는 경우 원문은 `SIGONG JOA`로 예제 출력과 같으며, 규칙 2에 해당하는 경우의 원문인 `SIGONG J O A`도 올바른 답이다.세 번째 테스트 케이스에서 `x`는 규칙 1에 의해 추가된 기호여야 한다. (규칙 2에 의해 추가되었다면 기호가 단어 앞뒤에 붙게 되므로 2개여야 한다.) 그러므로 `AAAA`가 한 단어여야 한다. 마찬가지로 `o`도 규칙 1에 의해 추가된 기호여야 하기 때문에 `ABBB`가 한 단어여야 한다. 이는 동시에 만족할 수 없는 조건이므로 주어진 문구는 규칙을 만족할 수 없게 된다. 따라서 `invalid`를 리턴한다.

→ 이러길래 나는 후자인 "SIGONG J O A"로 해서 한건데 이런 틀렸다고 나온다.

```
알림: '실행'을 눌렀을 시 올바른 코드가 틀린 결과로 표시되는 경우가 있습니다. 하단의 설명을 참고해주세요.
```

가 ㅇ상단에 적혀있긴 한다.

하지만 코드 채점하고 제출하니깐 틀렸다..

[프로그래머스 : 브라이언의 고민](https://hackerjacob.tistory.com/92)

이분은 이렇게 풀었는데 넘 김..

## 타일 장식물

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/43104)

DP 이용하는 대표적인 문제인듯

```c++
#include <string>
#include <vector>

using namespace std;

long long dp[81];
long long solution(int N) {
    long long answer = 0;
    dp[1] = 1;
    dp[2] = 1;
    for(int i=3; i<=N; i++){
        dp[i] = dp[i-1] + dp[i-2];
    }
    long long h = dp[N];
    long long w = dp[N-1] + dp[N];
    answer = (h+w) * 2;
    return answer;
}
```

효율성/ 정확성 다 만점~ㅎㅎ

## 자물쇠와 열쇠

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/60059)

다시 풀어볼것!

[프로그래머스 [2020카카오공채\] 자물쇠와 열쇠 c++](https://regularmember.tistory.com/186)

- core jumped 코드 location 함수에서 잘못함ㅋㅋ

locationKey 함수를 고쳐줌

73/100

38개 중에 12개 틀림..

```c++
#include <string>
#include <vector>

using namespace std;

vector<vector<int>> key;

void locationKey(vector<vector<int>> k, int size){
    for(int i=0; i<k.size(); i++){
        for(int j=0; j<k[i].size(); j++){
            key[i][j] = k[size-j-1][i];
        }
    }
}
bool solution(vector<vector<int>> ke, vector<vector<int>> lock) {
    bool answer = true;
    key = ke;
    int keysize = key.size();
    int locksize = lock.size();
    int N = locksize + (keysize-1)*2;
    vector<vector<int>> board(N, vector<int> (N,0));
    for(int i1=keysize-1,i2=0; i1<keysize-1+locksize,i2<locksize; i1++,i2++){
        for(int j1=keysize-1,j2=0; j1<keysize-1+locksize,j2<lock[i2].size(); j1++,j2++){
            board[i1][j1] = lock[i2][j2];
        }
    }
    
    for(int k=0; k<4; k++){
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
            for(int l=0+i; l<i+keysize; l++){
                for(int m=0+i; m<i+keysize; m++){
                    if(key[l][m]+board[i][j] == 1 && key[l][m] ==1){
                        return true;
                    }
                }
            }
            }
        }
        locationKey(ke,keysize);
        ke = key;
    }
    return false;
}
```

모르겠따 false가 잘 안나오는 건가