import java.io.*;
import java.util.*;

class info  {
	int d;
	int x;
	int y;
	boolean consume = false;

	public info(int d, int x, int y) {
		super();
		this.d = d;
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		info o = (info) obj;
		return d == o.d && x == o.x && y == o.y;
	}

}

// 8:56
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int N, M;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
						// 동 남 서 북 
	static int[][] arr;

	static info START, END;

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; ++j) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 동 남 서 북
		st = new StringTokenizer(br.readLine());
		int sx = Integer.parseInt(st.nextToken());
		int sy = Integer.parseInt(st.nextToken());
		int sd = Integer.parseInt(st.nextToken());
		START = new info(convertDir(sd), --sx, --sy);

		st = new StringTokenizer(br.readLine());
		int fx = Integer.parseInt(st.nextToken());
		int fy = Integer.parseInt(st.nextToken());
		int fd = Integer.parseInt(st.nextToken());
		END = new info(convertDir(fd), --fx, --fy);

		System.out.println(bfs());

	}

	private static int convertDir(int idx) {
		//	static int[] dx = { 0, 1, 0, -1 };
		//  static int[] dy = { 1, 0, -1, 0 };
							//  동   남    서    북 
                       		//  동   서    남     북 1 2 3 4
							 // 동 남 서 북 0 1 2 3
		if (idx == 1) {
			return 0;
		} else if (idx == 2) {
			return 2;
		} else if (idx == 3) {
			return 1;
		} else {
			return 3;
		}
	}

	private static boolean isRange(int x, int y) {
		return x >= 0 && x < N && y >= 0 && y < M;
	}

	private static int bfs() {
		boolean[][][] visited = new boolean[4][N][M];
		Queue<info> q = new LinkedList<>();
		
		int res= 0;
		visited[START.d][START.x][START.y] = true;
		q.add(START);

		while (!q.isEmpty()) {
			int s = q.size();

			while (s-- > 0) {
				info item = q.poll();
				int x = item.x;
				int y = item.y;
				int d = item.d;

				if(x==END.x && y==END.y &&d==END.d ) {
					return res;
				}
				
				for (int i = 1; i <= 3; ++i) {
					int nx = x + dx[d] * i;
					int ny = y + dy[d] * i;
					
					if (!isRange(nx, ny)) break;
					if( arr[nx][ny] == 1) break;
					
					if(!visited[d][nx][ny]) {
						visited[d][nx][ny] = true;
						q.add(new info(d, nx, ny));
					}
					
				}
				for (int i = -1; i < 2; ++i) {
					if (i == 0)
						continue;
					int nextDir = (d + i);

					if (nextDir < 0)
						nextDir = 3;
					else if (nextDir > 3)
						nextDir = 0;

					if (!visited[nextDir][x][y]) {
						visited[nextDir][x][y] = true;
						q.add(new info(nextDir, x, y));
					}
				}

			}
			res++;

		}
		return 0;

	}

}
