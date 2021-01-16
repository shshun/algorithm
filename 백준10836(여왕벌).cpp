#include <iostream>
#include <vector>
#include <algorithm>
#include <string.h>

using namespace std;

//10:22



int arr[701][701];
int M,N;
vector<int> v;


void printing() {

	int x, y;
	int idx = 0;

	for (x = M - 1; x >= 0; x--) {
		arr[x][0] += v[idx++];
	}
	for (y = 1; y<M; y++) {
		cout << idx << endl;
		arr[0][y] += v[idx++];
		for (int i = 1; i < M; i++) {
			arr[i][y] = arr[0][y];
		}

	}
	for (int i = 0; i < M; i++) {
		for (int j = 0; j < M; j++) {
			cout << arr[i][j]+1 << " ";
		}cout << "\n";
	}

}


int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> M >> N;


	v.assign(2 * M + 1,0);

	for (int i = 0; i < N; i++) {
		
			int one, two, zero;
			cin >> zero >> one >> two;
			
			for(int j=zero;j<zero+one;j++)
				v[j]+=1;

			for (int j = one+zero ; j < 2*M-1; j++)
				v[j] += 2;
		
	}

	
	printing();
	return 0;
}