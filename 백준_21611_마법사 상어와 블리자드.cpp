#include <iostream>
#include <vector>
#include <queue>

using namespace std;

#define endl '\n'

int N, M;
int arr[52][52];
int boomCnt[4];
int dx[] = {-1,1,0,0 };
int dy[] = {0,0,-1,1 };
int sx, sy;
queue<int> q;
queue<int> tempQ;

void printing() {
	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < N; ++j) {
			cout << arr[i][j] << " ";
		}cout << endl;
	}

}
void input() {
	cin >> N >> M;
	sx = N / 2;
	sy = N / 2;

	for (int i = 0; i < N; ++i) {
		for (int j = 0; j < N; ++j) {
			int input;
			cin >> input;
			arr[i][j] = input;
		}
	}
}
void deleteBall(int d, int s) {
	d--;
	for (int move = 1; move <= s; ++move) {
		int nx = sx + dx[d] * move;
		int ny = sy + dy[d] * move;
		arr[nx][ny] = 0;
	}
}
bool isRange(int x, int y) {
	return x >= 0 && y >= 0 && x < N && y < N;
}

bool continueBoom() {
	bool res = false;
	vector<int> v;
	int cnt = 1;

	int prev = -1;

	while (!q.empty()) {
		if (prev != q.front()) {
			if (cnt >= 4) {
				boomCnt[v.back()] += cnt;
				res = true;
				while(cnt--) v.pop_back();
			}				
			cnt = 1;
		}
		else {
			cnt++;
		}

		v.push_back(q.front());
		prev = q.front();
		q.pop();
	}

	for (int n : v) {
		q.push(n);
	}

	return res;
}

void makeMoreBall() {
	vector<int> v;
	int s = q.size();

	while (s--) {
		if (v.size() == 0) {
			v.push_back(q.front());
			q.pop();
			continue;
		}

		if (!v.empty() && v.back() == q.front()) {
			v.push_back(q.front());
			q.pop();
		}
		else {
			q.push(v.size());
			q.push(v[0]);
			v.clear();
			s++;
		}
	}

	q.push(v.size());
	q.push(v[0]);
	v.clear();
	

}
void moveBall() {
	q = queue<int>();
	int dx[] = { 0,1,0,-1 };
	int dy[] = { -1,0,1,0 };

	int x = sx;
	int y = sy;

	int dir = 0;
	int move = 1;


	while (1) {
		dir %= 4;

		for (int i = 0; i < move; ++i) {
			x += dx[dir];
			y += dy[dir];
			if (!isRange(x, y)) break;
			if (arr[x][y] != 0) {
				q.push(arr[x][y]);
				arr[x][y] = 0;
			}
		}

		if (!isRange(x, y)) break;

		dir++;
		dir %= 4;
		for (int i = 0; i < move; ++i) {
			x += dx[dir];
			y += dy[dir];
			if (!isRange(x, y)) break;
			if (arr[x][y] != 0) {
				q.push(arr[x][y]);
				arr[x][y] = 0;
			}
		}
		if (!isRange(x, y)) break;
		dir++;
		move++;
	}


	while(1){
		if (!continueBoom()) {
			break;
		}
	}

	makeMoreBall();

	x = sx;
	y = sy;
	dir = 0;
	move = 1;

	while (!q.empty()) {
		dir %= 4;
		for (int i = 0; i < move; ++i) {
			x += dx[dir];
			y += dy[dir];
			if (q.empty() || !isRange(x,y)) break;
			arr[x][y] = q.front();
			q.pop();
		}

		if (q.empty() || !isRange(x, y)) break;

		dir++;
		dir %= 4;
		for (int i = 0; i < move; ++i) {
			x += dx[dir];
			y += dy[dir];
			if (q.empty() || !isRange(x, y)) break;
			arr[x][y] = q.front();
			q.pop();
		}

		if (q.empty() || !isRange(x, y)) break;

		dir++;
		move++;
	}
}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(NULL);
	cout.tie(NULL);

	input();
	
	for (int i = 0; i < M; ++i) {
		int d, s;
		cin >> d >> s;

		deleteBall(d, s);		
		moveBall();	
		//cout << endl;
		//printing();
	}

	cout << (1*boomCnt[1] + 2 * boomCnt[2] + 3 * boomCnt[3]);
	return 0;
}