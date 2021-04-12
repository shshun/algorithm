package swea_1249_보급로;
import java.io.*;
import java.util.*;

class info implements Comparable<info> { 
	int x;
	int y;
	int cnt;
	
	
	public info(int x, int y, int cnt) {
		super();
		this.x = x;
		this.y = y;
		this.cnt = cnt;
	}


	@Override
	public int compareTo(info o) {
		// TODO Auto-generated method stub
		return Integer.compare(cnt, o.cnt);
	}
}
// 5 04
public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int TC,N;
	static int[][] arr = new int[100][100];
	static boolean[][] visited = new boolean[100][100];

	static int[] dx = {0,1,0,-1};
	static int[] dy = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1;tc<=TC;++tc) {
			N = Integer.parseInt(br.readLine());
			visited = new boolean[N][N];
			arr = new int[N][N];
			for(int i=0;i<N;++i) {
				String input = br.readLine();
				for(int j=0;j<N;++j) {
					arr[i][j] = input.charAt(j)-'0';
				}
			}
			
			sb.append(String.format("#%d %d\n", tc,bfs()));			
		}
		System.out.println(sb.toString());
		
	}

	private static int bfs() {
		PriorityQueue<info> pq = new PriorityQueue<>();
		pq.add(new info(0,0,0));
		visited[0][0]=true;
		
		while(!pq.isEmpty()) {
			
			info item = pq.poll();
			
			if(item.x==N-1 && item.y==N-1) return item.cnt;	
			
			for(int i=0;i<4;++i) {
				int nx = item.x+dx[i];
				int ny = item.y+dy[i];
				
				if(!isRange(nx,ny) || visited[nx][ny]) continue;
				
				visited[nx][ny]=true;
				pq.add(new info(nx,ny,item.cnt+arr[nx][ny]));
			}
		}
		
		return -1;
	}
	private static boolean isRange(int x, int y) {
		// TODO Auto-generated method stub
		return x>=0 && y>=0 && x<N && y<N;
	}

}
