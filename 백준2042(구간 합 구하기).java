import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

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

	public static long sum(long[] tree, int node, int start, int end, int left, int right) {
		if (left > end || right < start)
			return 0;

		if (left <= start && end <= right)
			return tree[node];

		int mid = (start + end) / 2;
		return sum(tree, node * 2, start, mid, left, right) + sum(tree, node * 2 + 1, mid + 1, end, left, right);
	}

	public static void update(long[] tree, int node, int start, int end, int idx, long diff) {
		if (!(start <= idx && idx <= end))
			return;

		tree[node] += diff;

		if (start != end) {
			int mid = (start + end) / 2;
			update(tree, node * 2, start, mid, idx, diff);
			update(tree, node * 2 + 1, mid + 1, end, idx, diff);
		}
	}

	public static long init(int[] data, long[] tree, int node, int start, int end) {
		if (start == end)
			return tree[node] = data[start];

		int mid = (start + end) / 2;

		return tree[node] = init(data, tree, node * 2, start, mid) + init(data, tree, node * 2 + 1, mid + 1, end);
	}

	static int[] arr;
	static long[] tree;
	static int N,M,K;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		StringBuilder sb = new StringBuilder();
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		arr= new int[N];
		
		for(int i=0;i<N;i++) {
			arr[i]=Integer.parseInt(br.readLine());
		}
		tree = new long[N*4];
		init(arr,tree,1,0,N-1);
	
		for(int i=0;i<M+K;++i) {
			st = new StringTokenizer(br.readLine(), " ");
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(a==1) {
				long diff = c - arr[b-1];
				arr[b-1]= c;
				update(tree, 1, 0, N - 1, b - 1, diff);
			}
			else {
				sb.append(sum(tree,1,0,N-1,b-1,c-1)).append("\n");
			}
			
		}
		bw.write(sb.toString());
		bw.flush();

	}

}
