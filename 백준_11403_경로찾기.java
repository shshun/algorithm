import java.util.*;
import java.io.*;

public class 백준_11403_경로찾기 {
	/**
	  *@since 2021. 5. 9.
	  *@author skyworking
	  *@see
	  *@time 오전 12:49:40
	  *@caution
	  */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[][] arr;
	static int INF = (int)1e9;
	public static void main(String[] args) throws Exception {
		input();
		solution();
		
		for(int i=0;i<N;++i) {
			for(int j=0;j<N;++j) {				
				if(arr[i][j]==INF) sb.append(0);
				else sb.append(1);
				sb.append(" ");
			}sb.append('\n');
		}
		
		System.out.println(sb);

	}
	private static void solution() {
		for(int k=0;k<N;++k) {
			for(int i=0;i<N;++i) {
				if(i==k) continue;
				for(int j=0;j<N;++j) {				
					if(k==j) continue;
					if(arr[i][k]==INF || arr[k][j]==INF) continue;
					if(arr[i][j]>arr[i][k]+arr[k][j]) {
						arr[i][j] = arr[i][k]+arr[k][j];
					}
				}
			}
		}	
	}
	private static void input() throws Exception {
		N = Integer.parseInt(br.readLine());
		arr= new int[N][N];
		
		for(int i=0;i<N;++i) {
			StringTokenizer st= new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;++j) {				
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j]==0) arr[i][j]=INF;
			}
		}
		
	}

}
