package 백준_9177_단어섞기;

import java.io.*;
import java.util.*;

class Info{
	
	int aIdx;
	int bIdx;
	
	public Info(int aIdx, int bIdx) {
		super();
		this.aIdx = aIdx;
		this.bIdx = bIdx;
	}
}
public class 백준_9177_단어섞기 {
	
	/**
	  *@since 2021. 5. 13.
	  *@author skyworking
	  *@see
	  *@time 오전 1:53:20
	  *@caution
	  */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static boolean[][] visited;
	static String A,B,C;
	static Map<String,Boolean> map = new HashMap<>();
	static boolean find;
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=N;++tc) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			A = st.nextToken();
			B = st.nextToken();
			C = st.nextToken();
			
			visited = new boolean[C.length()][A.length()+1];
			Queue<Info> q = new LinkedList<Info>();
			
			if(A.charAt(0)==C.charAt(0)) {
				visited[0][0]=true;
				q.add(new Info(1,0));
			}
			if(B.charAt(0)==C.charAt(0)) {
				q.add(new Info(0,1));
			}
			
			
			String answer = "no";
			while(!q.isEmpty()) {
				Info item = q.poll();
				int aIdx = item.aIdx;
				int bIdx = item.bIdx;
				
				if(aIdx+bIdx == C.length()) {
					answer = "yes";
					break;
				}
						
				if(aIdx<A.length()&&A.charAt(aIdx) == C.charAt(aIdx+bIdx)) {
					if(!visited[aIdx+bIdx][aIdx+1]) {
						visited[aIdx+bIdx][aIdx+1]=true;
						q.add(new Info(aIdx+1,bIdx));
					}
				}			
				if(bIdx<B.length() && B.charAt(bIdx) == C.charAt(aIdx+bIdx)) {
					q.add(new Info(aIdx,bIdx+1));
				}
				
			}
			
			sb.append(String.format("Data set %d: %s\n", tc,answer));
		}
		
		System.out.println(sb);
	}
	
}
