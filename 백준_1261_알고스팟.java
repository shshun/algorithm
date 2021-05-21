import java.io.*;
import java.util.*;

class Node implements Comparable<Node>{
	int x;
	int y;
	int cnt;
	public Node(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}
	@Override
	public int compareTo(Node o) {
		// TODO Auto-generated method stub
		return Integer.compare(cnt, o.cnt);
	}
	@Override
	public String toString() {
		return "Node [x=" + x + ", y=" + y + ", cnt=" + cnt + "]";
	}
	
	
}
public class 백준_1261_알고스팟 {
	/**
	  *@since 2021. 5. 22.
	  *@author skyworking
	  *@see
	  *@time 오전 1:54:06
	  *@caution
	  */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,M;
	static int INF = (int)1e9;
	static boolean[][] arr;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		arr = new boolean[N][M];
		
		for(int i=0;i<N;++i) {
			String input = br.readLine();
			for(int j=0;j<M;++j) {
				char c = input.charAt(j);
				if(c=='1') arr[i][j]=true;
			}
		}
		
		System.out.println(bfs());
		
	}

	private static int bfs() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		int[][] visited = new int[N][M];
		for(int i=0;i<N;++i) Arrays.fill(visited[i], INF);
		visited[0][0]=0;
		pq.add(new Node(0,0,0));
		
		while(!pq.isEmpty()) {
			Node item = pq.poll();
			
			if(item.x==N-1 && item.y==M-1) return item.cnt;
			//System.out.println(item.toString());
			for(int i = 0;i<4;++i) {
				int nx = item.x+dx[i];
				int ny = item.y+dy[i];
				
				if(!isRange(nx,ny)) continue;
				
				if(visited[nx][ny]>item.cnt) {
					visited[nx][ny] = item.cnt;
					pq.add(new Node(nx,ny,arr[nx][ny]?item.cnt+1:item.cnt));
				}
//				if(arr[nx][ny] && visited[nx][ny]>item.cnt+1) {
//					visited[nx][ny] = item.cnt+1;
//					pq.add(new Node(nx,ny,item.cnt+1));
//				}
//				else if(!arr[nx][ny] && visited[nx][ny]>item.cnt) {
//					visited[nx][ny] = item.cnt;
//					pq.add(new Node(nx,ny,item.cnt));
//				}
			}
			
		}
		
		
		return -1;
	}

	private static boolean isRange(int x, int y) {
		return x>=0 && y>=0 && x<N && y<M;
	}

}
