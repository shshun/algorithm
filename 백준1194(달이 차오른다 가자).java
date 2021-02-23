import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Minsik {
	int x;
	int y;
	int key;// 26개

	public Minsik(int x, int y, int key) {
		super();
		this.x = x;
		this.y = y;
		this.key = key;
	}
}

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	public static int lower_bound(long[] data, long n) {
		int left = 0;
		int right = data.length;

		while (left < right) {
			int mid = (left + right) / 2;

			if (data[mid] >= n)
				right = mid;
			else
				left = mid + 1;
		}

		return right;
	}

	public static int upper_bound(long[] data, long n) {
		int left = 0;
		int right = data.length;

		while (left < right) {
			int mid = (left + right) / 2;

			if (data[mid] <= n)
				left = mid + 1;
			else
				right = mid;
		}

		return right;
	}

	static int N, M, sx, sy;
	static char[][] arr;
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static boolean[][][] visited = new boolean[51][51][1 << 6];

	public static void main(String[] args) throws NumberFormatException, IOException {

		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new char[N][M];
		for (int i = 0; i < N; ++i) {
			arr[i] = br.readLine().toCharArray();
			for (int j = 0; j < M; ++j) {
				if (arr[i][j] == '0') {
					sx = i;
					sy = j;
					// visitX |= i;
					// visitY |= j;
					break;
				}
			}
		}

		Queue<Minsik> q = new LinkedList<>();
		q.add(new Minsik(sx, sy, 0));

		int answer = 0;
		while (!q.isEmpty()) {
			int s = q.size();

			while (s-- > 0) {
				Minsik m = q.poll();
				int x = m.x;
				int y = m.y;
				int key = m.key;

				if (arr[x][y] == '1') {
					System.out.println(answer);
					return;
				}

				for (int k = 0; k < 4; k++) {
					int nx = x + dx[k];
					int ny = y + dy[k];

					if (nx < 0 || ny < 0 || nx >= N || ny >= M)
						continue;
					if (visited[nx][ny][key])
						continue;
					if (arr[nx][ny] == '#')
						continue;

					int tmpKey=key;
					if ('a' <= arr[nx][ny] && arr[nx][ny] <= 'f') { // key
						tmpKey |= 1 << (arr[nx][ny] - 'a');
						
						if(!visited[nx][ny][tmpKey]) {
							visited[nx][ny][tmpKey] = true;
							q.add(new Minsik(nx, ny, tmpKey));
							continue;
						}
						
					} else if ('A' <= arr[nx][ny] && arr[nx][ny] <= 'F') { // door
						int bit = 1 << (arr[nx][ny] - 'A');
						// if(arr[nx][ny]=='A') System.out.println(bit);
						if ((key & bit) == 0)
							continue; // 열쇠 없다면 다시
					}
	

					visited[nx][ny][key] = true;
					q.add(new Minsik(nx, ny, key));

				}
			}
			answer++;

		}
		System.out.println("-1");
	}

}
