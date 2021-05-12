import java.io.*;
import java.util.*;

public class 백준_9461_파도반수열 {
	/**
	  *@since 2021. 5. 13.
	  *@author skyworking
	  *@see
	  *@time 오전 2:56:22
	  *@caution
	  */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static long[] dp;
	static int TC;
	public static void main(String[] args) throws Exception {
		TC = Integer.parseInt(br.readLine());
		dp = new long[101];
		
		dp[1]=1;
		dp[2]=1;
		dp[3]=1;
		dp[4]=2;
		dp[5]=2;
		
		for(int i=6;i<101;++i) {
			dp[i]=dp[i-1]+dp[i-5];
		}
		
		
		for(int tc=0;tc<TC;++tc) {
			int N= Integer.parseInt(br.readLine());
			sb.append(dp[N]).append('\n');
		}
		System.out.println(sb);

	}

}
