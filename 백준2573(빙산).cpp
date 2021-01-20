#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <string.h>
using namespace std;

//2:50


typedef pair<int, int> p;
int dx[4] = { 0,0,-1,1 };
int dy[4] = { 1,-1,0,0 };

int N, M;
int answer;
int idx=1;

int arr[301][301];
int area[301][301];
int temp[301][301];
bool visit[301][301] = { false, };
queue<p> q;


bool isRange(int x, int y) {
	if (x < 0 || y < 0 || x >= N || y >= M) return false;
	return true;
}
bool floodFill() {
	bool ice = false;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {

			if (arr[i][j] >0 &&!visit[i][j]){
				ice = true;
				queue<p> tempQ;
				tempQ.push({ i,j });
				visit[i][j] = true;
					
				while (!tempQ.empty()) {

					int x = tempQ.front().first;
					int y = tempQ.front().second;
					tempQ.pop();

					for (int k = 0; k < 4; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];

						if (isRange(nx, ny) && arr[nx][ny] > 0 && !visit[nx][ny]) {
							area[nx][ny] = idx;
							visit[nx][ny] = true;
							tempQ.push({ nx,ny });
						}
					}
				}
				idx++;
			}
			
		}
	}

	return ice;
}
void meltingIce() {
	int s = q.size();

	memcpy(temp, arr, sizeof(arr));

	while (s--) {
		int x = q.front().first;
		int y = q.front().second;
		q.pop();
		
		int cnt = 0;
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];

			if (isRange(nx, ny) && arr[nx][ny] == 0) cnt++;
		}

		if (arr[x][y] - cnt <= 0) temp[x][y] = 0;
		else {
			temp[x][y] = arr[x][y] - cnt;
			q.push({ x,y });
		}
	}

	memcpy(arr, temp, sizeof(arr));
	memset(temp, 0, sizeof(temp));
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	cin >> N >>M;
	
	
	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			cin >> arr[i][j];
			if (arr[i][j] > 0) q.push({ i,j });
		}
	}

	while (1) {
		idx = 1;
		memset(visit, false, sizeof(visit));
		if (!floodFill()) {
			cout << "0\n";
			return 0;
		}
		/*
		cout << endl;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				cout << arr[i][j] << " ";
			}cout << endl;
		}
		*/
		if (idx >= 3) {
			cout << answer << "\n";
			break;
		}

		meltingIce();
		answer++;
	}
	cout << endl;
	

	return 0;
}