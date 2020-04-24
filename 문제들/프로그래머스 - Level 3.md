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



## 섬 연결하기

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/42861)

- 흠.. 첫번째 시도 : dfs 틀렸다



\#include <string> #include <vector> #include <algorithm>

```c++
using namespace std;

struct a{
    int x,y,cost;
    a(int b, int c, int d){
        x = b;
        y = c;
        cost= d;
    }
    bool operator<(a &d){
        return cost < d.cost;
    }
};

bool checkIsland(vector<bool> ch, int N){
    for(int i=0; i<N; i++){
        if(!ch[i]) return false;
    }
    return true;
}

int solution(int n, vector<vector<int>> costs) {
    int answer = 0;
    vector<bool> ch(n);
    vector<a> island;
    for(int i=0; i<costs.size(); i++){
        int x = costs[i][0];
        int y = costs[i][1];
        int c = costs[i][2];
        island.push_back(a(x,y,c));
    }
    sort(island.begin(), island.end());
    ch[island[0].x] = true;
    for(int i=0; i<island.size(); i++){
        if(!ch[island[i].y]){
            ch[island[i].y] = true;
            answer += island[i].cost;
        }
       if(checkIsland(ch,n)) break;
    }
    return answer;
}
```

이렇게 풀었는데 1개 맞음ㅋㅋㅋㅋ ㅜㅜ 뭐임

**크루스칼로 풀었다 Union&Find 사용..**

다시 공부해야할듯 크루스칼.. 아 이거 dfs로도 충분히 가능할것같았는데 아쉽다

```c++
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct Data{
    int x,y,cost;
    Data(int a,int b, int c){
        x = a;
        y = b;
        cost = c;
    }
    bool operator<(Data &d){
        return cost < d.cost;
    }
};
int arr[101];
int res;

int Find(int v){
    if(v==arr[v]) return v;
    else return arr[v] = Find(arr[v]);
}

void Union(int a,int b, int c){
    a = Find(a);
    b = Find(b);
    if(a != b){
        arr[a] = b;
        res += c;
    }
}

int solution(int n, vector<vector<int>> costs) {
    int answer = 0;
    for(int i=0; i<n; i++){
        arr[i] = i;
    }
    vector<Data> island;
    for(int i=0; i<costs.size(); i++){
        int x = costs[i][0];
        int y = costs[i][1];
        int c = costs[i][2];
        island.push_back(Data(x,y,c));
    }
    sort(island.begin(), island.end());
    for(int i=0; i<island.size(); i++){
        Union(island[i].x,island[i].y,island[i].cost);
    }
    answer = res;
    return answer;
}
```

→ 다시 공부함





## 디스크 컨트롤러

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/42627)

힙 이용해서 풀었는데..흠...



`10점`

```c++
#include <string>
#include <vector>
#include <queue>

using namespace std;

struct process{
    int start, total;
    process(int x,int t){
        start = x;
        total = t;
    }
    bool operator<(const process &d)const{
        if(total == d.total) return start > d.start;
        return total > d.total;
    }
};

int solution(vector<vector<int>> jobs) {
    int answer = 0;
    priority_queue<process> pq;
    int start = 0;
    int total = 0;
    for(int i=0; i<jobs.size(); i++){
        if(jobs[i][0] == 0){
            start = jobs[i][0];
            total = jobs[i][1];
        }
        else pq.push(process(jobs[i][0],jobs[i][1]));
    }
    start = total;
    while(!pq.empty()){
        int barS = pq.top().start;
        int barVal = pq.top().total;
        pq.pop();
        total += (start - barS) + barVal;
        start += barVal;
    }
    answer = total / jobs.size();
    return answer;
}
```

10점 맞음ㅋㅋ



`25점`

```c++

#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct process{
    int start, total;
    process(int x,int t){
        start = x;
        total = t;
    }
    bool operator<(const process &d)const{
        if(d.total == total) return start > d.start;
        else return total > d.total;
    }
};
bool comp(vector<int> a, vector<int> b){
    return a[0] < b[0];
}

int solution(vector<vector<int>> jobs) {
    int answer = 0;
    priority_queue<process> pq;
    int spend = 0;
    int total = 0;
    sort(jobs.begin(), jobs.end(), comp);
    for(int i=0; i<jobs.size(); i++){
        if(i == 0){
            spend = jobs[i][0];
            total = jobs[i][1]+spend;
        }
        else pq.push(process(jobs[i][0],jobs[i][1]));
    }
    answer = total;
    while(!pq.empty()){
        int barS = pq.top().start;
        int barVal = pq.top().total;
        pq.pop();
        spend = total - barS;
        total += barVal;
        answer += (spend+barVal);
    }
    answer /= jobs.size();
    return answer;
}
```





## 예산

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/43237)

- 테스트 9번만 틀림

  효율성/ 정확성 다 맞았는데

  정확성에서 테스트 9번만 틀렸음..

  ```cpp
  #include <string>
  #include <vector>
  
  using namespace std;
  
  int solution(vector<int> budgets, int M) {
      int answer = 0;
      int left = 987654321;
      int right = -1;
      for(int i=0; i<budgets.size(); i++){
          if(left > budgets[i]){
              left = budgets[i];
          }
          if(right < budgets[i]){
              right = budgets[i];
          }
      }
      vector<int> tmp = budgets;
      int valMax = -1;
      int mid =0;
      while(left <=right){
          mid = (left + right) / 2;
          int total=0;
          for(int i=0; i<budgets.size(); i++){
              if(mid<budgets[i]){
                  budgets[i] = mid;
              }
              total += budgets[i];
          }
          if(total > M){
              right = mid-1;
          }else if(total<M){
              if(total > valMax){
                  answer = mid;
                  valMax = total;
              }
              left = mid+1;
          }
          else if(total == M){
              answer = mid;
              break;
          }
          budgets = tmp;
      }
      return answer;
  }
  ```

원인을 모르겠따!

budgets = [9, 8, 5, 6, 7] M = 5 일 때, 그러니까 최저 예산이 (총 예산 / 도시의 수)보다 클 때의 경우의 수 처리가 빠진것 같습니다. 라는 답변을 보았다

뭔소리지



## 가장 먼 노드

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/49189)

- 정답!

  ```cpp
  #include <string>
  #include <vector>
  #include <queue>
  
  using namespace std;
  
  int dis[20001];
  bool ch[20001];
  
  int solution(int n, vector<vector<int>> edge) {
      int answer = 0;
      vector<int> node[200001];
      for(int i=0; i<edge.size(); i++){
          int x = edge[i][0];
          int y = edge[i][1];
          node[x].push_back(y);
          node[y].push_back(x);
      }
      queue<int> q;
      q.push(1);
      ch[1] = true;
      while(!q.empty()){
          int index = q.front();
          q.pop();
          for(int i=0; i<node[index].size(); i++){
              if(ch[node[index][i]]) continue;
              ch[node[index][i]] = true;
              q.push(node[index][i]);
              dis[node[index][i]] = dis[index] + 1;
          }
      }
      int max = -1;
      for(int i=2; i<= n; i++){
          if(max < dis[i]) max = dis[i];
      }
      for(int i=2; i<= n; i++){
          if(max==dis[i]) answer++;
      }
      return answer;
  }
  ```





## 단속카메라

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/42884)

- 빵점.. 예제 테케는 맞음

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  
  using namespace std;
  
  bool ch[10001];
  
  bool comp(vector<int> a, vector<int> b){
      return a[0] < b[0];
  }
  
  bool isAllTrue(int n){
      for(int i=0; i<n; i++){
          if(!ch[i]) return false;
      }
      return true;
  }
  
  int solution(vector<vector<int>> routes) {
      int answer = 1;
      sort(routes.begin(), routes.end(), comp);
      for(int i=0; i<routes.size(); i++){
          if(ch[i]) continue;
          bool flag = false;
          int index = i+1;
          int start = routes[i][0];
          int end = routes[i][1];
          while(index <routes.size()){
              if(start <= routes[index][0] && end >= routes[index][1]){
                  ch[index] = true;
                  index++;
              }
              else break;
          }
          if(!isAllTrue(routes.size())) answer++;
      }
      return answer;
  }
  ```



[[프로그래머스\] 단속카메라](https://ga0n.tistory.com/entry/프로그래머스-단속카메라)

이 분걸 참고했고 접근법은 맞다! 근데 과정에서 카메라 비교에 대한 생각이 틀렸음

근데 잘 이해안됨.. 대충 내가 생각한대로 겹치는 바들을 한 감시카메라로 놓고 중복 범위 넘어가는 바들은 ++해서 새로운 감시카메라를 놓고 갈 준비를 함

- 정답 소스

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  
  using namespace std;
  
  int solution(vector<vector<int>> routes) {
      int answer = 1;
      sort(routes.begin(), routes.end());
      int camera = routes[0][1];
      for(int i=0; i<routes.size()-1; i++){
          if(camera > routes[i][1]) camera = routes[i][1]; //카메라 위치가 너무 앞서가서 위치조정
          if(camera < routes[i+1][0]){
              answer++; //단속카메라 새로 놓아야하고
              camera = routes[i+1][1]; //다음 이동경로 진출지점으로 카메라 위치 조정
          }
      }
      return answer;
  }
  ```

