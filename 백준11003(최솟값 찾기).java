import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb;

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

	public static boolean next_permutation(int[] data) {
		
	}
	static int N, L;

	static Deque<Point> deq;

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		deq = new ArrayDeque<Point>();// index, value 저장
		st = new StringTokenizer(br.readLine(), " ");

		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());

			if (deq.isEmpty()) {
				deq.add(new Point(i, num));
			} else {
				//최소값 관리
				while (!deq.isEmpty() && deq.peekFirst().x <= i - L)
					deq.pollFirst();

				if (deq.isEmpty() || num <= deq.peekFirst().y) { //최소 값 갱신
					deq.addFirst(new Point(i, num));
				} else { //최소 값 아니면 뒤에다
					while (!deq.isEmpty() && deq.peekLast().y >= num)
						deq.pollLast();
					deq.addLast(new Point(i, num));
				}
			}
			sb.append(deq.peekFirst().y).append(" ");
		}

		bw.write(sb.toString());
		bw.flush();
	}

}
