#include <iostream>
#include <vector>

using namespace std;

int N, M,K;
int dis[51];

vector<vector<int>> party;

int findParent(int idx) {
	if (dis[idx] == idx) return idx;

	return dis[idx] = findParent(dis[idx]);
}

void unionGroup(int a, int b) {
	int aP = findParent(a);
	int bP = findParent(b);

	if (aP != bP) {
		if (aP > bP) swap(aP, bP);

		dis[bP] = aP;
	}

}

int main() {
	cin >> N >> M;
	party.resize(M);

	for (int i = 1; i <= N; i++) {
		dis[i] = i;
	}

	cin >> K;
	vector<int> known;
	for (int i = 0; i < K; i++) {
		int temp;
		cin >> temp;
		known.push_back(temp);
	}


	for (int i = 0; i < known.size(); i++) {
		int idx = known[i];
		dis[idx] = known[0];
	}


	for (int i = 0; i < M; i++) {
		int cnt;
		cin >> cnt;
		for (int j = 0; j < cnt; j++) {
			int n;
			cin >> n;
			party[i].push_back(n);
		}

		for (int k = 0; k < party[i].size(); k++) {
			unionGroup(dis[party[i][k]], party[i][0]);
		}
	}

	if (K == 0) {
		cout << M << '\n';
		return 0;
	}

	int answer = 0;
	int knownP = findParent(known[0]);
	for (int i = 0; i < M; i++) {
		int partyP = findParent(party[i][0]);
		if (partyP != knownP) answer++;
	}

	cout << answer << '\n';
	return 0;
}