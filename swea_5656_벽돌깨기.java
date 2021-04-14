import java.io.*;
import java.util.*;

//11 20
public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int TC,N,W,H,answer,total;
	static int[][] map;
	static int[][] cpyMap;
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		/*
		 * 기초 로직
		 * 1. 구슬을 떨어뜨릴 위치를 DFS를 사용하여 중복 순열로 구한다.
		 * 
		 * 2. 구한 중복 순열을 토대로 구슬을 떨구는 로직 진행(simulation 함수에서 동작 수행됨)
		 * 	2-1. 원본 배열에서 값을 복사해온 배열을 설정 후, 이 배열에서 simulation을 동작시킴
		 * 	2-2. dropBall함수에서 구슬을 하나씩 떨어뜨린다. 
		 *  2-3. 이때 최초로 0이 아닌 터트릴 블럭이 나올 경우 explosion함수(DFS 로직)를 통해 연쇄 폭발을 일으킨다.
		 *  2-4. 하나의 구슬이 떨어지고 폭발시킬 수 있는 모든 블럭을 파괴 한 후 pushMap함수(Queue를 이용함)를 통해 터진 공간을 매꿔준다. 
		 *  2-5. 폭발을 일으킬때마다 파괴된 블럭의 카운트를 리턴한다.
		 *  
		 * 3.(전체 블럭 개수 - 파괴된 블럭 수) 연산하면 현재 남아있는 블럭갯수를  알 수 있다. (최소 정답을 계속 갱신함)   
		 */
		TC = Integer.parseInt(br.readLine());
		
		for(int tc=1;tc<=TC;++tc) {
			input();
			solve(tc);
		}
		
		System.out.println(sb.toString());

	}
	
	private static void solve(int tc) throws Exception{
		answer= Integer.MAX_VALUE;
			
		for(int i=0;i<W;++i) {
			int[] select = new int[N];
			select[0]=i;
			//중복순열 계산
			dfs(1,select);		
		}
		
		sb.append(String.format("#%d %d\n",tc,answer));
	}

	private static void dfs(int cnt,int[] select) {
		if(cnt == N) {		
			
			answer = Math.min(answer,total-simulation(select));
			return;
		}
		
		for(int i=0;i<W;++i) {
			select[cnt] = i;
			dfs(cnt+1,select);
		}
		
	}

	private static int simulation(int[] select) {
		cpyMap = new int[H][];
		
		for(int i=0;i<map.length;++i) cpyMap[i]= map[i].clone();
		
		int res = 0;
		for(int i=0;i<select.length;++i) {
			res+=dropBall(select[i]);
			
			if(res!=0) pushMap();
		}
		return res;
	}

	private static int dropBall(int idx) {
	
		int x = 0;
		int y = idx;
		int res = 0;
		while(true) {
			if(!isIn(x,y)) break;
			
			if(cpyMap[x][y]>0) {
				res+=explosion(x,y);
				break;
			}
			x+=1;
		}
		
		return res;
	}

	private static int explosion(int x, int y) {
		int res = 1;
		int s = cpyMap[x][y]-1;
		cpyMap[x][y]=0;
		for(int k=1;k<=s;++k) {
			for(int i=0;i<4;++i) {
				int nx = x+dx[i]*k;
				int ny = y+dy[i]*k;
				
				if(!isIn(nx,ny)) continue;
				if(cpyMap[nx][ny]>1) {
					res+=explosion(nx, ny);
				}
				else if(cpyMap[nx][ny]==1) {
					cpyMap[nx][ny]=0;
					res++;
				}
			}
		}
		
		return res;
	}
	
	private static void pushMap() {	
		for(int i=0;i<W;++i) {
			Queue<Integer> q = new LinkedList<Integer>();
			for(int j=H-1;j>=0;--j) {
				if(cpyMap[j][i]!=0) {
					q.add(cpyMap[j][i]);
					cpyMap[j][i]=0;
				}
			}
			
			int idx = H-1;
			while(!q.isEmpty()) {
				cpyMap[idx--][i] = q.poll();
			}
		}
	}

	private static boolean isIn(int x, int y) {
		return x>=0 && y>=0 && x<H && y<W;
	}

	private static void input() throws Exception{
		total = 0;
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		for(int i=0;i<H;++i) {
			st = new StringTokenizer(br.readLine()," ");
			for(int j=0;j<W;++j) {
				map[i][j]=Integer.parseInt(st.nextToken());
				if(map[i][j]>0) total++;
			}
		}
		
	}
		
	private static void printing() {
		System.out.println();
		for(int i=0;i<H;++i) {
			for(int j=0;j<W;++j) {
				System.out.print(cpyMap[i][j]+" ");
			}System.out.println();
		}
	}

}
