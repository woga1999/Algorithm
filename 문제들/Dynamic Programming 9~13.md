# Dynamic Programming 9~13

# 9

### 가방문제 (냅샙 알고리즘 )

최고 17kg의 무게를 저장할 수 있는 가방이 있다. 그리고 각각 3kg, 4kg, 7kg, 8kg, 9kg의 무게를 가진 5종류의 보석이 있다. 이 보석들의 가치는 각각 4, 5, 10, 11, 13이다. 이 보석을 가방에 담는데 17kg를 넘지 않으면서 최대의 가치가 되도록 하려면 어떻게 담아야 할까요? 각 종류별 보석의 개수는 **무한이** 많다. 한 종류의 보석을 여러 번 가방에 담을 수 있 다는 뜻입니다.


dp 이용해봤는데 그냥 배열만 쓴거 같다..

하나 맞음

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

struct jew{
	int weight, value;
	jew(int w, int v) {
		weight = w;
		value = v;
	}
};

int N, L;
int dp[31];

int main() {
	ios_base::sync_with_stdio(false);
	cin >> N >> L;
	vector<jew> a;
	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;
		a.push_back(jew(x, y));
	}
	dp[0] = a[0].weight;
	int max = a[0].value;
	for (int i = 1; i < N; i++) {
		if (dp[i - 1] + a[i].weight <= L && max < max + a[i].value) {
			max += a[i].value;
			dp[i] = dp[i - 1] + a[i].weight;
		}
	}
	cout << max << "\n";
	
	return 0;
}
```

난 문제를 잘못쓴줄 알았는데 아니였음 보석이 하나 종류 당 개수가 무한대래

문제 설명에서 무한이라고 나왔구만..

강의 설명보고 정답



```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

struct jew{
	int weight, value;
	jew(int w, int v) {
		weight = w;
		value = v;
	}
};

int N, L;
int dp[10001];

int main() {
	ios_base::sync_with_stdio(false);
	cin >> N >> L;
	vector<jew> a;
	for (int i = 0; i < N; i++) {
		int x, y;
		cin >> x >> y;
		a.push_back(jew(x, y));
	}
	for (int i = 0; i < N; i++) {
		int w = a[i].weight;
		int v = a[i].value;
		for (int j = w; j <= L; j++) {
			if (dp[j] < dp[j - w] + v) {
				dp[j] = dp[j - w] + v;
			}
		}
	}
	cout << dp[L] << "\n";

	return 0;
}
```

# 10

### 동전 교환

다음과 같이 여러 단위의 동전들이 주어져 있을때 거스름돈을 가장 적은 수의 동전으로 교환 해주려면 어떻게 주면 되는가? 각 단위의 동전은 **무한정** 쓸 수 있다.




`정답!`

시간을 너무 많이 들였다;; 

첨에 진짜 이상하게 짰음.. 

min이여서 987654321하고 하니깐 오히려 값만 커지고 이상해짐 왜냐면 dp[1] = dp[0] + 1 이면 987654321+1 하는거니깐 당연히 앞에가 더 크지

그러면서 아예 0으로 두고 하는 걸 생각하는데

dp[j/coin] + j/coin; 뭐 이런식으로 짜고 .. 왜냐면 거슬려 줄 금액에서 나누면 나머지를 또 coin 값만큼 나눠서 개수를 더해야생각했음.. 근데 디버깅하면서 이상한 걸 깨달음.. 3/1 = 3 으로 나오면서 아 이건 아니다 +1 개수다 하면서 다시 짬

명확하게 솔루션을 짜는 법을 기르기

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int dp[501];
int arr[12];

int main() {
	ios_base::sync_with_stdio(false);
	int N,L;
	cin >> N;
	for (int i = 0; i < N; i++) {
		cin >> arr[i];
	}
	cin >> L;
	for (int i = 0; i < N; i++) {
		int coin = arr[i];
		for (int j = arr[i]; j <= L; j++) {
			if (dp[j] == 0) {
				dp[j] = dp[j - coin] + 1;
			}
			else dp[j] = min(dp[j], dp[j - coin] + 1);
		}
	}
	cout << dp[L] << "\n";
	return 0;
}
```

`소스코드`

```c++
#include<bits/stdc++.h>
using namespace std;
int main(){
	ios_base::sync_with_stdio(false);
	freopen("input.txt", "rt", stdin);
	int n, m;
	cin>>n;
	vector<int> coin(n);
	for(int i=0; i<n; i++) cin>>coin[i];
	cin>>m;
	vector<int> dy(m+1, 1000);
	dy[0]=0;
	for(int i=0; i<n; i++){
		for(int j=coin[i]; j<=m; j++){
			dy[j]=min(dy[j], dy[j-coin[i]]+1);
		}
	}
	cout<<dy[m];
	return 0;
}
```

vector로 초기화 가능

# 11

### 최대점수 구하기(냅색 알고리즘)

이번 정보올림피아드대회에서 좋은 성적을 내기 위하여 현수는 선생님이 주신 N개의 문제를 풀려고 합니다. 각 문제는 그것을 풀었을 때 얻는 점수와 푸는데 걸리는 시간이 주어지게 됩 니다. 제한시간 M안에 N개의 문제 중 최대점수를 얻을 수 있도록 해야 합니다. (해당문제는 해당시간이 걸리면 푸는 걸로 간주한다, 한 유형당 한개만 풀 수 있습니다.)



20점!

한 유형당 한개만 풀 수 있대서 한 유형을 무수히 풀었던 앞에 냅색 알고리즘과는 좀 다르게 짜야했다고 생각했다. 그래서 for문 위치를 바꿔서 했는데 예제랑 테케 1개만 맞음 ㅠㅠ

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int dp[1001];
int arr[12];

struct pro {
	int score, time;
	pro(int s, int t) {
		score = s;
		time = t;
	}
};

int main() {
	ios_base::sync_with_stdio(false);
	int N,L,score,time;
	cin >> N >> L;
	vector<pro> a;
	for (int i = 0; i < N; i++) {
		cin >> score >> time;
		a.push_back(pro(score, time));
	}
	for (int i = 0; i <= L; i++) {
		for (int j = 0; j < a.size(); j++) {
			int t = a[j].time;
			int val = a[j].score;
			if (i - t < 0) continue;
			dp[i] = max(dp[i - t] + val, dp[i]);
		}
	}
	cout << dp[L] << "\n";
	return 0;
}
```

아 잘못생각했음 이것도 중복된다.. 그러면 다르게 풀어야지

잠만 이거 좀 이상한대.. 답은 한 유형당 한개만 푼게 아닌데..

아님.. 맞다. 한 유형당 한개임

강의 보고 짬.. 이차원 dp임 

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int dp[101][1001];
int arr[12];

struct pro {
	int score, time;
	pro(int s, int t) {
		score = s;
		time = t;
	}
};

int main() {
	ios_base::sync_with_stdio(false);
	int N,L,score,time;
	cin >> N >> L;
	vector<pro> a;
	for (int i = 0; i < N; i++) {
		cin >> score >> time;
		a.push_back(pro(score, time));
	}
	for (int i = 1; i <= N; i++) {
		int time = a[i - 1].time;
		int sco = a[i - 1].score;
		for (int j = time; j <= L; j++) {
			dp[i][j] = max(dp[i - 1][j - time] + sco, dp[i-1][j]);
		}
	}
	cout << dp[N][L] << "\n";
	return 0;
}
```

실전에서는 이차원배열보다는 일차원으로 해야한다 크기가 너무 큼

일차원 방법이 있음

dp 일차원 배열 **뒤에서(L) 앞으로 전진하면서** 똑같이 time 빼면서 가면 됨

```c++
#include<bits/stdc++.h>
using namespace std;
int main() {
	freopen("input.txt", "rt", stdin);
	ios::sync_with_stdio(false);
	int n, m, ps, pt;
	cin>>n>>m;
	vector<int> dy(m+1);
	for(int i=0; i<n; i++){
		cin>>ps>>pt;
		for(int j=m; j>=pt; j--){
			dy[j]=max(dy[j], dy[j-pt]+ps);
		}			
	}
	cout<<dy[m]<<"\n";		
	return 0;
}
```



# 12

### 플로이드 워샬 알고리즘

N개의 도시가 주어지고, 각 도시들을 연결하는 도로와 해당 도로를 통행하는 비용이 주어질 때 모든 도시에서 모든 도시로 이동하는데 쓰이는 비용의 최소값을 구하는 프로그램을 작성하 세요.



`소스코드`

중점은 3중 포문 k로 다 돌아보는것

```c++
#include<bits/stdc++.h>
using namespace std;
int main(){
	ios_base::sync_with_stdio(false);
	int n, m, a, b, c;
	cin>>n>>m;
	vector<vector<int> > dis(n+1, vector<int>(n+1, 5000));
	for(int i=1; i<=n; i++) dis[i][i]=0;
	for(int i=1; i<=m; i++){
		cin>>a>>b>>c;
		dis[a][b]=c;	
	}
	for(int k=1; k<=n; k++){
		for(int i=1; i<=n; i++){
			for(int j=1; j<=n; j++){
				if(dis[i][j]>dis[i][k]+dis[k][j]){
					dis[i][j]=dis[i][k]+dis[k][j];
				}
			}
		}
	}
	for(int i=1; i<=n; i++){
		for(int j=1; j<=n; j++){
			if(dis[i][j]==5000){
				cout<<"M ";
			}
			else cout<<dis[i][j]<<" ";
		}
		cout<<endl;
	}

	return 0;
}
```

# 13

### 회장 뽑기 (플로이드-워샬 응용)

월드컵축구의 응원을 위한 모임에서 회장을 선출하려고 한다. 이모임은 만들어진지 얼마 되지 않았기 때문에 회원사이에 서로 모르는 사람도 있지만, 몇 사람을 통하면 서로 모두 알 수 있 다. 각 회원은 다른 회원들과 가까운 정도에 따라 점수를 받게 된다. 예를 들어 어느 회원이 다른 모든 회원과 친구이면, 이 회원의 점수는 1점이다. 어느 회원의 점수가 2점이면, 다른 모든 회원이 친구이거나, 친구의 친구임을 말한다. 또한, 어느 회원의 점수가 3점이면, 다른 모든 회원이 친구이거나, 친구의 친구이거나, 친국의 친구의 친구임을 말한다.4점, 5점등은 같은 방법으로 정해진다. 각 회원의 점수를 정할 때 주의할 점은 어떤 두 회원이 친구 사이이면서 동시에 친구의 친구 사이이면, 이 두 사람은 친구사이라고 본다. 회장은 회원들 중에서 점수가 가장 작은 사람이 된다. 회장의 점수와 회장이 될 수 있는 모든 사람을 찾는 프로그램을 작성하시오.

응 개틀림~

아 이게 친구의 친구만 1로 체크하고 다 더하는 줄 알고 그렇게 짰는데 아니였고 그래서 K라고 두고 했더니 완전 이상해졌다.

그래서 강의를 보는데 일단

패착 1 ) 무방향 그래프 xy yx 다 1이다

패착 2) 이것들을 더하면 친구의 친구면 2 친구의 친구의 친구면 3이 된다.

패착 3) 그 줄의 최대값이다. 점수래서 다 더하는 줄 알았더니 행의 제일 큰 값을 구하고

4) 그 중에서 작은 값을 구해서 회장 후보 점수와 수와 후보들을 모두 출력

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int dy[51];

int main() {
	ios_base::sync_with_stdio(false);
	int N;
	cin >> N;
	int x, y;
	vector<vector<int> > dis(N + 1, vector<int>(N + 1, 1000));
	for (int i = 1; i <= N; i++) dis[i][i] = 0;
	while (1) {
		cin >> x >> y;
		if (x == -1 && y == -1) break;
		dis[x][y] = 1;
		dis[y][x] = 1;
	}
	for(int k=1; k<=N; k++){
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dis[i][j] = min(dis[i][j], dis[i][k] + dis[k][j]);
			}
		}
	}
	int min = 987654321;
	for (int i = 1; i <= N; i++) {
		int val = 0;
		for (int j = 1; j <= N; j++) {
			if (dis[i][j] != 1000) {
				if (val < dis[i][j]) val = dis[i][j];
			}
		}
		dy[i] = val;
		if (dy[i] < min) min = dy[i];
	}
	vector<int> chair;
	for (int i = 1; i <= N; i++) {
		if(min == dy[i]) chair.push_back(i);
	}
	cout << min << " " << chair.size() << "\n";
	for (int i = 0; i < chair.size(); i++) {
		cout << chair[i] << " ";
	}
	return 0;
}
```

이건 정답 `소스코드`

```c++
#include<bits/stdc++.h>
using namespace std;
int main(){
	ios_base::sync_with_stdio(false);
	freopen("input.txt", "rt", stdin);	
	int n, a, b, score;
	cin>>n;
	vector<vector<int> > dis(n+1, vector<int>(n+1, 100));
	vector<int> res(n+1);
	for(int i=1; i<=n; i++) dis[i][i]=0;
	while(true){
		cin>>a>>b;
		if(a==-1 && b==-1) break;
		dis[a][b]=1;
		dis[b][a]=1;
	}
	for(int k=1; k<=n; k++){
		for(int i=1; i<=n; i++){
			for(int j=1; j<=n; j++){
				dis[i][j]=min(dis[i][j], dis[i][k]+dis[k][j]);
			}
		}
	}
	score=100;
	for(int i=1; i<=n; i++){
		for(int j=1; j<=n; j++){
			if(i==j) continue;
			res[i]=max(res[i], dis[i][j]);
		}
		score=min(score, res[i]);
	}
	int cnt=0;
	for(int i=1; i<=n; i++){
		if(res[i]==score) cnt++;
	}
	cout<<score<<" "<<cnt<<endl;
	for(int i=1; i<=n; i++){
		if(res[i]==score) cout<<i<<" ";
	}
	return 0;
}
```