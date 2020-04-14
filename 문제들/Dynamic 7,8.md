# Dynamic 7,8

# 7

### 알리바바와 40인의 도둑(Bottom-Up)

알리바바는 40인의 도둑으로부터 금화를 훔쳐 도망치고 있습니다. 알리바바는 도망치는 길에 평소에 잘 가지 않던 계곡의 돌다리로 도망가고자 한다. 계곡의 돌다리는 N×N개의 돌들로 구성되어 있다. 각 돌다리들은 높이가 서로 다릅니다. 해당 돌다리를 건널때 돌의 높이 만큼 에너지가 소비됩니다. 이동은 최단거리 이동을 합니다. 즉 현재 지점에서 오른쪽 또는 아래쪽으로만 이동해야 합니다. N*N의 계곡의 돌다리 격자정보가 주어지면 (1, 1)격자에서 (N, N)까지 가는데 드는 에너지의 최소량을 구하는 프로그램을 작성하세요. 만약 N=3이고, 계곡의 돌다리 격자 정보가 다음과 같다면

최소에너지를 구해라

▣ 입력설명

첫 번째 줄에는 자연수 N(1<=N<=20)이 주어진다. 두 번째 줄부터 계곡의 N*N 격자의 돌다리 높이(10보다 작은 자연수) 정보가 주어진다.
▣ 출력설명 

첫 번째 줄에 (1, 1)출발지에서 (N, N)도착지로 가기 위한 최소 에너지를 출력한다.
▣ 입력예제 1 

5 

3 7 2 1 9 

5 8 3 9 2 

5 3 1 2 3 

5 4 3 2 1 

1 7 5 2 4
▣ 출력예제 1 

25

`20점` 나머지는 다 시간초과 아마 중간에서 break가 없어서 그런거 같기도..

    #include <string>
    #include <vector>
    #include <iostream>
    #include <algorithm>
    #include <queue>
    
    using namespace std;


​    
```c++
int arr[21][21];
int dp[21][21];
int dx[] = { 1,0 };
int dy[] = { 0,1 };

int main() {
	ios_base::sync_with_stdio(false);
	int N;
	cin >> N;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> arr[i][j];
		}
	}
	queue<pair<int, int> > q;
	q.push({ 1,1 });
	dp[1][1] = arr[1][1];
	while (!q.empty()) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		for (int i = 0; i < 2; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 1 || ny < 1 || nx >N || ny >N ) continue;
			
			if (dp[nx][ny] == 0) {
				dp[nx][ny] = arr[nx][ny] + dp[x][y];
			}
			else if (dp[nx][ny] != 0 && dp[nx][ny] > arr[nx][ny] + dp[x][y]) {
				dp[nx][ny] = arr[nx][ny] + dp[x][y];
			}
			q.push({ nx,ny });
		}
	}
	cout << dp[N][N] << "\n";
	return 0;
}
```



```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int arr[21][21];
int dp[21][21];
int N;

int main() {
	ios_base::sync_with_stdio(false);
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}
	dp[0][0] = arr[0][0];
	for (int i = 1; i < N; i++) {
		dp[0][i] = dp[0][i - 1] + arr[0][i];
		dp[i][0] = dp[i - 1][0] + arr[i][0];
	}
	for (int i = 1; i < N; i++) {
		for (int j = 1; j < N; j++) {
			if (dp[i - 1][j] < dp[i][j - 1]) {
				dp[i][j] = dp[i - 1][j] + arr[i][j];
			}
			else {
				dp[i][j] = dp[i][j-1] + arr[i][j];
			}
		}
	}
	cout << dp[N - 1][N - 1];
	return 0;
}
```







# 8

### 40인의 도둑 (Top-Down)

위는 BFS와 DP 이용..이라고 하기엔 여튼 배열을 이용했고

여기선 DFS를 이용할거다

메모이제이션으로!

할려했는데 구현 실패함

    #include <string>
    #include <vector>
    #include <iostream>
    #include <algorithm>
    #include <queue>
    
    using namespace std;


​    
```c++
int arr[21][21];
int dp[21][21];
int dx[] = { 1,0 };
int dy[] = { 0,1 };
int N;

int dfs(int px, int py, int x, int y) {
	if (dp[x][y]) {
		if (dp[x][y] > arr[x][y] + dp[px][py]) {
			return arr[x][y] + dp[px][py];
		}
		else return dp[x][y];
	}
	if (x == N && y == N) {
		return dp[x][y];
	}
	for (int i = 0; i < 2; i++) {
		int nx = x + dx[i];
		int ny = y + dy[i];
		if (nx<1 || ny<1 || nx >N || ny > N) continue;
		dp[nx][ny] = dfs(x, y, nx, ny);
	}
}
int main() {
	ios_base::sync_with_stdio(false);
	cin >> N;
	for (int i = 1; i <= N; i++) {
		for (int j = 1; j <= N; j++) {
			cin >> arr[i][j];
		}
	}
	cout << dfs(0,0,1,1) << "\n";
	return 0;
}
```



**정답 코드**



```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <queue>

using namespace std;

int arr[21][21];
int dp[21][21];
int N;

int dfs(int x, int y) {
	if (dp[x][y]) return dp[x][y];
	if (x == 0) {
		return dp[x][y] = dfs(x, y - 1) + arr[x][y];
	}
	else if (y == 0) {
		return dp[x][y] = dfs(x - 1, y) + arr[x][y];
	}

	return dp[x][y] = min(dfs(x - 1, y), dfs(x, y - 1)) + arr[x][y];
}

int main() {
	ios_base::sync_with_stdio(false);
	cin >> N;
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < N; j++) {
			cin >> arr[i][j];
		}
	}
	dp[0][0] = arr[0][0];
	cout << dfs(N-1,N-1);
	return 0;
}
```

