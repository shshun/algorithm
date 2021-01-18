#include <iostream>
#include <vector>
#include <algorithm>
#include <queue>
#include <limits.h>
int N, M, B;

using namespace std;
int arr[257];

int cnt=INT_MAX;
int height=-1;

int minH = 257;
int maxH = 0;
int cal(int h) {

	int block = B;
	int r = 0;

	if (h > maxH) return -1;
	for (int i = maxH; i >= minH; i--) {
		if (i == h || arr[i]==0) continue;

		else if (arr[i] > 0 && i > h) { //제거
			r += (i-h)*arr[i]* 2;
			block += (i-h)*arr[i];
		}

		else if (arr[i] > 0 && i < h) { //쌓기
			int needBlock = (h - i) * arr[i];
			if (block - needBlock < 0) return -1;

			block -= needBlock;
			r += needBlock;
		}
		
	}


	
	return r;
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> N >> M >> B;

	for (int i = 0; i < N; i++) {
		for (int j = 0; j < M; j++) {
			int temp;
			cin >> temp;
			minH = min(minH, temp);
			maxH = max(maxH, temp);
			arr[temp]++;
		}
	}

	for (int i = 0; i <= 257; i++) {
		int result = cal(i);
		if (result >= 0) {
			//cout <<"height :"<< i << " | result " << result << endl;
			if (cnt > result) {
				cnt = result;
				height = i;
			}
			else if(cnt==result) {
				height = i;
			}
		}
	}
	cout << cnt << " " << height << '\n';
	return 0;
}