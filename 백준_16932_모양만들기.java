import java.awt.Point;
import java.io.*;
import java.util.*;
public class 백준_16932_모양만들기 {
	/**
	  *@since 2021. 6. 10.
	  *@author skyworking
	  *@see
	  *@time 오전 12:55:22
	  *@caution
	  */
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N,M;
	static int[][] arr;
	static int[] dx = {0,0,-1,1};
	static int[] dy = {-1,1,0,0};
	static List<Integer> area = new ArrayList<>();
	static Set<Point> zeroSet = new HashSet<>();
	static Set<Integer> visitSet = new HashSet<>();
	static Queue<Point> q = new LinkedList<>();
	
	public static void main(String[] args) throws Exception{
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		area.add(0);	
		arr = new int[N][M];

		Queue<Point> promiseQ = new LinkedList<Point>();
		for(int i=0;i<N;++i) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<M;++j) {
				arr[i][j] = st.nextToken().charAt(0)-'0';
				if(arr[i][j]==1) {
					arr[i][j]=-1;
					promiseQ.add(new Point(i,j));
				}
			}
		}
		
		while(!promiseQ.isEmpty()) {
			Point p = promiseQ.poll();
			if(arr[p.x][p.y]<0) floodFill(p.x,p.y);
		}

		
		int answer = 0;	
		
		for(Point p : zeroSet) {
			int res = 1;
			visitSet.clear();
			for(int i=0;i<4;++i) {
				int nx = p.x+dx[i];
				int ny = p.y+dy[i];
				
				if(!(nx>=0 &&ny>=0 && nx<N && ny<M)) continue;
				if(arr[nx][ny]>0 && !visitSet.contains(arr[nx][ny])) {
					visitSet.add(arr[nx][ny]);
					res+=area.get(arr[nx][ny]);
				}
			}
			answer = Math.max(answer,res);
			//answer = Math.max(answer,mergeArea(p.x,p.y));
		}
		
		System.out.println(answer);

	}

	private static void floodFill(int i, int j) {
		q.offer(new Point(i,j));
		
		int res = 1;
		int idx = area.size();
		arr[i][j] = idx;
		
		while(!q.isEmpty()) {
			Point p = q.poll();
			
			for(int d = 0;d<4;++d) {
				int nx = p.x+dx[d];
				int ny = p.y+dy[d];
				
				if(!isRange(nx, ny)) continue;
				
				if(arr[nx][ny]<0) {
					res++;
					arr[nx][ny] = idx;
					q.offer(new Point(nx,ny));
				}else if(arr[nx][ny]==0) {
					zeroSet.add(new Point(nx,ny));
				}
				
			}
		}
		
		area.add(res);
	}
	
	private static boolean isRange(int nx, int ny) {
		return nx>=0 &&ny>=0 && nx<N && ny<M;
	}
	
//	private static int mergeArea(int x, int y) {
//		int res = 1;
//		visitSet.clear();
//		for(int i=0;i<4;++i) {
//			int nx = x+dx[i];
//			int ny = y+dy[i];
//			
//			if(isRange(nx,ny) && arr[nx][ny]>0 && !visitSet.contains(arr[nx][ny])) {
//				visitSet.add(arr[nx][ny]);
//				res+=area.get(arr[nx][ny]);
//			}
//		}
//		
//		return res;
//	}

}
