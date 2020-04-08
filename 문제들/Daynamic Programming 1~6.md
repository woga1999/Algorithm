# Daynamic Programming - 1~6

# 1

## 네트워크 선 자르기 (Bottom-Up)

현수는 네트워크 선을 1m, 2m의 길이를 갖는 선으로 자르려고 합니다. 예를 들어 4m의 네트워크 선이 주어진다면

1) 1m+1m+1m+1m 

2) 2m+1m+1m 

3) 1m+2m+1m 

4) 1m+1m+2m 

5) 2m+2m
의 5가지 방법을 생각할 수 있습니다. (2)와 (3)과 (4)의 경우 왼쪽을 기준으로 자르는 위치가 다르면 다른 경우로 생각한다. 그렇다면 네트워크 선의 길이가 Nm라면 몇 가지의 자르는 방법을 생각할 수 있나요?

▣ 입력설명 

첫째 줄은 네트워크 선의 총 길이인 자연수 N(3≤N≤45)이 주어집니다.
▣ 출력설명 

첫 번째 줄에 부분증가수열의 최대 길이를 출력한다.
▣ 입력예제 1 

7
▣ 출력예제 1 

21

DP 이용

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>
#include <unordered_map>
#include <map>

using namespace std;

int dp[51];

int main() {
	ios_base::sync_with_stdio(false);
	int N;
	cin >> N;
	dp[1] = 1;
	dp[2] = 2;
	for (int i = 3; i <= N; i++) {
		dp[i] = dp[i - 1] + dp[i - 2];
	}
	cout << dp[N] << "\n";

	return 0;
}
```

# 2

## Top-Down 방식 (재귀, 메모이제이션)

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int dp[51];

int dfs(int index) {
	if (index == 1 || index == 2) return index;
	if (dp[index] > 0) return dp[index];
	return dp[index] = dfs(index - 1) + dfs(index - 2);
}
int main() {
	ios_base::sync_with_stdio(false);
	int N;
	cin >> N;
	cout << dfs(N) << "\n";

	return 0;
}
```

걍 재귀 : 8초 → 메모이제이션 : 0.3초

# 3

## 도전과제 : 계단오르기(Top-Down : 메모이제이션)

철수는 계단을 오를 때 한 번에 한 계단 또는 두 계단씩 올라간다. 만약 총 4계단을 오른다면 그 방법의 수는 1+1+1+1, 1+1+2, 1+2+1, 2+1+1, 2+2 로 5가지이다. 그렇다면 총 N계단일 때 철수가 올라갈 수 있는 방법의 수는 몇 가지인가?

위와 똑같은데?

**동적 계획법은 Bottom-up**

재귀는 Top-down : 메모이제이션 어쨌든 넓은 범위로 DP라고 하긴 하는 거지 이정도 개념 알아두기 

: DP 파트 3.도전과제 강의

## 도전과제 : 돌다리 건너기(Bottom-Up)

철수는 학교에 가는데 개울을 만났습니다. 개울은 N개의 돌로 다리를 만들어 놓았습니다. 철 수는 돌 다리를 건널 때 한 번에 한 칸 또는 두 칸씩 건너뛰면서 돌다리를 건널 수 있습니다. 철수가 개울을 건너는 방법은 몇 가지일까요?

▣ 입력설명 

첫째 줄은 돌의 개수인 자연수 N(3≤N≤45)이 주어집니다.
▣ 출력설명 

첫 번째 줄에 개울을 건너는 방법의 수를 출력합니다.

▣ 입력예제 1 

7
▣ 출력예제 1 

34

위의 1번 문제랑 같다.근데 21출력이 아니라 N+1 해야한다. 돌로 다리를 만들고 건너는 거니깐

만약 중간 중간 디딜 수 없는 돌다리가 있으면 dp[?] = 0 인거임!!



문제는 많이 응용해서 나올 수 있다

# 4

## 최대 부분 증가수열(LIS : Longest Increasing Subsequence)

N개의 자연수로 이루어진 수열이 주어졌을 때, 그 중에서 가장 길게 증가하는(작은 수에서 큰 수로) 원소들의 집합을 찾는 프로그램을 작성하라. 예를 들어, 원소가 2, 7, 5, 8, 6, 4, 7, 12, 3 이면 가장 길게 증가하도록 원소들을 차례대로 뽑아내면 2, 5, 6, 7, 12를 뽑아내어 길 이가 5인 최대 부분 증가수열을 만들 수 있다.

▣ 입력설명 

첫째 줄은 입력되는 데이터의 수 N(1≤N≤1,000, 자연수)를 의미하고, 둘째 줄은 N개의 입력데이터들이 주어진다.
▣ 출력설명 

첫 번째 줄에 부분증가수열의 최대 길이를 출력한다.

▣ 입력예제 1 

8 

5 3 7 8 6 2 9 4

▣ 출력예제 1 

4

`20점` 나옴 푸하핳!

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int dp[51];
int arr[1001];

int main() {
	ios_base::sync_with_stdio(false);
	int N;
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}
	for (int i = 1; i < N; i++) {
		int res = arr[i];
		for (int j = i + 1; j <= N; j++) {
			if (arr[j] > res) {
				dp[i]++;
				res = arr[j];
			}
		}
	}
	int max = -1;
	for (int i = 1; i <= N; i++) {
		if (max < dp[i]) max = dp[i];
	}
	cout << max+1 << "\n";

	return 0;
}
```

`강의 듣고 고친 코드`

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int dp[1001];
int arr[1001];

int main() {
	ios_base::sync_with_stdio(false);
	int N;
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}
	int max = -1;
	for (int i = 1; i <= N; i++) {
		int res = 0;
		for (int j = i-1; j >= 1; j--) {
			if (arr[i] > arr[j] && res < dp[j]) {
				res = dp[j];
			}
		}
		dp[i] = res + 1;
		if(dp[i] > max) max = dp[i];
	}
	cout << max << "\n";

	return 0;
}
```

# 5

## 최대 선 연결하기

왼쪽의 번호와 오른쪽의 번호가 있는 그림에서 같은 번호끼리 선으로 연결하려고 합니다. 왼쪽번호는 무조건 위에서부터 차례로 1부터 N까지 오름차순으로 나열되어 있습니다. 오른쪽의 번호 정보가 위부터 아래 순서로 주어지만 서로 선이 겹치지 않고 최대 몇 개의 선 을 연결할 수 있는 지 구하는 프로그램을 작성하세요.

위의 그림은 오른쪽 번호 정보가 4 1 2 3 9 7 5 6 10 8 로 입력되었을 때 선이 서로 겹치지 않고 연결할 수 있는 최대 선을 개수 6을 구한 경우입니다.

▣ 입력설명 

첫 줄에 자연수 N(1<=N<=100)이 주어집니다. 두 번째 줄에 1부터 N까지의 자연수 N개의 오른쪽 번호 정보가 주어집니다. 순서는 위쪽번호 부터 아래쪽번호 순으로입니다.
▣ 출력설명 

첫 줄에 겹치지 않고 그을 수 있는 최대선의 개수를 출력합니다.

▣ 입력예제 1 

10 

4 1 2 3 9 7 5 6 10 8

▣ 출력예제 1

6

`응 개틀림~`

```c++
#include <string>
#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;

int r[101];
int l[101];
int dp[101];

int main() {
	ios_base::sync_with_stdio(false);
	int N;
	cin >> N;
	for (int i = 1; i <= N; i++) {
		cin >> r[i];
		l[i] = i;
	}
	int max = 0;
	for (int i = 1; i < N; i++) {
		int res = 0;
		for (int j = i + 1; j <= N; j++) {
			if (l[i] == r[j] && res < j) {
				res = j;
				max++;
			}
		}
	}
	cout << max << "\n";
	return 0;
}
```

왼쪽이 증가하는 수열이니깐

오른쪽도 증가하는 수열이어야한다!

문제만 다르지 앞이랑 같다!

# 6

## 가장 높은 탑 쌓기

밑면이 정사각형인 직육면체 벽돌들을 사용하여 탑을 쌓고자 한다. 탑은 벽돌을 한 개씩 아래 에서 위로 쌓으면서 만들어 간다. 아래의 조건을 만족하면서 가장 높은 탑을 쌓을 수 있는 프 로그램을 작성하시오.
(조건1) 벽돌은 회전시킬 수 없다. 즉, 옆면을 밑면으로 사용할 수 없다. (조건2) 밑면의 넓이가 같은 벽돌은 없으며, 또한 무게가 같은 벽돌도 없다. (조건3) 벽돌들의 높이는 같을 수도 있다. (조건4) 탑을 쌓을 때 밑면이 좁은 벽돌 위에 밑면이 넓은 벽돌은 놓을 수 없다. (조건5) 무게가 무거운 벽돌을 무게가 가벼운 벽돌 위에 놓을 수 없다.

`걍 싹 틀림`

    #include <string>
    #include <vector>
    #include <iostream>
    #include <algorithm>
    
    using namespace std;


​    
```c++
struct brick {
	int height, weight, width;
	brick(int wi, int he, int we) {
		width = wi;
		height = he;
		weight = we;
	}
};
int dp[101];
vector<brick> a;

int main() {
	ios_base::sync_with_stdio(false);
	int N;
	cin >> N;
	for (int i = 1; i <= N; i++) {
		int x, y, z;
		cin >> x >> y >> z;
		a.push_back(brick(x, y, z));
	}
	int max =- 1;
	for (int i = 0; i < N-1; i++) {
		int res = a[i].height;
		for (int j = i + 1; j < N; j++) {
			if (a[i].width > a[j].width && a[i].weight > a[j].weight) {
				res += a[j].height;
			}
		}
		dp[i] = res;
		if (dp[i] > max) max = dp[i];
	}
	cout << max << "\n";
	return 0;
}
```

엥? 밑면의 넓이대로 내림차순 정렬함

그리고 무게로 LIS 알고리즘 하면 됨..

index 몇 번이 제일 꼭대기라고 생각하고 LIS 적용하면 된다

꼭대기에 있는 무게보다 무거워야 한다 그 밑에 깔려 있는게! 이미 너비는 내림차순으로 정렬했으니깐

    #include <string>
    #include <vector>
    #include <iostream>
    #include <algorithm>
    
    using namespace std;


​    
```c++
struct brick {
	int height, weight, width;
	brick(int wi, int he, int we) {
		width = wi;
		height = he;
		weight = we;
	}

	bool operator<(brick &d) {
		return width> d.width;
	}
};
int dp[101];
vector<brick> a;

int main() {
	ios_base::sync_with_stdio(false);
	int N;
	cin >> N;
	for (int i = 1; i <= N; i++) {
		int x, y, z;
		cin >> x >> y >> z;
		a.push_back(brick(x, y, z));
	}
	sort(a.begin(), a.end());
	int max =- 1;
	for (int i = 0; i < N; i++) {
		int res = 0;
		for (int j = i -1; j >= 0; j--) {
			if (res < dp[j] && a[i].weight < a[j].weight) {
				res = dp[j];
			}
		}
		dp[i] = res + a[i].height;
		if (dp[i] > max) max = dp[i];
	}
	cout << max << "\n";
	return 0;
}
```