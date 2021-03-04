import java.io.*;
import java.util.*;

class pair {
	int first;
	int second;

	public pair(int first, int second) {
		super();
		this.first = first;
		this.second = second;
	}

}

/*
 * public static int lower_bound(long[] data, long n) { int left = 0; int right
 * = data.length;
 * 
 * while (left < right) { int mid = (left + right) / 2;
 * 
 * if (data[mid] >= n) right = mid; else left = mid + 1; }
 * 
 * return right; }
 * 
 * public static int upper_bound(long[] data, long n) { int left = 0; int right
 * = data.length;
 * 
 * while (left < right) { int mid = (left + right) / 2;
 * 
 * if (data[mid] <= n) left = mid + 1; else right = mid; }
 * 
 * return right; }
 */
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int N, K, M, V;

	static pair[] jewel;

	static PriorityQueue<Integer> backpack = new PriorityQueue<>();
	static PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		jewel = new pair[N];
		for (int i = 0; i < N; ++i) {
			st = new StringTokenizer(br.readLine(), " ");
			M = Integer.parseInt(st.nextToken());
			V = Integer.parseInt(st.nextToken());

			jewel[i] = new pair(M, V);
		}

		for (int i = 0; i < K; ++i) {
			backpack.add(Integer.parseInt(br.readLine()));
		}

		Arrays.sort(jewel, (o1, o2) ->Integer.compare(o1.first, o2.first));
		long answer = 0;
		int idx = 0;
		
		while (K-- > 0) {
			int weight = backpack.poll();

			while (idx < N && jewel[idx].first <= weight)
				pq.add(jewel[idx++].second);

			if (!pq.isEmpty()) {
				answer += pq.poll();
			}
		}
		System.out.println(answer);
	}
}
