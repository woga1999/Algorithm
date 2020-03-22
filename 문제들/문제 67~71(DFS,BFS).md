# 강의 문제 67~71(DFS,BFS)

# 67

### 최소비용(DFS:인접행렬)

▣ 입력예제 1
5 8 

1 2 12 

1 3 6 

1 4 10 

2 3 2 

2 5 2 

3 4 3 

4 2 2 

4 5 5

▣ 출력예제 1
13

```c++
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
int arr[21][21];
bool check[21];
int minV;

void dfs(int v, int sum) {
	if (v == N) {
		if (sum < minV) minV = sum;
		return;
	}
	check[v] = true;
	for (int i = 1; i <= N; i++) {
		if (check[i] || arr[v][i] ==0) continue;
		dfs(i, sum + arr[v][i]);
		check[i] = false;
	}
}

int main() {
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		int x, y, k;
		cin >> x >> y >> k;
		arr[x][y] = k;
	}
	minV = 987654321;
	dfs(1, 0);
	cout << minV << "\n";
	return 0;
}
```

**최소 비용 = 최단 거리 구하는 알고리즘**

[다익스트라 알고리즘(Dijkstra Algorithm)](https://hsp1116.tistory.com/42)

# 68

### 최소비용 (DFS:가중치 방향그래프 인접리스트)

▣ 입력예제 1
5 8 

1 2 12 

1 3 6 

1 4 10 

2 3 2 

2 5 2 

3 4 3 

4 2 2 

4 5 5

▣ 출력예제 1
13

```c++
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
vector<pair<int,int> > arr[21];
bool check[21];
int minV;

void dfs(int v, int sum) {
	if (v == N) {
		if (sum < minV) minV = sum;
		return;
	}
	check[v] = true;
	for (int i = 0; i < arr[v].size(); i++) {
		if (check[arr[v][i].first]) continue;
		dfs(arr[v][i].first, sum + arr[v][i].second);
		check[arr[v][i].first] = false;
	}
}

int main() {
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		int x, y, k;
		cin >> x >> y >> k;
		arr[x].push_back({ y,k });
	}
	minV = 987654321;
	dfs(1, 0);
	cout << minV << "\n";
	return 0;
}
```

#define x first

#define y second

arr[v][i].x (o)

# 69

### 이진트리 넓이우선탐색(BFS)

아래 그림과 같은 이진트리를 넓이우선탐색해 보세요. 간선 정보 6개를 입력받아 처리해보세요.

▣ 출력예제 1
1 2 3 4 5 6 7

**큐 자료구조 직접 구현**

```c++
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int Q[100];
int front=-1, back=-1;
int check[10];
vector<int> arr[10];

int main() {
	int a, b;
	for (int i = 1; i <= 6; i++) {
		cin >> a >> b;
		arr[a].push_back(b);
		arr[b].push_back(a);
		//무방향그래프라
	}
	Q[++back] = 1;
	check[1] = 1;
	while (front < back) {
		//front == back 큐가 비어있다
		int x = Q[++front];
		printf("%d ", x);
		for (int i = 0; i < arr[x].size(); i++) {
			if (check[arr[x][i]] == 1) continue;
			check[arr[x][i]] = 1;
			Q[++back] = arr[x][i];
		}
	}
	return 0;
}
```

# 70

### 그래프 최단거리(BFS)

다음 그래프에서 1번 정점에서 각 정점으로 가는 최소 이동 간선수를 출력하세요.

```c++
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

queue<int> q;
vector<int> arr[21];
bool check[21];
int dis[21];

int main() {
	int N, M;
	cin >> N >> M;
	for (int i = 0; i < M; i++) {
		int x, y;
		cin >> x >> y;
		arr[x].push_back(y);
	}
	q.push(1);
	check[1] = true;
	while (!q.empty()) {
		int x = q.front();
		q.pop();
		for (int i = 0; i < arr[x].size(); i++) {
			if (check[arr[x][i]])continue;
			check[arr[x][i]] = true;
			q.push(arr[x][i]);
			dis[arr[x][i]] = dis[x] + 1;
		}
	}
	for (int i = 2; i <= N; i++) {
		printf("%d : %d\n", i, dis[i]);
	}
	return 0;
}
```

# 71

### 송아지 찾기

`틀림..` 점프 수를 어디서 세야하는지 도무지 모르겠다

```c++
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int jump[] = { 1, -1, 5 };
int check[10000];

int main() {
	int S, E,min=987654321;
	queue<int> q;
	cin >> S >> E;
	q.push(S);
	check[S] = true;
	int cnt = 0;
	bool flag = false;
	while (!q.empty()) {
		int x = q.front();
		q.pop();
		for (int i = 0; i < 3; i++) {
			int current = x + jump[i];
			if (check[current]) continue;
			if (!flag) {
				cnt++;
				flag = true;
			}
			check[current] = true;
			if (current == E) {
				if (cnt < min) min = cnt;
				cnt = 0;
			}
			else {
				q.push(current);
			}
		}
		flag = false;
	}
	cout << cnt << "\n";
	return 0;
}
```

**답**

```c++
#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int jump[] = { 1, -1, 5 };
int check[10001];

int main() {
	int S, E,min=987654321;
	queue<int> q;
	cin >> S >> E;
	q.push(S);
	check[S] = 1;
	while (!q.empty()) {
		int x = q.front();
		q.pop();
		for (int i = 0; i < 3; i++) {
			int current = x + jump[i];
			if (current<=0 || current>10000 || check[current]) continue;
			if (current == E) {
				printf("%d\n", check[x]);
				exit(0);
			}
			check[current] = check[x]+1;
			q.push(current);
		}
	}
	return 0;
}
```