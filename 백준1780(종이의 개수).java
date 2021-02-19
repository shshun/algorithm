import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb;

	public static int lower_bound(long[] data, long n) {
		int left = 0;
		int right = data.length;

		while (left < right) {
			int mid = (left + right) / 2;

			if (data[mid] >= n)
				right = mid;
			else
				left = mid + 1;
		}

		return right;
	}

	public static int upper_bound(long[] data, long n) {
		int left = 0;
		int right = data.length;

		while (left < right) {
			int mid = (left + right) / 2;

			if (data[mid] <= n)
				left = mid + 1;
			else
				right = mid;
		}

		return right;
	}

	static int N;
	static int[][] arr;
	static int[] answer = new int[3];

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];

		for (int i = 0; i < N; ++i) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken()) + 1;
			}
		}

		dfs(0, 0, N);

		for (int i = 0; i < 3; i++)
			System.out.println(answer[i]);
	}

	private static void dfs(int x, int y, int leng) {
		if (leng == 1) {
			answer[arr[x][y]]++;
			return;
		}

		boolean flag = true;
		int target = arr[x][y];
		
		for (int i = 0; i < leng; ++i) {
			for (int j = 0; j < leng; ++j) {
				if(arr[x+i][y+j]!=target) {
					flag =false;
					i=leng+1;
					break; 
				}
			}
		}
		
		if(flag) {
			answer[arr[x][y]]++;
			return;
		}
				
		int nextLeng = leng/3;
		int nextIdx = nextLeng;
		
	
		dfs(x,y,nextLeng);
		dfs(x,y+nextIdx,nextLeng);
		dfs(x,y+nextIdx*2,nextLeng);
		
		dfs(x+nextIdx,y,nextLeng);
		dfs(x+nextIdx,y+nextIdx,nextLeng);
		dfs(x+nextIdx,y+nextIdx*2,nextLeng);
		

		dfs(x+nextIdx*2,y,nextLeng);
		dfs(x+nextIdx*2,y+nextIdx,nextLeng);
		dfs(x+nextIdx*2,y+nextIdx*2,nextLeng);
	}
}
