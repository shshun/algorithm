package boj_2411;

import java.io.*;
import java.util.*;

class coor implements Comparable<coor>{
	int x;
	int y;
	public coor(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	@Override
	public int compareTo(coor o) {
		if(x==o.x) return Integer.compare(y, o.y);
		
		return Integer.compare(x, o.x);
	}
}
public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N,M,A,B;
	static List<coor> list = new ArrayList<coor>();
	static int[] dx = {-1,0};
	static int[] dy = {0,1};
	static boolean[][] hurdle;
	static int[][] dp;
	public static void main(String[] args) throws Exception {
		StringTokenizer st= new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int A = Integer.parseInt(st.nextToken());
		int B = Integer.parseInt(st.nextToken());
		
		hurdle = new boolean[N+1][M+1];
		dp = new int[N+1][M+1];
		
		// 5 8 
		// 4 0 -> 0 0 / 1 0 -> N- 
		for(int i=0;i<A;++i) {
			st= new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			// 1- > 0 | 2-> 1 | 3 -> 2 | 4 -> 3 | 5-> 4 
		//	System.out.println(x+" "+y+" convert :  "+((x+N)%N)+" "+(y-1));
			list.add(new coor(x,y));
		}
		
		for(int i=0;i<B;++i) {
			st= new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			hurdle[x][y]=true;
		}
		
		list.add(new coor(N,M));
		Collections.sort(list);
		
		int curX = 1;
		int curY = 1;
		dp[1][1]=1;
		
		for(int i=0;i<list.size();++i) {
			coor c = list.get(i);
			
			
			for(int x = curX;x<=c.x;++x) {
				for(int y = curY;y<=c.y;++y) {
					if(x==curX && y==curY || hurdle[x][y]) continue;
					if(!hurdle[x-1][y]) dp[x][y]+=dp[x-1][y];
					if(!hurdle[x][y-1]) dp[x][y]+=dp[x][y-1];
				}
			}
			
			curX = c.x;
			curY = c.y;
			System.out.println();
			for(int a=0;a<N;++a) {
				for(int j=0;j<M;++j) {
					System.out.print(dp[a][j]+" ");
				}System.out.println();
			}
		}
		
		
		
		System.out.println(dp[N][M]);
		
	}
}
