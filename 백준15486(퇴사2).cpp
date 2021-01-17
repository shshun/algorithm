#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;


//8:37

typedef pair<int, int> p;
int N;
int dp[150010];
vector<p> v;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);

	cin >> N;
	v.push_back({ 0,0 });
	for (int i = 1; i <=N; i++) {
		int a, b;
		cin >> a >> b;

		v.push_back({ a,b });
	}
	//v.push_back({ 0,0 });
	int answer = 0;
	for (int i = 1; i <= N+1; i++) {
		answer = max(answer, dp[i]);
		if (i+ v[i].first > N + 1) continue;
		dp[i + v[i].first] = max(answer+v[i].second,dp[i+v[i].first]);
	}

	cout << answer <<'\n';
	return 0;


}