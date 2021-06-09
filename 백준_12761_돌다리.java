import java.io.*;
import java.util.*;

public class 백준_12761_돌다리 {
	/**
	  *@since 2021. 6. 6.
	  *@author skyworking
	  *@see
	  *@time 오후 10:11:28
	  *@caution
	  */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int A,B,N,M;
	public static void main(String[] args) throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());


		int answer = bfs();

		
		System.out.println(answer);
		
	}
	private static int bfs() {
		boolean[] visited = new boolean[100001];
		Queue<Integer> q = new LinkedList<Integer>();
		visited[N]= true;
		q.add(N);
		
		int res = 0;		
		List<Integer> jump = new ArrayList<Integer>();
		jump.add(-1);
		jump.add(1);
		jump.add(A);
		jump.add(B);
		jump.add(-A);
		jump.add(-B);
		
		while(!q.isEmpty()) {
			int s= q.size();
			
			for(int i=0;i<s;++i) {				
				int pos = q.poll();
				
				if(pos==M) {
					return res;
				}
				
				for(int move : jump) {					
					if(isRange(pos+move) && !visited[pos+move]) {
						visited[pos+move]=true;
						q.add(pos+move);
					}
				}
				
				if(isRange(pos*A) && !visited[pos*A]) {
					visited[pos*A]=true;
					q.add(pos*A);
				}
				if(isRange(pos*B) && !visited[pos*B]) {
					visited[pos*B]=true;
					q.add(pos*B);
				}
			}
			res++;
		}
		
		return -1;
	}

	private static boolean isRange(int val) {
		return val>=0 && val<=100000;
	}
}
