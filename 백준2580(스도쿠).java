package algo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class pair{
	int x;
	int y;
	int part;
	public pair(int x, int y,int p) {
		super();
		this.x = x;
		this.y = y;
		this.part=p;
	}
	
}
public class BOJ2580 {
	/**
	  *@since 2021. 2. 1.
	  *@author skyworking
	  *@see https://www.acmicpc.net/problem/2580
	  *@time 3:07
	  *@caution
	  */
	
	static final int ROW=0;
	static final int COL=1;
	static int N = 9;
	static int[][] arr=new int[N][N];
	static boolean[][] visit = new boolean[9][10];
	static int[][] visitXY = new int[2][10];
	static boolean find= false;
	static ArrayList<pair> v = new ArrayList<>();
	
	
	static boolean isRight(int x,int y,int num) {
			
		if( (visitXY[ROW][x] & (1<<num)) >0 ) return false;
		if( (visitXY[COL][y] & (1<<num)) >0 ) return false;
		
		return true;
	}
	
	static void dfs(int idx) {
		if(find) return;
		
		if(!find && idx == v.size()) {
			find = true;
			StringBuilder sb = new StringBuilder();
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					sb.append(arr[i][j]).append(" ");
				}sb.append("\n");
			}
			
			System.out.println(sb.toString());
			return;
		}

		for(int i=1;i<=N;i++) {
			int x = v.get(idx).x;
			int y = v.get(idx).y;
			int p = v.get(idx).part;			
			
			if(!visit[p][i] && isRight(x,y,i)) {
				arr[x][y]=i;
				visit[p][i]=true;
				visitXY[ROW][x]|=1<<i;
				visitXY[COL][y]|=1<<i;
				
				dfs(idx+1);
				
				arr[x][y]=0;
				visit[p][i]=false;
				visitXY[ROW][x]&=~(1<<i);
				visitXY[COL][y]&=~(1<<i);
			}
		}
		
		
		
	}
	
	public static int getPart(int x,int y) {
	
		if(x<3&&y<3) return 0;
		else if(x<3&&2<=y&&y<=5) return 1;
		else if(x<3&&6<=y&&y<=8) return 2;
		
		else if(3<=x &&x<=5&&y<3&&y<3) return 3;
		else if(3<=x &&x<=5&&3<=y&&y<=5) return 4;
		else if(3<=x &&x<=5&&6<=y&&y<=8) return 5;
		
		else if(6<=x &&x<=8&&y<3&&y<3) return 6;
		else if(6<=x &&x<=8&&3<=y&&y<=5) return 7;
		else return 8;
		
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int part=0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<N;j++) {
				part=getPart(i,j);
				int num = Integer.parseInt(st.nextToken());
				arr[i][j]=num;
				
				if(num==0) v.add(new pair(i,j,part));
				else {
					visitXY[ROW][i]|=1<<num;
					visitXY[COL][j]|=1<<num;
					visit[part][num]=true;		
				}
			}
		}
	/*
		for(int k=0;k<N;k++) {
			for(int i=1;i<=N;i++) {
				if( (visitXY[ROW][k] & (1<<i)) >0 ) System.out.print(1+" ");
				else   System.out.print(0+" ");
			}System.out.println();
		}
		*/
		
		
		dfs(0);
		//System.out.println("END");
	}

}
