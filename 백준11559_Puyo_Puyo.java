import java.awt.Point;
import java.io.*;
import java.util.*;

public class 백준11559_Puyo_Puyo {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static char[][] map = new char[12][6];

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static boolean[][] visited;
	static Queue<Point> q = new LinkedList<Point>();

	public static void printing() {
		System.out.println();
		for (int i = 0; i < 12; ++i) {
			for (int j = 0; j < 6; ++j) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) throws Exception {

		for (int i = 0; i < 12; ++i) {
			String input = br.readLine();
			map[i] = input.toCharArray();

			for (int j = 0; j < 6; ++j) {
				if (map[i][j] != '.') {
					q.add(new Point(i, j));
				}
			}
		}

		int answer = 0;
		while (!q.isEmpty()) {

			int boomCnt = 0;
			visited = new boolean[12][6];

			int s = q.size();

			for (int i = 0; i < s; ++i) {
				Point item = q.poll();
				if (visited[item.x][item.y])
					continue;
				if (canExplosion(item))
					boomCnt++;
			}

			if (boomCnt == 0) {
				break;
			}

			//printing();
			fallMap();
			//printing();
			answer++;
		}

		System.out.println(answer);

	}

	private static void fallMap() {
		for (int j = 0; j < 6; ++j) {
			Queue<Character> tmpQ = new LinkedList<Character>();
			for (int i = 11; i >= 0; --i) {
				if(map[i][j]!='.') {
					tmpQ.add(map[i][j]);
					map[i][j]='.';
				}
			}
			
			int idx =11;
			while(!tmpQ.isEmpty()) {
				map[idx][j]=tmpQ.poll();
				q.add(new Point(idx--,j));
			}
		}

	}

	private static boolean canExplosion(Point item) {
		Queue<Point> tmpQ = new LinkedList<Point>();
		Queue<Point> backupQ = new LinkedList<Point>();

		tmpQ.add(new Point(item.x, item.y));
		backupQ.add(new Point(item.x, item.y));

		boolean[][] tmpVisit = new boolean[12][6];
		tmpVisit[item.x][item.y] = true;

		while (!tmpQ.isEmpty()) {

			Point p = tmpQ.poll();

			for (int d = 0; d < 4; ++d) {
				int nx = p.x + dx[d];
				int ny = p.y + dy[d];

				if (!isIn(nx, ny) || tmpVisit[nx][ny])
					continue;
				if (map[nx][ny] == map[p.x][p.y]) {
					tmpQ.add(new Point(nx, ny));
					backupQ.add(new Point(nx, ny));
					tmpVisit[nx][ny] = true;
				}
			}
		}

		if (backupQ.size() >= 4) {
			explosion(backupQ);
			return true;
		}

		return false;
	}

	private static void explosion(Queue<Point> tmpQ) {
		while (!tmpQ.isEmpty()) {
			Point p = tmpQ.poll();
			visited[p.x][p.y] = true;
			map[p.x][p.y] = '.';
		}
	}

	public static boolean isIn(int x, int y) {
		return x >= 0 && x < 12 && y >= 0 && y < 6;
	}

}
