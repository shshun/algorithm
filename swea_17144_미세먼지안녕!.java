import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	// 1 05
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int R, C, T;
	static Queue<Point> q = new LinkedList<>();
	static Queue<int[]> tq = new LinkedList<>();
	static Point[] Aircon = new Point[2];
	static int[][] map;
	static int[][] dust;
	static int[][] movePath;

	static final int AIR = -1;

	public static void main(String[] args) throws Exception {
		input();
		solve();
		System.out.println(sb);
	}

	private static void solve() {
		recordCleanPath();
		
		for (int t = 0; t < T; ++t) {
			spreadJaehyeon();
			cleanJaehyeon();
		}

		sb.append(countJaehyeon());
	}

	private static void printing() {
		System.out.println();
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static int countJaehyeon() {
		int res = 0;
		for (int i = 0; i < R; ++i) {
			for (int j = 0; j < C; ++j) {
				if (map[i][j] != AIR)
					res += map[i][j];
			}
		}

		return res;
	}

	private static void cleanJaehyeon() {
		

		while(!tq.isEmpty()) {
			int[] p = tq.poll();
			
			int x = p[0];
			int y= p[1];
			int c = (x < Aircon[1].x ? 1 : -1);
			
			int d = movePath[x][y];
			
			map[x][y]=0;
			if(d==4) continue;
			int nx = x+dx[d] * c;
			int ny = y+dy[d];
			
			dust[nx][ny]=p[2];		
		}
		tq.clear();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == AIR)
					continue;
				if(dust[i][j]>0) {
					map[i][j]=dust[i][j];
					dust[i][j]=0;
				}
				if (map[i][j] > 0) {
					q.add(new Point(i, j));
				}
			}
		}
	}

	private static boolean canSpread(int x, int y) {
		if (x < 0 || y < 0 || x >= R || y >= C) {
			return false;
		}
		if (map[x][y] == AIR) {
			return false;
		}
		return true;
	}

	private static void spreadJaehyeon() {
		while (!q.isEmpty()) {
			Point tempP = q.poll();
			int x = tempP.x;
			int y = tempP.y;
			int cnt = 0;
			
			if(map[x][y]<5) continue;
			
			for (int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (canSpread(nx, ny)) {
					dust[nx][ny] += (map[x][y]) / 5;
					cnt++;
				}
			}

			int t = (map[x][y] / 5) * cnt;
			map[x][y] -= t;
		}

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (map[i][j] == AIR)
					continue;
				map[i][j] += dust[i][j];
				dust[i][j] = 0;
				if(map[i][j]!=0 && movePath[i][j]>=0) {
					tq.add(new int[] {i,j,map[i][j]});
				}
			}
		}
	}

	private static void recordCleanPath() {
		for (int i = 0; i < Aircon.length; i++) {
			int x = Aircon[i].x;
			int y = Aircon[i].y;
			int c = (i == 0 ? 1 : -1);
			
			for (int j = 0; j < 4;) {
				int nx = x + dx[j] * c;
				int ny = y + dy[j];
				if (canSpread(nx, ny)) {
					movePath[nx][ny] = j;
					x = nx;
					y = ny;
				} else {
					j++;
				}
			}
			x = Aircon[i].x + dx[0] * c;
			y = Aircon[i].y + dy[0];
			for (int j = 0; j < 4;) {
				int nx = x + dx[j] * c;
				int ny = y + dy[j];
				if (canSpread(nx, ny)) {
					movePath[nx][ny] = j;
					x = nx;
					y = ny;
				} else {	
					movePath[nx-dx[j]*c][ny-dy[j]] = ++j;				
				}
				
			}
			

		}
	}

	private static void input() throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		map = new int[R][C];
		dust = new int[R][C];

		movePath = new int[R][C];

		int idx = 0;
		for (int i = 0; i < R; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			Arrays.fill(movePath[i], -1);
			for (int j = 0; j < C; ++j) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == -1) {
					Aircon[idx++] = new Point(i, j);
				} else if (map[i][j] > 0) {
					q.add(new Point(i, j));
				}
			}
		}
	}

}
