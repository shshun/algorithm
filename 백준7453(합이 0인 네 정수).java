import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import javax.naming.ldap.StartTlsResponse;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	// static StringBuilder sb;

	public static int lower_bound(long[] data, long n) {
		int left = 0;
		int right = data.length;

		while (left < right) {
			int mid = (left + right) / 2;

			if (data[mid]>= n)
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

	static int[][] arr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		

		int N = Integer.parseInt(br.readLine());
		
		arr = new int[4][N];
		
		for(int i =0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<4;j++) {
				arr[j][i]=Integer.parseInt(st.nextToken());
			}
		}
			
		int answer = 0;
		long[][] sumArr = new long[2][N*N];
		
		int idx = 0;
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				sumArr[0][idx]=arr[0][i]+arr[1][j];
				sumArr[1][idx]=arr[2][i]+arr[3][j];
				idx++;
			}
		}
		
		Arrays.sort(sumArr[0]);
		Arrays.sort(sumArr[1]);
		
		for(long num : sumArr[0] ) {
			long diff = -num;
			
			int start = lower_bound(sumArr[1],diff);
			int end = upper_bound(sumArr[1],diff);
			
			answer+=(end-start);
		}
		
		System.out.println(answer);
		
	}

}
