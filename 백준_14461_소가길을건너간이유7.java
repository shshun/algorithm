import java.awt.Point;
import java.io.*;
import java.util.*;
class Edge implements Comparable<Edge>{
	int idx;
	int cost;
	int cnt;
	
	public Edge(int idx, int cost,int cnt) {
		super();
		this.idx = idx;
		this.cost = cost;
		this.cnt = cnt;
	}

	@Override
	public int compareTo(Edge o) {
		if(cost==o.cost) Integer.compare(cnt, o.cnt);
		return Integer.compare(cost, o.cost);
	}
}

public class 백준_14461_소가길을건너간이유7 {

	/**
	  *@since 2021. 4. 29.
	  *@author skyworking
	  *@see
	  *@time 오전 1:49:40
	  *@caution
    */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,T;
	static int[][] arr;
	static int[][] dist;
	
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	
	static Map<Integer,Integer> map = new HashMap<Integer, Integer>();
	static int INF = (int)1e9;
	public static void main(String[] args) throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		arr = new int[N][N];
		dist = new int[3][N*N];
		
		for(int i=0;i<N;++i) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;++j) {
				arr[i][j]=Integer.parseInt(st.nextToken());
			//	map.put((i*N)+j,arr[i][j]);
			}
		}
		
		System.out.println(dijkstra());
		
		
		

	}
	private static int dijkstra() {
		for(int i=0;i<3;++i) Arrays.fill(dist[i], INF);
		dist[0][0]=0;
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(0,0,0));
		
		while(!pq.isEmpty()) {
			
			Edge e = pq.poll();
			int cur = e.idx;
			int cost = e.cost;
			int cnt = e.cnt;
			if(dist[cnt][cur]>cost) continue;
			
			Point p = convertXY(e.idx);
			
//			System.out.println(p.x+" "+p.y+ " cnt : "+e.cnt + " |  val : "+dist[e.idx]);
			for(int d=0;d<4;++d) {
				int nx = p.x+dx[d];
				int ny = p.y+dy[d];
				
				if(!isIn(nx,ny)) continue;			
				int nextIdx = convertIdx(nx,ny);
				
				if(cnt==2) {
					//int nextCost = map.get(nextIdx)+2+cost;
					int nextCost = arr[nx][ny]+T+cost;
					if(dist[0][nextIdx]>nextCost) {
	//					System.out.println("cnt 3 ! : "+nx+" "+ny+ " => "+nextCost);
						dist[0][nextIdx]=nextCost;
						pq.add(new Edge(nextIdx, nextCost, 0));
					}
				}
				else {
					int nextCost = cost+T;
					if(dist[cnt+1][nextIdx]>nextCost) {
		//				System.out.println(nx+" "+ny+ " => "+nextCost);
						dist[cnt+1][nextIdx]=nextCost;
						pq.add(new Edge(nextIdx, nextCost, cnt+1));
					}
				}
			}
			//System.out.println();
		}
		
	

		return Math.min(dist[0][(N*N)-1],Math.min(dist[1][(N*N)-1],dist[2][(N*N)-1]));
	}
	private static boolean isIn(int x, int y) {
		return x>=0 && x<N && y>=0 &&y<N;
	}
	private static int convertIdx(int x, int y) {
		return (x*N)+y;
	}
	private static Point convertXY(int idx) {
		return new Point(idx/N,idx%N);
	}

}
