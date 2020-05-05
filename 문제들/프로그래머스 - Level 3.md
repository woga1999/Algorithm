다른 사람 풀이 보니깐 멀티셋 사용한 사람도 있음 중복허용, set의 알아서 정렬 특성을 사용.

```cpp
if(sub=="I ") que.insert(stoi(s.substr(2,s.length()-2))); 
else if(s.substr(2,1)=="1"&&que.size()>0) { que.erase(--que.end()); }
else if(que.size()>0) { que.erase(que.begin()); }
```

이런식으로 사용함 호오..프로그래머스 - Level 3

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



## 정수 삼각형

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/43105)

- 정답

  이게 대분류가 DP라서 어떻게 DP로 풀까 많이 고민했다 0에서 먼저 시작하려했는데 그럼 답이 안나와서 삼각형 끝단에서 시작

  정확성 효율성 다 맞았다!

  ```cpp
  #include <string>
  #include <vector>
  
  using namespace std;
  
  int dp[500][500];
  
  int solution(vector<vector<int>> triangle) {
      int answer = 0;
      int N = triangle.size();
      for(int i=0; i<triangle[N-1].size(); i++){
          dp[N-1][i] = triangle[N-1][i];
      }
      for(int i=triangle.size()-1; i>0; i--){
          for(int j=0; j<triangle[i].size(); j++){
              if(j != 0 && j != triangle[i].size()-1){
                  if(dp[i-1][j-1] < dp[i][j] + triangle[i-1][j-1]){
                      dp[i-1][j-1] = dp[i][j] + triangle[i-1][j-1];
                  }
                  if(dp[i-1][j] < dp[i][j] + triangle[i-1][j]){
                      dp[i-1][j] = dp[i][j] + triangle[i-1][j];
                  }
              }else if(j==0){
                  if(dp[i-1][j] < dp[i][j] + triangle[i-1][j]){
                      dp[i-1][j] =  dp[i][j] + triangle[i-1][j];
                  }
              }else{
                  if(dp[i-1][j-1] < dp[i][j] + triangle[i-1][j-1]){
                      dp[i-1][j-1] = dp[i][j] + triangle[i-1][j-1];
                  }
              }
          }
      }
      answer = dp[0][0];
      return answer;
  }
  ```

흠.. 다른 사람들 풀이 보는데 내가 너무 복잡하게 생각한듯, 내가 첨에 생각한 0에서 시작이 0,0으로 두고 시작해도 답 나옴 난 처음에 일차원배열로 하려고 해서 그런듯!

첨에 생각했듯이 대신 0,0으로 하면 나머지 끝단 dp 값들에서 max 값 비교해서 뽑아내야 함!





## 이중우선순위큐

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/42628)

- 정답

  첨에 아 우선순위 큐 놓고 다른 큐 만들어서 뺴고 먹고 해야하나?하다가 vector<int>로 그대로하자! 했다가 빼려면 또다시 for문을 돌려야한다는 걸 알고 덱을 사용함

  하다보니깐 코드가 좀 드러워졌는데.. 문자열에 뭐가 포함되었는지 확인할떄 **find(string) ≠ -1** 을 기억하자~

  만점!

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  #include <deque>
  
  using namespace std;
  
  vector<int> solution(vector<string> operations) {
      vector<int> answer;
      deque<int> a;
      for(int i=0; i<operations.size(); i++){
          if(operations[i] == "D 1" && !a.empty()){
              a.pop_back();
          }else if(operations[i] == "D -1" && !a.empty()){
              a.pop_front();
          }else if(operations[i].find("I") != -1){
              string tmp = operations[i].substr(2);
              int data = stoi(tmp);
              a.push_back(data);
              if(a.size()>0)sort(a.begin(), a.end());
          }
      }
      if (a.size() > 0) {
  		sort(a.begin(), a.end());
  		answer.push_back(a[a.size()-1]);
  		answer.push_back(a[0]);
  	}
  	else {
  		answer.push_back(0);
  		answer.push_back(0);
  	}
      return answer;
  }
  ```

다른 사람 풀이 보니깐 멀티셋 사용한 사람도 있음 중복허용, set의 알아서 정렬 특성을 사용.

```cpp
if(sub=="I ") que.insert(stoi(s.substr(2,s.length()-2))); 
else if(s.substr(2,1)=="1"&&que.size()>0) { que.erase(--que.end()); }
else if(que.size()>0) { que.erase(que.begin()); }
```

이런식으로 사용함 호오..



## 입국심사

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/43238)

-자의로 못풀었다

-결국 블로그 보고 참고!

[프로그래머스_입국심사](https://woongsin94.tistory.com/185)

카테고리가 대분류고 시간을 도출하는 문제길래 이분탐색인건 알겠는데 문제는 시간 타겟을 정하고 어떻게 판별해야하는지 생각이 안났다.

블로그에서 `추정 시간값/ 각 심사관별 심사시간 = 심사관당 맡을 수 있는 입국자 수` 를 보고 아차! 싶었다. target분 내에 해결하는 수들이 n이면 되는거다!

- 해서 풀었는데 44/100 틀림 왜틀린거지..

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  
  using namespace std;
  
  long long solution(int n, vector<int> times) {
      long long answer = 0;
      sort(times.begin(), times.end());
      long long left = times[0];
      long long right = times[times.size()-1] * n;
      while(left <=right){
          long long mid = (left + right) / 2;
          long long peopleTotal = 0;
          for(int i=0; i<times.size(); i++){
              peopleTotal += mid / times[i];
          }
          if(peopleTotal < n){
              left = mid + 1;
          }else{
              right = mid -1;
          }
          answer = mid;
      }
      return answer;
  }
  ```

- 한줄 추가로 88.9/100 테케 8번만 틀림

  ```cpp
  while(left <=right){
          long long mid = (left + right) / 2;
          long long peopleTotal = 0;
          for(int i=0; i<times.size(); i++){
              peopleTotal += mid / times[i];
          }
          if(peopleTotal < n){
              left = mid + 1;
          }else{
              if(answer > mid) answer = mid; 
              right = mid -1;
          }
      }
  ```

- 정답

  `long long right = (long long)times[times.size()-1] * n;`

  이걸 안해줘서 테케 8번이 계속 틀린거였다 *n해서 int 값을 넘어버리면 이상한 값이 나오니깐 캐스팅해줘야했던거임!!!!! 겨우 통과했다 결국 코드를 안보겠다는 다짐은 멀리 보내고 위 블로그 속 정답 코드와 비교했다

  아무리 봐도 다른게 캐스팅 문제라 저거만 추가했더니 됐음.. 이 무슨..

  최종 코드

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  
  using namespace std;
  
  long long solution(int n, vector<int> times) {
      long long answer = 0;
      sort(times.begin(), times.end());
      long long left = times[0];
      long long right = (long long)times[times.size()-1] * n;
      answer = right;
      while(left <=right){
          long long mid = (left + right) / 2;
          long long peopleTotal = 0;
          for(int i=0; i<times.size(); i++){
              peopleTotal += mid / times[i];
          }
          if(peopleTotal < n){
              left = mid + 1;
          }else{
              if(answer > mid) answer = mid; 
              right = mid -1;
          }
      }
      return answer;
  }
  ```



## 저울

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/42886)

- 응 헛소리 코드

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  
  using namespace std;
  
  int solution(vector<int> weight) {
      int answer = 0;
      sort(weight.begin(),weight.end());
      int total = 0;
      for(int i=0; i<weight.size(); i++){
          total += weight[i];
      }
      answer = (total - weight[weight.size()-1]) + weight[0];
      
      return answer;
  }
  ```

- 테케 한개 빼고 다 시간초과 뜸

  dfs 이용해서 1~weight 사이즈만큼 더할때 계산하고 set에 넣는다 그리고 하나씩 answer++하며 답을 찾아가는 과정을 거친다.

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  #include <set>
  
  using namespace std;
  
  vector<int> d[10000];
  int N;
  
  void dfs(int index, int cnt, int n, int sum, vector<int> t) {
  	if (cnt == n) {
  		d[n - 1].push_back(sum);
  		return;
  	}
  	if (index >= N) return;
  	dfs(index + 1, cnt + 1, n, sum + t[index], t);
  	dfs(index + 1, cnt, n, sum, t);
  }
  
  int solution(vector<int> weight) {
      int answer = 1;
      N = weight.size();
      sort(weight.begin(),weight.end());
      int total = 0;
      for(int i=0; i<weight.size(); i++){
          total += weight[i];
          d[0].push_back(weight[i]);
      }
      d[N-1].push_back(total);
      int index =1;
      while(index<N-1){
          dfs(0, 0, index + 1,0,weight);
          index++;
      }
      set<int> tmp;
      for(int i=0; i<N; i++){
          for(int j=0; j<d[i].size(); j++){
              tmp.insert(d[i][j]);
          }
      }
      
      for(auto x : tmp){
          if(x == answer) answer++;
          else break;
      }
      return answer;
  }
  ```

[프로그래머스 고득점 kit : 저울](https://sihyungyou.github.io/programmers-저울/)

를 참고했고 진짜 쉬운거였다... 며칠 흐르고 다시 풀기!

- 다시 풀었는데 25점? 똑같은데 소스

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  
  using namespace std;
  
  int solution(vector<int> weight) {
      int answer = 0;
      sort(weight.begin(), weight.end());
  		answer = weight[0];
      for(int i=1; i<weight.size(); i++){
          if(answer+1 >= weight[i]){
              answer += weight[i];
          }else{
              answer++;
              break;
          }
      }
      
      return answer;
  }
  ```

  엥..ㅋㅋ엥??

  answer++하고 break;하는게 아니라

  break;하고 return answer+1; 로 하니깐 다 통과함 왜지? 다 똑같은데 왜 answer++해서 하는건 오류가 날까







## 등굣길

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/42898)

- 75/100

  ```cpp
  #include <string>
  #include <vector>
  
  using namespace std;
  
  int map[101][101];
  
  int solution(int m, int n, vector<vector<int>> puddles) {
      int answer = 0;
      for(int i=1; i<=n; i++){
          for(int j=1; j<=m ;j++){
              map[i][j] = 1;
          }
      }
      for(int i=0; i<puddles.size(); i++){
          map[puddles[i][1]][puddles[i][0]] =0;
      }
       for(int i=2; i<=n; i++){
          for(int j=2; j<=m ;j++){
              if(map[i][j] ==0) continue;
              map[i][j] = (map[i-1][j] + map[i][j-1]) % 1000000007;
          }
      }
      
      return map[n][m];
  }
  ```

  정확성 테스트 1,9,10

  효율성 테스트 1,7

정답 풀이 보는데 나랑 비슷한데 다른점은 dp 배열과 puddles를 -1로 뒀다는 거고.. 왜 틀린지 몰겠음.. map 자체로 dp화하는 게 패착인가

- 정답

  ```cpp
  #include <string>
  #include <vector>
  
  using namespace std;
  
  int map[101][101];
  int dp[101][101];
  
  int solution(int m, int n, vector<vector<int>> puddles) {
      int answer = 0;
      for(int i=0; i<puddles.size(); i++){
          map[puddles[i][1]][puddles[i][0]] =-1;
      }
      dp[0][1] = 1;
       for(int i=1; i<=n; i++){
          for(int j=1; j<=m ;j++){
              if(map[i][j] ==-1){
                  dp[i][j] = 0;
              }
              else dp[i][j] = (dp[i-1][j] + dp[i][j-1])%1000000007;
          }
      }
      
      return dp[n][m];
  }
  ```





## 순위

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/49191)

처음 접근 : 패배한 애들만 nth[100]에서 ++해준다. win이면 변동없음

근데 이걸 그래프로 어떻게 이용하는거지?

다른 사람 정답 풀이 봤고 : 플로이드-와샬 알고리즘를 이용한다고 한다. 와우.. 생각지도 못한 알고리즘...

- 정답 소스

  ```cpp
  #include <string>
  #include <vector>
  
  using namespace std;
  
  bool battle[101][101];
  
  int solution(int n, vector<vector<int>> results) {
      int answer = 0;
      for(int i=0; i<results.size(); i++){
          battle[results[i][0]][results[i][1]] = true;
      }
      for(int k=1; k<=n; k++){
         for(int i=1; i<=n; i++){
             for(int j=1; j<=n; j++){
                 if(battle[i][k] && battle[k][j]) battle[i][j] = true;
             }
         }
      }
      for(int i=1; i<=n; i++){
          int cnt =0;
          for(int j=1; j<=n; j++){
              if(battle[i][j] || battle[j][i]) cnt++;
          }
          if(cnt == n-1) answer++;
      }
      return answer;
  }
  ```

다시 풀기!!!





## 보행자 천국

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/1832)

- 예제 테케는 다 맞았는데 제출용 테케 실패

  ```cpp
  #include <vector>
  
  using namespace std;
  
  int MOD = 20170805;
  int dx[] = {1,0};
  int dy[] = {0, 1};
  int cnt =0;
  
  void dfs(int m,int n, int x, int y, int index, vector<vector<int> > c){
      if(m-1 == x && y == n-1){
          cnt = ++cnt % MOD;
          return;
      }
      if(c[x][y] == 2){
          int nx = x + dx[index];
          int ny = y + dy[index];
          if (nx >= m || ny >= n) return;
          dfs(m,n,nx,ny,index,c);
      }
      else {
          for(int i=0; i<2; i++){
              int nx = x + dx[i];
              int ny = y + dy[i];
              if (nx >=m || ny >=n || nx < 0 || ny<0 || c[nx][ny] == 1) continue;
              dfs(m,n,nx,ny,i,c);
          }
      }
  }
  
  // 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
  int solution(int m, int n, vector<vector<int>> city_map) {
      dfs(m,n,0,0,0,city_map);
      return cnt;
  }
  ```

왜 틀린거인지 도무지 이해가 안감..

[[프로그래머스\] 보행자천국](https://jayrightthere.tistory.com/entry/프로그래머스-보행자천국)

DP를 이용하면 된다. DFS로도 될 것 같은데 왜 정확성 테스트에서 실패하는지 모르겠따...흠

→ 다시 풀기

- DP 이용 ( 좀 이상..)

  예제 2만 맞음

  ```cpp
  #include <vector>
  
  using namespace std;
  
  int MOD = 20170805;
  int R[501][501];
  int D[501][501];
  
  // 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
  int solution(int m, int n, vector<vector<int>> city_map) {
  	int answer = 0;
  	if (city_map[0][1] == 0) {
  		D[0][1] = 1;
  	}
  	if (city_map[1][0] == 0) {
  		R[1][0] = 1;
  	}
  	for (int i = 1; i < city_map.size(); i++) {
  		for (int j = 1; j < city_map[i].size(); j++) {
  			if (city_map[i][j] == 0) {
  				D[i][j] = (R[i][j - 1] + D[i - 1][j]) % MOD;
  				R[i][j] = (R[i][j - 1] + D[i - 1][j]) % MOD;
  			}
  			else if (city_map[i][j] == 2) {
  				D[i][j] = D[i - 1][j] % MOD;
  				R[i][j] = R[i][j - 1] % MOD;
  			}
  		}
  	}
  	answer = (D[m - 1][n - 1] + R[m - 1][n - 1]) % MOD;
  	return answer;
  }
  ```

- 정답 DP (첨엔 실패였음 근데 초기화하니깐 성공함)

  ```cpp
  #include <vector>
  
  using namespace std;
  
  int MOD = 20170805;
  int R[501][501];
  int D[501][501];
  
  // 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
  int solution(int m, int n, vector<vector<int>> city_map) {
  	int answer = 0;
       for (int i = 1; i <= m; i++) {
          for (int j = 1; j <= n; j++) {
              R[i][j] = D[i][j] = 0;
          }
      }
       
  	D[1][1] = R[1][1] = 1;
  	for (int i = 1; i <=m; i++) {
  		for (int j = 1; j <=n; j++) {
  			if (city_map[i-1][j-1] == 0) {
  				D[i][j] += (R[i][j - 1] + D[i - 1][j]) % MOD;
  				R[i][j] += (R[i][j - 1] + D[i - 1][j]) % MOD;
  			}
  			else if (city_map[i-1][j-1] == 2) {
  				D[i][j] = D[i - 1][j] % MOD;
  				R[i][j] = R[i][j - 1] % MOD;
  			}
  		}
  	}
  	answer = (D[m - 1][n] + R[m][n - 1]) % MOD;
  	return answer;
  }
  ```

참고해서 풀긴 풀었는데 왜 저렇게 해야하는지.. 어렴풋이만 알겠음.



## 가장 긴 팰린드롬

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/12904)

- 25.2점 엥

  ```cpp
  #include <iostream>
  #include <string>
  #include <vector>
  
  using namespace std;
  
  string reverseString(string original){
      vector<char> s;
      for(int i=0; i<original.size(); i++){
          s.push_back(original[i]);
      }
      string result = "";
      while(!s.empty()){
          result += s.back();
          s.pop_back();
      }
      return result;
  }
  
  int solution(string s)
  {
      int answer=0;
      for(int i=1; i<s.size(); i++){
          string tmp = s;
          string original = tmp.substr(0,i+1);
          if(original == reverseString(original)) answer = i+1;
      }
      return answer;
  }
  ```

- 정확성은 다 맞고 효율성 테케 틀림

  ```cpp
  #include <iostream>
  #include <string>
  #include <vector>
  
  using namespace std;
  
  string reverseString(string original){
      vector<char> s;
      for(int i=0; i<original.size(); i++){
          s.push_back(original[i]);
      }
      string result = "";
      while(!s.empty()){
          result += s.back();
          s.pop_back();
      }
      return result;
  }
  
  int solution(string s)
  {
      int answer=0;
      for(int i=0; i<s.size(); i++){
          int val =0;
          for(int j=1; j<=s.size()-i; j++){
              string tmp = s;
              string original = tmp.substr(i,j);
              if(original == reverseString(original)) val = j;
          }
          if(answer < val) answer = val;
      }
      
      return answer;
  }
  ```

- 효율성 하나만 틀림 왜 시간초과일까..

  ```cpp
  #include <iostream>
  #include <string>
  
  using namespace std;
  
  bool isReverse(string origin){
      string other = origin;
      for(int i=0,j=other.size()-1; i<origin.size(),j>=0; i++,j--){
          if(other[j] != origin[i]) return false;
      }
      return true;
  }
  
  int solution(string s)
  {
      int answer=0;
      for(int i=0; i<s.size(); i++){
          int val =0;
          for(int j=s.size()-i; j>=1; j--){
              string tmp = s;
              string original = tmp.substr(i,j);
              if(isReverse(original)){
                  val = j;
                  break;
              }
          }
          if(answer < val) answer = val;
      }
      
      return answer;
  }
  ```



## 거스름돈



- 시도한 코드

  냅색이나 동전교환 문제랑 비슷해서 첨엔 그 식대로 DP로 하다가 18이 나와서 다시 짬.

  그래서 dfs 이용했는데 7이 나옴..

  ```cpp
  #include <string>
  #include <vector>
  
  using namespace std;
  
  int cnt;
  vector<int> val;
  
  void dfs(int index, int sum, int n) {
  	if (sum == n) {
  		cnt++;
  		return;
  	}
  	for (int i = index; i < val.size(); i++) {
  		dfs(i+1, sum + val[i], n);
  	}
  }
  
  int solution(int n, vector<int> money) {
  
  	for (int i = 0; i < money.size(); i++) {
  		int having = money[i];
  		int num = 1;
  		int ans = 1;
  		while (ans <= n) {
  			ans = having * num;
  			if (ans == n) {
  				cnt++;
  				break;
  			}
  			if(ans<n) val.push_back(ans);
  			num++;
  		}
  	}
  	dfs(0, 0, n);
  	return cnt;
  }
  ```

아.. 진짜 난 빠가다... ㅠㅜ 냅색 알고 때 썼던 거 그대로 쓰면 되는 거였음 ㅠㅜㅠㅜ

냅색은 대신 가치가 있던 거고 얘는 걍 경우의 수니깐 dp[0] = 1로 두고 계산하면 되는 거였음..

다른 사람 코드 참고해서 내 식대로 품

- 만점

  ```cpp
  #include <string>
  #include <vector>
  
  using namespace std;
  
  int dp[100001];
  
  int solution(int n, vector<int> money) {
      int answer =0;
      dp[0] = 1;
     
      for(int i=0; i<money.size(); i++){
          for(int j=money[i]; j<=n; j++){
              dp[j] += dp[j-money[i]] % 1000000007;
          }
      }
      answer = dp[n];
  	return answer;
  }
  ```



## 블록 이동하기

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/60063)

- 보기도 싫음.. 완전 노가다 코드 ㅠ 실패!

  ```cpp
  #include <string>
  #include <vector>
  #include <queue>
  
  using namespace std;
  
  int dir[][2] = { {1,1}, {1,-1}, {-1, -1},{-1,1} };
  
  struct robot {
  	int x1, y1, x2, y2;
  	int index, time;
  };
  
  bool ch[101][101][101][101];
  
  int rotate(int index) {
  	if (index == 3) {
  		index = 0;
  	}
  	else {
  		index++;
  	}
  	return index;
  }
  
  int reRotate(int index) {
  	if (index == 0) {
  		index = 3;
  	}
  	else {
  		index--;
  	}
  	return index;
  }
  
  int solution(vector<vector<int>> board) {
  	int answer = 987654321;
  	int N = board.size();
  	robot ro;
  	ro.x1 = 0;
  	ro.y1 = 0;
  	ro.x2 = 0;
  	ro.y2 = 1;
  	ro.index = 0;
  	ro.time = 0;
  	queue<robot> q;
  	q.push(ro);
  	ch[0][0][0][1] = true;
  	while (!q.empty()) {
  		robot tmp = q.front();
  		int x1 = tmp.x1;
  		int y1 = tmp.y1;
  		int x2 = tmp.x2;
  		int y2 = tmp.y2;
  		int index = tmp.index;
  		int time = tmp.time;
  		q.pop();
  		if (x1 == N - 1 && y1 == N - 1 && answer > time) {
  			answer = time;
  		}
  		if (x2 == N - 1 && y2 == N - 1 && answer > time) {
  			answer = time;
  		}
  		for (int i = 0; i < 4; i++) {
  			robot r;
  			int rIndex = rotate(index);
  			int nx1 = x1+dir[rIndex][0];
  			int ny1 = y1+ dir[rIndex][1];
  			int nx2 = x2+dir[rIndex][0];
  			int ny2 = y2+dir[rIndex][1];
  			if (nx1 >= 0 && ny1 >= 0 && nx1 < N && ny1 < N && !ch[nx1][ny1][x2][y2] && board[nx1][y1]==0) {
  				r.x1 = nx1;
  				r.y1 = ny1;
  				r.x2 = x2;
  				r.y2 = y2;
  				r.index = rIndex;
  				r.time = time + 1;
  				ch[nx1][ny1][x2][y2] = true;
  				q.push(r);
  			}
  			if (nx2 >= 0 && ny2 >= 0 && nx2 < N && ny2 < N && !ch[x1][y1][nx2][ny2] && board[nx2][y2] ==0) {
  				r.x1 = x1;
  				r.y1 = y1;
  				r.x2 = nx2;
  				r.y2 = ny2;
  				r.index = rIndex;
  				r.time = time + 1;
  				ch[x1][y1][nx2][ny2] = true;
  				q.push(r);
  			}
  		}
  		for (int i = 0; i < 4; i++) {
  			robot r;
  			int rRIndex = reRotate(index);
  			int rnx1 = x1 + dir[rRIndex][0];
  			int rny1 = y2 + dir[rRIndex][1];
  			int rnx2 = x2 + dir[rRIndex][0];
  			int rny2 = y2 + dir[rRIndex][1];
  			if (rnx1 >= 0 && rny1 >= 0 && rnx1 < N && rny1 < N && !ch[rnx1][rny1][x2][y2] && board[rnx1][y1] == 0) {
  				r.x1 = rnx1;
  				r.y1 = rny1;
  				r.x2 = x2;
  				r.y2 = y2;
  				r.index = rRIndex;
  				r.time = time + 1;
  				ch[rnx1][rny1][x2][y2] = true;
  				q.push(r);
  			}
  			if (rnx2 >= 0 && rny2 >= 0 && rnx2 < N && rny2 < N && !ch[x1][y1][rnx2][rny2] && board[rnx2][y2] == 0) {
  				r.x1 = x1;
  				r.y1 = y1;
  				r.x2 = rnx2;
  				r.y2 = rny2;
  				r.index = rRIndex;
  				r.time = time + 1;
  				ch[x1][y1][rnx2][rny2] = true;
  				q.push(r);
  			}
  		}
  	}
  	return answer;
  }
  ```

아 정답 코드 보는데 회전만 신경 쓸게 아니라 로봇이 또 움직여야하는 걸 넣었어야..했다!

*귀찮은* 난이도 높은 시뮬구현 문제 아닌가 싶다..

[프로그래머스 [2020카카오공채\] 블록 이동하기 c++](https://regularmember.tistory.com/177)

→ 다시 풀기!



## 멀리 뛰기

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/12914)

전형적 DP 문제

- 정답~

  ```cpp
  #include <string>
  #include <vector>
  
  using namespace std;
  
  long long dp[2001];
  
  long long solution(int n) {
      long long answer = 0;
      dp[0] = 1;
      dp[1] = 1;
      for(int i=2; i<=n; i++){
          dp[i] = (dp[i-1] + dp[i-2]) % 1234567;
      }
      answer = dp[n];
      return answer;
  }
  ```



## 기지국 설치

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/12979)

- 단순하게 품.. 30점

  ```cpp
  #include <iostream>
  #include <vector>
  using namespace std;
  
  int solution(int n, vector<int> stations, int w)
  {
      int answer = 0;
      vector<bool> ch(n+1, false);
      for(int i=0; i<stations.size(); i++){
          int index = stations[i];
          ch[index] = true;
          for(int i=1; i<=w; i++){
              if(index -i >0){
                  ch[index-i] = true;
              }
              if(index+i<=n){
                  ch[index+i] = true;
              }
          }
      }
      int cnt=0;
      for(int i=1; i<=n; i++){
          if(!ch[i]){
              cnt++;
          }
      }
      answer = cnt / (w+1);
      return answer;
  }
  ```

- 약 DFS? 이용해서 풀었는데 정확성: 50 효율성 0

  정확성에서 21 개 중에 6개 틀림.. 효율성은 4개인데 시간 초과 으아!

  ```cpp
  #include <iostream>
  #include <vector>
  using namespace std;
  
  int ans = 987654321;
  
  int checkAllTrue(vector<bool> ch, int n){
      for(int i=1; i<=n; i++){
          if(!ch[i]){
              return i;
          }
      }
      return -1;
  }
  
  void dfs(int index, int w, int n, vector<bool> ch, int cnt){
      if(index == -1){
          if(cnt < ans) ans = cnt;
          return;
      }
      if(index>n) return;
      ch[index] = true;
      for(int i=1; i<=w; i++){
          if(index -i >0 && !ch[index-i]){
              ch[index-i] = true;
          }
          if(index+i<=n && !ch[index+i]){
              ch[index+i] = true;
          }
      } 
      int nextIndex = checkAllTrue(ch,n);
      if (nextIndex + w <= n && !ch[nextIndex + w] && nextIndex != -1) dfs(nextIndex + w, w, n, ch, cnt+1);
  	else dfs(nextIndex, w, n, ch, cnt+1);
  }
  
  int solution(int n, vector<int> stations, int w)
  {
      vector<bool> ch(n+1, false);
      for(int i=0; i<stations.size(); i++){
          int index = stations[i];
          ch[index] = true;
          for(int i=1; i<=w; i++){
              if(index -i >0){
                  ch[index-i] = true;
              }
              if(index+i<=n){
                  ch[index+i] = true;
              }
          }
      }
      int minVal = 987654321;
      for(int i=1; i<=n; i++){
          if(!ch[i] && minVal > i){
              minVal = i;
          }
      }
      if(minVal + w <=n && !ch[minVal+w]) dfs(minVal+w, w,n,ch,0);
      else dfs(minVal,w,n,ch,0);
      return ans;
  }
  ```

[171. 기지국설치(Programmers)](https://willbfine.tistory.com/381)

- 그리디 문제이다. 정답 소스 : 개천재인듯

  ```cpp
  #include <iostream>
  #include <vector>
  using namespace std;
  
  int solution(int n, vector<int> stations, int w)
  {
      int ans = 0;
      int index = 0;
      int location = 1;
      while(location<=n){
          if(index<stations.size() && location>=stations[index]-w){
              location = stations[index] + w + 1;
              index++;
          }else{
              location += 2*w + 1;
              ans++;
          }
      }
      return ans;
  }
  ```

→ 다시 풀기.. 프그에서는 정답을 내서 풀었다고 체크 되어 있겠지만 다시 풀자!

어떻게 이런 천재적인 생각을 하지?!



## 숫자 게임

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/12987)

- 틀림.. 오름차순 정렬하고 비교했음

  정확성: 23점

  효율성: 0점

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  
  using namespace std;
  
  int solution(vector<int> A, vector<int> B) {
      int answer = 0;
      sort(A.begin(), A.end(), greater<int>());
      sort(B.begin(), B.end(), greater<int>());
      for(int i=0; i<A.size(); i++){
          if(A[i] < B[i]) answer++;
      }
      return answer;
  }
  ```

그리고 dfs로 조합 구하듯 했는데 틀리고 시간초과나고 장난아님..

[[Lv3\] 숫자 게임](https://ydeer.tistory.com/65)

정답 코드 참고해서 보는데 내가 생각한 처음 접근법이 맞았다 근데 왜 난.. 졸라 틀렸던 걸까?

→ 알았다!!!! index 별로 비교해서 그럼 위 정답 소스는 A한테 이길 경우만 Bindex를 줄여서 갔다. 이길 가능성까지 계속 패를 큰거와 비교하는거임..

단순하게 크고 작고를 비교해서는 안됐나보다 뭔 예외가 있던거 같은데 무슨 예외인지 모르겠다..

쓸패는 버려라.. *A를 이길 수 있다면 써서 이기고(indexb--), 진다면 가장 왼편의 쓰레기를 가져올테니 이번 패는 안 쓰는거죠.*

- 정답 소스

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  
  using namespace std;
  
  int solution(vector<int> A, vector<int> B) {
      int ans = 0;
      sort(A.begin(), A.end());
      sort(B.begin(), B.end());
      int N = A.size();
      int a = N-1;
      int b = N-1;
      while(a>=0){
          int aVal = A[a];
          int bVal = B[b];
          if(bVal > aVal){
              b--;
              ans++;
          }
          a--;
      }
      return ans;
  }
  ```

허무쓰.. 흠.. 다시 풀어보기



## N-Queen

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/12952)

- 정답 근데 약간 너무 코드 이상한듯..>?

  효율 없게 짠거같다... dfs를 이용해줬고 문제는 check 배열 true 와 false 였다. 굉장히 많은 시간을 소비함.. 비쥬얼스튜디오에서 디버깅 엄청 돌려서 알아냈다..

  그리고 n 범위가 작기 때문에 이런식으로 푼거다 완탐+dfs!

  ```cpp
  #include <string>
  #include <vector>
  
  using namespace std;
  
  int board[13][13];
  int ans = 0;
  bool ch[13][13];
  int N;
  
  void checkTrue(int row, int col) {
  	for (int i = 0; i < N; i++) {
  		ch[col][i] = true;
  		ch[i][row] = true;
  		if(row+i <N && col+i<N)ch[col+i][row+i] = true;
  		if (row - i >= 0 && col - i >= 0) ch[col - i][row - i] = true;
  		if (row - i >= 0 && col + i < N) ch[col + i][row - i] = true;
  		if (row + i < N && col - i >= 0) ch[col - i][row + i] = true;
  	}
  }
  void checkFalse() {
  	for (int i = 0; i < N; i++) {
  		for (int j = 0; j < N; j++) {
  			ch[i][j] = false;
  		}
  	}
  	vector<pair<int, int> > point;
  	for (int i = 0; i < N; i++) {
  		for (int j = 0; j < N; j++) {
  			if (board[i][j] == 1) {
  				point.push_back({ i,j });
  			}
  		}
  	}
  	for (int i = 0; i < point.size(); i++) {
  		checkTrue(point[i].second, point[i].first);
  	}
  }
  
  void dfs(int qCnt, int col) {
  	if (qCnt == N) {
  		ans++;
  		return;
  	}
  	for (int row = 0; row < N; row++) {
  		if (ch[col][row]) continue;
  		board[col][row] = 1;
  		checkTrue(row, col);
  		dfs(qCnt + 1, col + 1);
  		board[col][row] = 0;
  		checkFalse();
  	}
  }
  
  int solution(int n) {
  	N = n;
  	dfs(0, 0);
  	return ans;
  }
  ```

ㅋㅋ.. 나왜케 빡세게 푼거지??

아 어차피 col를 따로 체크하니깐 행은 필요없고 열만 계속 true하면 됐었구나..

[N-Queen 알고리즘](http://sooyoung32.github.io/dev/2016/03/14/n-queen-algorithm.html)

이분 코드 깔끔..



## 배달

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/12978)

- bfs로 풀었는데 40점 맞음

  뒤로 가서는 segmentation fault 나고 실패함 이유 = map의 범위 50이 아니라 51이여야함

  ```cpp
  #include <iostream>
  #include <vector>
  #include <queue>
  
  using namespace std;
  
  bool vill[51];
  
  int solution(int N, vector<vector<int> > road, int K) {
      int ans = 0;
      vector<int> map[50][50];
      for(int i=0; i<road.size(); i++){
          map[road[i][0]][road[i][1]].push_back(road[i][2]);
          map[road[i][1]][road[i][0]].push_back(road[i][2]);
      }
      queue<pair<int,int> > q;
      vill[1] = true;
      for(int i=2; i<=N; i++){
          if(map[1][i].size() != 0){
              for(int j=0; j<map[1][i].size(); i++){
                  q.push({i,map[1][i][j]});
              }
          }
      }
      while(!q.empty()){
          int next = q.front().first;
          int val = q.front().second;
          q.pop();
          vill[next] = true;
          for(int i=1; i<=N; i++){
              if(map[next][i].empty() || vill[i]) continue;
              for(int j=0; j<map[next][i].size(); i++){
                  if(map[next][i][j] + val <= K){
                      q.push({i, map[next][i][j]+val});
                  }
              }
          }
      }
      for(int i=1; i<=N; i++){
          if(vill[i]) ans++;
      }
      return ans;
  }
  ```

- 인접리스트가 아니라 인접행렬도 도로 수가 많으면 작은 양으로 함 46점 ㅠ

  ```cpp
  #include <iostream>
  #include <vector>
  #include <queue>
  
  using namespace std;
  
  int map[50][50];
  bool vill[51];
  
  int solution(int N, vector<vector<int> > road, int K) {
      int ans = 0;
      for(int i=0; i<road.size(); i++){
          if(map[road[i][0]][road[i][1]] > 0 && map[road[i][0]][road[i][1]] > road[i][2]){
              map[road[i][0]][road[i][1]] = road[i][2];
              map[road[i][1]][road[i][0]] = road[i][2];
          }else if(map[road[i][0]][road[i][1]] == 0){
              map[road[i][0]][road[i][1]] = road[i][2];
              map[road[i][1]][road[i][0]] = road[i][2]; 
          }
      }
      queue<pair<int,int> > q;
      vill[1] = true;
      for(int i=2; i<=N; i++){
          if(map[1][i] != 0) q.push({i,map[1][i]});
      }
      while(!q.empty()){
          int next = q.front().first;
          int val = q.front().second;
          q.pop();
          vill[next] = true;
          for(int i=1; i<=N; i++){
              if(map[next][i] ==0 || vill[i]) continue;
              if(map[next][i] + val <= K){
                  q.push({i, map[next][i]+val});
              }
          }
      }
      for(int i=1; i<=N; i++){
          if(vill[i]) ans++;
      }
      return ans;
  }
  ```

답을 찾아보니깐 다익스트라 알고리즘을 이용한다고 한다. 다익스트라인줄 몰랐던데 최단 거리가 아니라서 걍 값만 안넘으면 되는 줄 알았다..흠

- 다익스트라 이용해서 통과

  ```cpp
  #include <iostream>
  #include <vector>
  #include <queue>
  
  using namespace std;
  
  bool check[51];
  int dist[51];
  vector<pair<int,int>> a[51];
  
  int solution(int N, vector<vector<int> > road, int K) {
      int ans=0;
      for(int i=0; i<road.size(); i++){
          a[road[i][0]].push_back({road[i][1],road[i][2]});
          a[road[i][1]].push_back({ road[i][0],road[i][2] });
      }
      for(int i=0; i<=N; i++) dist[i] = 987654321;
      dist[1] = 0;
      for(int i=1; i<=N; i++){
          int minIndex =0;
          for(int j=1; j<=N; j++){
              if(!check[j] && dist[j] < dist[minIndex]) minIndex = j;
          }
          check[minIndex] = true;
          for(int j=0; j<a[minIndex].size(); j++){
              int nextNode = a[minIndex][j].first;
              int val = a[minIndex][j].second;
              if(dist[nextNode] > dist[minIndex]+val){
                  dist[nextNode] = dist[minIndex]+val;
              }
          }
      }
      for(int i=1; i<=N; i++){
          if(dist[i] <=K) ans++;
      }
      return ans;
  }
  ```

→ 다익스트라 코드 포고 품.. 안보고도 풀 수 있게 해야함, 다시 풀기!





## 야근 지수

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/12927)

- 대충 풀었더니 역시 안되죠~ 26점

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  
  using namespace std;
  
  long long solution(int n, vector<int> works) {
      long long answer = 0;
      int index = 0;
      sort(works.begin(), works.end(), greater<int>());
      while(n--){
          if(index == works.size()) index = 0;
          if(works[index] > 0){
              works[index]--;
          }
          index++;
      }
      for(int i=0; i<works.size(); i++){
          long long val = works[i] * works[i];
          answer += val;
      }
      return answer;
  }
  ```

뭘해도 안되길래 검색했다 요지는 최대값을 없애는 것.. 와우

[프로그래머스 문제풀이 level3 - 야근 지수 | rajephon's blog](https://blog.rajephon.dev/2018/10/14/programmers-solution-level3-no-overtime/)

와.. 이렇게 생각해야하는 구나 근데 어떻게 이렇게 생각해 ㅋㅋ

- 정답 소스

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  #include <queue>
  
  using namespace std;
  
  long long solution(int n, vector<int> works) {
      long long answer = 0;
      priority_queue<int, vector<int>, less<int>> pq;
      for(int i=0; i<works.size(); i++){
          pq.push(works[i]);
      }
      while(n--){
          if(pq.empty()) break;
          int top = pq.top();
          pq.pop();
          top--;
          if(top >0) pq.push(top);
      }
      while(!pq.empty()){
          long long val = pq.top() * pq.top();
          pq.pop();
          answer += val;
      }
      
      return answer;
  }
  ```

**for문으로 안 넣고 \*priority_queue q(works.begin(), works.end());\* 이렇게 초기화 가능**



## 줄 서는 방법

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/12936)

- dfs로 풀었는데 효율성에서 시간초과남..

  63점.. n이 20이하라 괜찮을 줄 알았는데 무리였나보다 (정확성에서도 2개가 시간초과)

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  
  using namespace std;
  vector<vector<int>> ans;
  bool ch[21];
  
  void dfs(int cnt, vector<int> result, int n){
      if(cnt==n){
          ans.push_back(result);
          return;
      }
      for(int i=1; i<=n; i++){
          if(ch[i]) continue;
          ch[i] = true;
          result.push_back(i);
          dfs(cnt+1, result, n);
          ch[i] = false;
          result.pop_back();
      }
  }
  vector<int> solution(int n, long long k) {
      vector<int> answer;
      dfs(0,{},n);
      sort(ans.begin(), ans.end());
      for(int i=0; i<ans[k-1].size(); i++){
          answer.push_back(ans[k-1][i]);
      }
      return answer;
  }
  ```

규칙이 있어서 그걸 캐치하고 계산해서 푸는 문제였다; 지금은 졸려서 머리가 안돌아가니깐 자고 일어나서 다시 풀기



## 최고의 집합

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/12938)

- 80점.. 효율성에서 4개 틀림..

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  
  using namespace std;
  
  vector<int> solution(int n, int s) {
      vector<int> answer;
      int etc = s % n;
      int val = s / n;
      if(val ==0){
          return {-1};
      }
      for(int i=0; i<n; i++){
          answer.push_back(val);
      }
      if(etc>0){
          int index =0;
          for(int i=0; i<etc; i++){
              if(index == n) index =0;
              answer[index]++;
              index++;
          }
      }
      sort(answer.begin(), answer.end());
      return answer;
  }
  ```

- 우선순위 큐로 최소를 뿔려봤는데 오히려 효율성 5개틀린다

  코드도 드러움..

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  #include <queue>
  
  using namespace std;
  
  vector<int> solution(int n, int s) {
      vector<int> answer,tmp;
      int etc = s % n;
      int val = s / n;
      if(val ==0){
          return {-1};
      }
      for(int i=0; i<n; i++){
          answer.push_back(val);
      }
      if(etc>0){
          priority_queue<int, vector<int>, greater<int>> pq(answer.begin(), answer.end());
          while(etc--){
              int val = pq.top();
              pq.pop();
              pq.push(val+1);
          }
          while(!pq.empty()){
              tmp.push_back(pq.top());
              pq.pop();
          }
      }
      if(!tmp.empty()){
          for(int i=0; i<n; i++){
              answer[i] = tmp[i];
          }
      }
      return answer;
  }
  ```

- 정답 소스

  와 ㅋㅋ 문제는 sort 이용해서 효율성에서 오류난거였음.. sort가 logn인데 그래도 또 만개를 또 이용하는 건 시간을 잡아먹나..보다.. index 범위 신경쓴것도 지웠음 왜냐면 n개를 넘을 정도면 나뉘어졌을테니깐ㅋㅋ 코드짜면서도 슬쩍 생각했는데 노파심에 추가했더니ㅠ

  그리고 오히려 index++하고 sorting 하니깐 우선순위 큐 이용한거처럼 효율성 더 틀려서 나옴

  ```cpp
  #include <string>
  #include <vector>
  #include <algorithm>
  
  using namespace std;
  
  vector<int> solution(int n, int s) {
      vector<int> answer;
      int etc = s % n;
      int val = s / n;
      if(val ==0){
          return {-1};
      }
      for(int i=0; i<n; i++){
          answer.push_back(val);
      }
      if(etc>0){
          int index =n-1;
          for(int i=0; i<etc; i++){
              answer[index--]++;
          }
      }
      return answer;
  }
  ```



## 하노이의 탑

[프로그래머스](https://programmers.co.kr/learn/courses/30/lessons/12946)

- 정답 소스

  ```cpp
  #include <string>
  #include <vector>
  
  using namespace std;
  vector<vector<int>> answer;
  
  void hanoi(int n, int start, int to,int mid){
      if(n==1){
          answer.push_back({start,to});
          return;
      }
      else{
          hanoi(n-1, start,mid,to);
          answer.push_back({start,to});
          hanoi(n-1, mid,to, start);
      }
  }
  vector<vector<int>> solution(int n) {
      hanoi(n,1,3,2);
      return answer;
  }
  ```