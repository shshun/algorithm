import java.io.*;
import java.util.*;

/*
 * public static int lower_bound(long[] data, long n) { int left = 0; int right
 * = data.length;
 * 
 * while (left < right) { int mid = (left + right) / 2;
 * 
 * if (data[mid] >= n) right = mid; else left = mid + 1; }
 * 
 * return right; }
 * 
 * public static int upper_bound(long[] data, long n) { int left = 0; int right
 * = data.length;
 * 
 * while (left < right) { int mid = (left + right) / 2;
 * 
 * if (data[mid] <= n) left = mid + 1; else right = mid; }
 * 
 * return right; }
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static String str;
	static int N,K;
	static PriorityQueue<Long> pq = new PriorityQueue<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine()," ");
		int[] arr = new int[N];
		for(int i=0;i<N;++i) {
			int num = Integer.parseInt(st.nextToken());
			arr[i]=num;
			pq.add(1L*num);
		}
		
		int cnt = 0;
		
		long num=0;
		while(!pq.isEmpty()) {
			num = pq.poll();
			cnt++;
			if(cnt==K) break;
			
			for(int i=0;i<N;++i) {
				pq.add(num*arr[i]);	
				if(num%arr[i]==0) break;
			}
			
			
		}
		
		System.out.println(num);
		
	}

}
