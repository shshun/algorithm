import java.io.*;
import java.util.*;

class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int[][] dp;
	static int[][] arr;
	static int N;
	static int answer = Integer.MAX_VALUE;
    static int INF = (int)1e9;
    
	public static void main(String[] args) throws IOException {

		N = Integer.parseInt(br.readLine());
		arr = new int[N][N];
		dp = new int[1 << N][N];

		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		System.out.println(tsp(0, 1));
	}

	private static int tsp(int idx, int visited) {

		if (visited == (1 << N) - 1) {
			return arr[idx][0];
		}

		if (dp[visited][idx] != 0)
			return dp[visited][idx];

		dp[visited][idx] = INF;

		for (int i = 0; i < N; ++i) {
			if ( (visited & (1 << i)) != 0 || arr[idx][i] == 0) continue;
			int res = tsp(i, (visited | (1 << i)));
			
			if(res!=0) {
				dp[visited][idx] = Math.min(dp[visited][idx], res+arr[idx][i]);
			}
			
			
		}

		return dp[visited][idx];
	}
}