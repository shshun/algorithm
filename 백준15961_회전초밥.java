package boj_2531;

import java.util.*;
import java.io.*;

public class Main {

	//10 50
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int N,d,k,c;
	static int[] cnt;
	
	public static void main(String[] args) throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		cnt = new int[d+1];
		for(int i = 0;i<N;++i) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		
		int answer = 0;
		
		for(int i=0;i<k;++i) {
			if(cnt[arr[i]]++==0) answer++;
		}
		
		int res = answer;
		
		if(cnt[c]==0) answer++;
		
		for(int i=0;i<N;++i) {
			if(--cnt[arr[i]]==0) res--;			
			if(cnt[arr[(i+k)%N]]++==0) res++;			
			answer = Math.max(answer, cnt[c]==0?res+1:res);
		}
		
		System.out.println(answer);

	}

}
