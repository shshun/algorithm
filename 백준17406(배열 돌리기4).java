package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



class info{
	int first;
	int second;

	public info(int first, int second) {
		super();
		this.first = first;
		this.second = second;
	}	
}


public class Main {
	
	public static int N,M,answer;
	public static boolean infi;
	public static char[][] arr=new char[51][51];
	public static boolean[][] visit=new boolean[51][51];
	public static int[][] dp=new int[51][51];

		
	public static int[] dx = {0,1,0,-1};
	public static int[] dy= {1,0,-1,0};
	
	public static boolean isRange(int x,int y) {
		if(x<0||y<0||x>=N||y>=M) return false;
		return true;
	}
	/*
	public static void getInfinit() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<M;j++) {
				for(int k=0;k<2;k++) {
					if(arr[i][j]!='H') {
						int nx = i+dx[k]*(arr[i][j]-'0');
						int ny = j+dy[k]*(arr[i][j]-'0');
						
						if(isRange(nx,ny) && arr[i][j]==arr[nx][ny]) {
							infi[i][j]=true;
							infi[nx][ny]=true;
						}
					}
				}
			}
		}
	}
	*/
	public static void dfs(int x,int y,int cnt) {

		answer = Math.max(cnt,answer);
		
		 dp[x][y]=cnt;
		
		for(int k=0;k<4;k++) {
			int nx = x+dx[k]*(arr[x][y]-'0');
			int ny = y+dy[k]*(arr[x][y]-'0');
				
			if(isRange(nx,ny) && arr[nx][ny]!='H') {
				if(visit[nx][ny]) {
					infi=true;
					return;
				}
				
				if(dp[nx][ny]>cnt) continue;
				
				visit[nx][ny] = true;
				dfs(nx,ny,cnt+1);
				visit[nx][ny] =false;
			}
		}
	}
	
	/*
	public static int bfs() {
		Queue<info> q = new LinkedList<>();
		
		q.add(new info(0,0));
		
		int cnt = 0;
		
		while(!q.isEmpty()) {
			int s = q.size();
			for(int i=0;i<s;i++) {
				info temp = q.poll();
				int x= temp.first;
				int y= temp.second;
				
			//	System.out.println("("+x+" , "+y+") =>"+cnt);
				if(arr[x][y]=='H') continue;
				
				for(int k=0;k<4;k++) {
					int nx = x+dx[k]*(arr[x][y]-'0');
					int ny = y+dy[k]*(arr[x][y]-'0');
					
					
					
					if(isRange(nx,ny) && arr[nx][ny]!='H'&& (visit[nx][ny]<cnt || visit[nx][ny]==0 )) {
						if(infi[nx][ny]) return -1;
						q.add(new info(nx,ny));
						visit[nx][ny] = cnt;
					}
				}
			}
			cnt++;
		}
		
		return cnt;
	}
	*/
	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = bf.readLine().split(" ");
		N=Integer.parseInt(temp[0]);
		M=Integer.parseInt(temp[1]);
			
		
		for(int i=0;i<N;i++) {
			String str = bf.readLine();
			for(int j=0;j<M;j++) {
				arr[i][j]=str.charAt(j);
			}
		}
		
	//	getInfinit();
		//System.out.println(bfs());
		visit[0][0]=true;
		dfs(0,0,1);
		if(infi) System.out.println(-1);
		else System.out.println(answer);
	}

}

