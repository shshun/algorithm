import java.util.*;
import java.awt.Point;
import java.io.*;

public class 백준3584_가장가까운공통조상 {

	/**
	  *@since 2021. 5. 9.
	  *@author skyworking
	  *@see
	  *@time 오전 12:06:40
	  *@caution
	  */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int TC,N;
	static List<Integer>[] list;
	public static void main(String[] args) throws Exception{
		int TC = Integer.parseInt(br.readLine());
		
		for(int tc = 0;tc<TC;++tc) {
			N = Integer.parseInt(br.readLine());
			boolean[] visited = new boolean[N+1];
			list = new ArrayList[N+1];
			
			for(int i=1;i<=N;++i) {
				list[i] = new ArrayList<>();
			}
			for(int i=0;i<N-1;++i) {
				
				StringTokenizer st = new StringTokenizer(br.readLine());
				int A  = Integer.parseInt(st.nextToken());
				int B  = Integer.parseInt(st.nextToken());
				
				list[B].add(A); //A의 자식은 B다
			}
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			Queue<Integer> q = new LinkedList<Integer>();
			q.add(Integer.parseInt(st.nextToken()));
			q.add(Integer.parseInt(st.nextToken()));
			
			int answer = 0;
			while(!q.isEmpty()) {
				int a = q.poll();
				int b = q.poll();
				
				if(visited[a]) {
					answer = a;
					break;
				}
				
				if(visited[b]) {
					answer = b;
					break;
				}
				
				if(a==b) {
					answer = b;
					break;
				}
				
				visited[a]=true;
				visited[b]=true;
				
				if(list[a].size()>0) a=list[a].get(0);									
				else visited[a]=false;
				
				if(list[b].size()>0) b=list[b].get(0);
				else visited[b]=false;
				
				q.add(a);
				q.add(b);
			}
			
			sb.append(answer).append('\n');
		}
		System.out.println(sb);

	}

}
