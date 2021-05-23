import java.awt.Point;
import java.io.*;
import java.util.*;

public class 백준_3187_양치기꿍 {
	/**
	  *@since 2021. 5. 24.
	  *@author skyworking
	  *@see
	  *@time 오전 2:45:21
	  *@caution
	  */
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static final char SHEEP =  'k';
	static final char EM =  '.';
	static final char WOLF =  'v';
	static final char WALL =  '#';
	
	static int[] dx= {0,0,-1,1};
	static int[] dy= {-1,1,0,0};
	static int R,C;
	static char[][] arr;
	static boolean[][] visited;
	static int wolfCnt =0;
	static int sheepCnt =0;
	public static void main(String[] args) throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		arr = new char[R][C];
		visited = new boolean[R][C];
		
		for(int i=0;i<R;++i) {
			String input =br.readLine();
			arr[i] =input.toCharArray();
			for(int j=0;j<C;++j) {
				char c = input.charAt(j);
				if(c==WOLF) wolfCnt++;
				else if(c==SHEEP) sheepCnt++;
			}
		}
		
		
		
		for(int i=0;i<R;++i) {
			for(int j=0;j<C;++j) {
				if(!visited[i][j] && arr[i][j]!=WALL) {
					bfs(i,j);
				}
			}
		}

		System.out.println(sheepCnt+" "+wolfCnt);
	}
	private static void bfs(int x, int y) {
		int wc = 0;
		int sc = 0;
		
		Queue<Point> q = new LinkedList<Point>(); 
		visited[x][y]=true;
		q.add(new Point(x,y));
		if(arr[x][y]==WOLF) wc++;
		else if(arr[x][y]==SHEEP) sc++;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int d=0;d<4;++d) {
				int nx = p.x+dx[d];
				int ny = p.y+dy[d];
				
				if(!isRange(nx,ny) ||visited[nx][ny]) continue;
				if(arr[nx][ny]==WALL) continue;
				
				if(arr[nx][ny]==WOLF) wc++;
				else if(arr[nx][ny]==SHEEP) sc++;
				
				visited[nx][ny]=true;
				q.add(new Point(nx,ny));
			}
		}
		
		if(wc==0 || sc==0) return;
			
		if(wc>=sc) {
			sheepCnt-=sc;
		}else{
			wolfCnt-=wc;
		}
		
	}
	private static boolean isRange(int nx, int ny) {
		return nx>=0 && ny>=0 && nx<R && ny<C;
	}

}
