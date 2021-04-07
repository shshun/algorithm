package boj_2473;

import java.util.*;
import java.io.*;
public class Main {
	
	//8 : 30
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static int[] arr;
	public static void main(String[] args) throws Exception{
		N= Integer.parseInt(br.readLine());
		arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i=0;i<N;++i) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		
		Arrays.sort(arr);
		
		int[] answer =  new int[3];
		
		long min = Long.MAX_VALUE;
		for(int i=0;i<N;++i) {
			int lo = i+1;
			int hi = N-1;
			
			while(lo<hi) {
				long sum = 1L*arr[i]+arr[lo]+arr[hi];
				if(Math.abs(min)>Math.abs(sum)) {
					answer[0]=arr[i];
					answer[1]=arr[lo];
					answer[2]=arr[hi];
					min = sum;
				}
				
				if(sum<0) lo++;
				else if(sum>0) hi--;
				else {
					i=N;
					break;
				}
			}
			//System.out.print(answer[0]+" | "+answer[1]+" | "+answer[2]);
		//	System.out.println(arr[i]+" "+arr[lo]+" "+arr[hi]);
		}
		
		System.out.print(answer[0]+" "+answer[1]+" "+answer[2]);
		
		
	}
	
	public static int lower_bound(long val,int s) {
		int lo = s;
		int hi = N-1;
		//System.out.println(lo+" "+hi);
		while(lo<hi) {
			int mid = (lo+hi)/2;
			
			if(val<=arr[mid]) {
				hi=mid;
			}
			else {
				lo= mid+1;
			}
		}
		
		return arr[lo];
	}

}
