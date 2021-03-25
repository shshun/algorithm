import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main2 {
	// 8:47
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int N, TC;
	static Point[] coor;
	static int[][] adj;
	static final int INF = (int)1e9;
	public static void main(String[] args) throws IOException {
		TC = Integer.parseInt(br.readLine());

		for (int tc = 0; tc < TC; ++tc) {
			StringTokenizer st;
			N = Integer.parseInt(br.readLine());
			N+=2;
			coor = new Point[N ];
			adj = new int[N ][N ];
			
			for (int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());

				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				coor[i] = new Point(x, y);
			}


			calDistance();
			
			bw.write(adj[0][N-1]>=INF?"sad\n":"happy\n");	
		}
		bw.flush();
	}

	private static void calDistance() {
		for (int i = 0; i < N; ++i) {
			for (int j = 0; j < N; ++j) {
				int val = Math.abs(coor[i].x - coor[j].x) + Math.abs(coor[i].y - coor[j].y);
				if(val>1000) val = INF;			
				adj[i][j]=val;
			}
		}

		for (int k = 0; k < N; ++k) {
			for (int i = 0; i < N; ++i) {
				for (int j = 0; j < N; ++j) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k]+adj[k][j]);
				}
			}

		}

	}
}