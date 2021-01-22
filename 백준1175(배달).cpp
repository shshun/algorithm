#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
using namespace std;

//3:41

typedef pair<int, int> p;

#define EM '.'
#define WALL '#'
#define DELIVER 'C'

struct info {
	int x;
	int y;
	int d;
};

int N, M;
int dx[4] = { 0,0,-1,1 };
int dy[4] = { -1,1,0,0 };
char arr[51][51];
bool visit[51][51] = { false, };
int sX, sY;

vector<p> v;
queue<int> lastD;

bool isRange(int x, int y) {
	if (x < 0 || y < 0 || x >= N || y >= M) return false;
	if (arr[x][y] == WALL) return false;

	return true;
}

int bfs(int xx, int yy,int target) {
	bool visit[51][51][4] = { false, };

	//visit[xx][yy] = true;
	queue<info> q;

	if (lastD.empty()) {
		for (int k = 0; k < 4; k++) {
			visit[xx][yy][k] = true;
			q.push({ xx,yy,k });
		}
	}
	else {
		while (!lastD.empty()) {
			int lastDir = lastD.front(); 
			lastD.pop();
			q.push({ xx,yy,lastDir });
			visit[xx][yy][lastDir] = true;
		}
	}

	int tX = v[target].first;
	int tY = v[target].second;

	int distance = 0;
	bool find = false;

	while (!q.empty()) {
		int s = q.size();
		while (s--) {
			info temp = q.front(); 
			q.pop();

			int x = temp.x;
			int y = temp.y;

			if (x == tX && y == tY) {
				find = true;
				lastD.push(temp.d);
			}
			
			if (find == true) continue;

			for (int k = 0; k < 4; k++) {
				if (temp.d == k) continue;

				int nx = x + dx[k];
				int ny = y + dy[k];

				if (isRange(nx, ny) && !visit[nx][ny][k]) {
					visit[nx][ny][k] = true;
					q.push({ nx,ny,k });
				}
			}
		}

		if(!find) distance++;
	}

	if (find) return distance;
	else return -1;
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> N >> M;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> arr[i][j];
			if (arr[i][j] == 'S') {
				arr[i][j] = EM;
				sX = i;
				sY = j;
			}
			else if (arr[i][j] == DELIVER) {
				v.push_back({ i,j });
			}
		}
	}

	int answer = -1;

	///출발점->1->2
	int distOne = bfs(sX, sY, 0);
	int distTwo = 0;
	//cout << distOne << endl;
	if (distOne >= 0) {
		distTwo= bfs(v[0].first, v[0].second, 1);
		//cout << distTwo << endl;
		if (distTwo >= 0) {
			answer = distOne + distTwo;
		}
	}
	//cout << "=======================\n";
	while(!lastD.empty()) lastD.pop();

	///출발점->2->1
	distTwo = bfs(sX, sY, 1);
	//cout << distTwo << endl;
	if (distTwo >= 0) {
		distOne = bfs(v[1].first, v[1].second, 0);
		//cout << distOne << endl;
		if (distOne >= 0) {
			if (answer < 0) answer = distTwo + distOne;
			else answer = min(answer, distTwo + distOne);
		}
	}

	cout << answer << '\n';
	return 0;
}