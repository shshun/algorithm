#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

//12:04

int N;
int arr[102];
bool visit[102] = { false, };
vector<int> answer;


void dfs(int src, int cur) {

	if (visit[cur]) {
		if (cur == src) {
			answer.push_back(src);
		}
	}
	else {
		visit[cur] = true;
		dfs(src,arr[cur]);
	}
	
}
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> N;

	for (int i = 1; i <= N; i++) {
		cin >> arr[i];
	}
	/*

	for (int i = 0; i < N; i++) {
		cout << arr[i] << " ";
	}cout << endl;
	*/

	for (int i = 1; i <= N; i++) {
		dfs(i,i);
		memset(visit, false, sizeof(visit));
	}

	cout << answer.size() << '\n';
	sort(answer.begin(), answer.end());
	for (int i = 0; i < answer.size(); i++) {
		cout << answer[i] << '\n';
	}
	return 0;
}