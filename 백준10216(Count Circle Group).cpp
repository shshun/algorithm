#include <iostream>
#include <vector>
#include <string.h>
#include <queue>
#include <set>

using namespace std;

//11:05

typedef pair<int, int> p;
#define MAX_ 5001
int N, TC;

int dx[4] = { 0,0,-1,1 };
int dy[4] = { -1,1,0,0 };

int arr[MAX_][MAX_];
int dist[3001];

struct info {
	int x;
	int y;
	int r;
};
vector<info> v;

int findParent(int idx) {
	if (dist[idx] == idx) return idx;

	return dist[idx] = findParent(dist[idx]);
}

void unionGroup(int a, int b) {
	int aP = findParent(a);
	int bP = findParent(b);

	if (aP == bP) return;

	if (aP > bP) swap(aP, bP);

	dist[bP] = aP;
}


void isRange(int a,int b) {
	info in1 = v[a];
	info in2 = v[b];


	int disX = abs(in1.x - in2.x);
	int disY = abs(in1.y - in2.y);

	int r = in1.r+in2.r;
	if (disX * disX + disY * disY <= r * r) {
		unionGroup(a, b);
	}
	
}

int main() {

	ios_base::sync_with_stdio(false);
	cin.tie(0);
	cin >> TC;


	for (int tc = 0; tc < TC; tc++) {
		
		cin >> N;
		v.clear();

		memset(arr, 0, sizeof(arr));
		for (int i = 0; i < N; i++) {
			dist[i] = i;
		}

		for (int i = 0; i < N; i++) {
			int a, b, r;

			cin >> a >> b >> r;
			v.push_back({ a, b, r });
		}

		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				isRange(i, j);
			}
		}
		set<int> s;
		for (int i = 1; i <= N; i++) {
			s.insert(findParent(i));

			//cout << i << "=>" << findParent(i) << endl;
		}
		/*
		cout << endl;
		for (int i = 0; i < 11; i++) {
			for (int j = 0; j < 11; j++) {
				cout << arr[i][j] << " ";
			}cout << endl;
		}
		*/
		cout << s.size()<<'\n';
	}

	return 0;
}