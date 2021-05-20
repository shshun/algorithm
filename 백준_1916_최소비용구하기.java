import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge>{
	int idx;
	int cost;
	
	public Edge(int idx, int cost) {
		super();
		this.idx = idx;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {	
		return Integer.compare(cost,o.cost);
	}
}
public class 백준_1916_최소비용구하기 {
	/**
	  *@since 2021. 5. 20.
	  *@author skyworking
	  *@see
	  *@time 오후 10:25:29
	  *@caution
	*/
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,M;
	static int INF = (int)1e9;
	static List<Edge>[] list;
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		list = new List[N+1]; 
		for(int i=1;i<=N;++i) list[i] = new ArrayList<Edge>();
		
		for(int i=0;i<M;++i) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());	
			
			list[start].add(new Edge(end,val));
		}
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		int answer = dijkstra(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
		System.out.println(answer);
	}

	private static int dijkstra(int start, int end) {
		int[] dist = new int[N+1];	
		for(int i = 0;i<=N;++i) dist[i] = INF;
		dist[start]=0;
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();		
		pq.add(new Edge(start,0));
		
		while(!pq.isEmpty()) {
			Edge e = pq.poll();
			
			if(dist[e.idx]<e.cost) continue;
			
			int idx = e.idx;
			int cost = e.cost;
			
			for(Edge item : list[idx]) {
				int next = item.idx;
				int nextCost = item.cost+cost;
				
				if(dist[next]>nextCost) {
					dist[next]=nextCost;
					pq.add(new Edge(next,nextCost));
				}
			}
			
		}
		System.out.println(Arrays.toString(dist));
		return dist[end];
	}

}
