#include <iostream>
#include <vector>
#include <algorithm>;
using namespace std;

#define MAX_ 10001
int TC;

int prime[MAX_] = { 1, };
vector<int> v;
int main() {
	ios_base::sync_with_stdio(false);
	cin.tie(0);
	cin >> TC;
	

	for (int i = 2; i < MAX_; i++) {
		prime[i] = i;
	}

	for (int i = 2; i < MAX_; i++) {
		if (prime[i] == 0) continue;

		for (int j = 2*i; j < MAX_; j+=i) {
			prime[j]=0;
		}
	}
	
	
	for (int i = 2; i <= MAX_; i++) {
		
		if (prime[i] != 0) {
			v.push_back(prime[i]);
		}
	}
	
	for (int tc = 0; tc < TC; tc++) {
		int n;
		cin >> n;
	
		int gap = 987654312;
		pair<int, int> answer;
		
		if (prime[n / 2] != 0) {
			cout << n / 2 << " " << n / 2 << '\n';
			continue;
		}
		else {
			for (int i = 2; i < n; i++) {
				if (prime[i] != 0 && prime[n - i] != 0) {
					int temp = abs(i - (n - i));
					if (gap > temp) {
						gap = temp;
						answer = { i,n - i };
					}
				}
			}
		}

		cout << answer.first << " "<<answer.second << '\n';
	}
		
	return 0;
}