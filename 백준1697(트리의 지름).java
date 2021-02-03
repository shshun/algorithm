
import java.awt.Point;
import java.io.*;
import java.util.*;


public class Main {
	/**
	  *@since 2021. 2. 3.
	  *@author skyworking
	  *@see https://www.acmicpc.net/problem/1967
	  *@time 10:53
	  *@caution
	  */
	static int N,M,visit,answer;
	static int INF = (int) 1e9;
	static ArrayList<Point>[] v; 
	static BufferedWriter bw;
	static BufferedReader br;
	
	public static int dikjstra(int start) {
		int[] dist=new int[N];		
		Arrays.fill(dist, INF);
		
		PriorityQueue<Point> pq = new PriorityQueue<>((p1,p2)->Integer.compare(p1.x,p2.x));	
		pq.add(new Point(0,start));
		dist[start]=0;
		
		while(!pq.isEmpty()) {
			int curCost = pq.peek().x;
			int curIdx = pq.peek().y;
			pq.poll();
			
			if(curCost > dist[curIdx]) continue;
			
			for(int i=0;i<v[curIdx].size();i++) {				
				int nextCost = curCost+v[curIdx].get(i).x;
				int nextIdx = v[curIdx].get(i).y;
				
				if(dist[nextIdx] > nextCost) {
					dist[nextIdx]=nextCost;
					pq.add(new Point(nextCost,nextIdx));
					
				}
			}
		}
		
		
		int maxIdx = 0;
		int maxVal=0;
		
		for(int i=0;i<N;i++) {
			if(maxVal<dist[i] && dist[i]!=INF) {
				maxVal = dist[i];
				maxIdx = i;
			}
		}
		
		if(start!=0) answer = maxVal;
		
		return maxIdx;
	}
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;// 
		
		N=Integer.parseInt(br.readLine());
		
		v=new ArrayList[N];
		
		for(int i=0;i<N;i++) v[i]=new ArrayList<>();
		
		for(int i=0;i<N-1;i++) {
			
			st= new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			a--;b--;
			
			if(v[a]==null) v[a]=new ArrayList<>();
			if(v[b]==null) v[b]=new ArrayList<>();
			
			v[a].add(new Point(c,b));
			v[b].add(new Point(c,a));
		}
	
		dikjstra(dikjstra(0));
	
		StringBuilder sb = new StringBuilder(""+answer);
		bw.write(sb.toString());	

		bw.flush();
	}
	
}



