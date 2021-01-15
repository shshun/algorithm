#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <string.h>

using namespace std;
//9:20

typedef pair<int, int> p;

int N, M;
int dx[4] = { 0,0,1,-1 };
int dy[4] = { 1,-1,0,0 };

int temp[102][102];
int arr[102][102];
p A[2], B[2];


bool isRange(int x, int y) {
	if (x >= 0 && y >= 0 && x < 102 && y < 102) {
		return true;
	}

	return false;
}
int bfs(p *input) {

	queue<p> q;

	int sX = input[0].first;
	int sY = input[0].second;
	int destX = input[1].first;
	int destY = input[1].second;

	q.push({ sX,sY });

	int dist = 1;
	while (!q.empty()) {
		int s = q.size();
		for (int i = 0; i < s; i++) {
			int x = q.front().first;
			int y = q.front().second;
			q.pop();

			if (x == destX && y == destY) return dist-1;

			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];

				if (arr[nx][ny]==0 && isRange(nx, ny)) {
					arr[nx][ny] = dist;
					q.push({ nx,ny });
				}
			}
		}
		dist++;
	}

	return -1;

}

void findPath(p *input) {
	
	int sX = input[0].first;
	int sY = input[0].second;

	int destX = input[1].first;
	int destY = input[1].second;


	int x = destX;
	int y = destY;
	int val = arr[x][y];

	

	while (val != 1) {
		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];

			if (isRange(nx, ny) && arr[nx][ny] == val - 1) {
				temp[nx][ny] = 1;
				val--;
				x = nx;
				y = ny;
				break;
			}
		}
	}
    temp[sX][sY] = 1;
	temp[destX][destY] = 1;
	memcpy(arr, temp, sizeof(temp));
	memset(temp, 0, sizeof(temp));

}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> N >> M;

	for (int i = 0; i < 2; i++) {
		int x, y;
		cin >> y >> x;
		A[i] = { x,y };
	}

	for (int i = 0; i < 2; i++) {
		int x, y;
		cin >> y >> x;
		B[i] = { x,y };
	}


	int answer = 987654321;
	
	int disA=bfs(A);
	findPath(A);
	int disB = bfs(B);
	
	if (disB > 0) answer = disA + disB;

	memset(arr, 0, sizeof(arr));
	
	disB = bfs(B);
	findPath(B);
	disA = bfs(A);
	
	if (disA > 0) answer = min(answer, disA + disB);

	if (answer == 987654321) {
		cout << "IMPOSSIBLE\n";
	}
	else {
		cout << answer << endl;
	}
	return 0;
}