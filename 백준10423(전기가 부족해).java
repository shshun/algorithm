package boj_10423;

import java.io.*;
import java.util.*;

//1:18

class info implements Comparable<info> {
	int cur;
	int next;
	int dis;

	public info(int cur, int next, int dis) {
		super();
		this.cur = cur;
		this.next = next;
		this.dis = dis;
	}

	public int compareTo(info i) {
		return Integer.compare(dis, i.dis);
	}
}

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, K;
	static PriorityQueue<info> pq = new PriorityQueue<>();
	static int[] parent;

	public static boolean unionGroup(int a, int b) {
		int aP = findParent(a);
		int bP = findParent(b);

		if (aP == bP)
			return false;

		if (aP > bP) {
			int temp = aP;
			aP = bP;
			bP = temp;
		}

		parent[bP] = aP;

		return true;
	}

	public static int findParent(int idx) {
		if (parent[idx] == idx)
			return idx;
		return parent[idx] = findParent(parent[idx]);
	}

	public static void main(String[] args) throws Exception {
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		parent = new int[N];

		for (int i = 0; i < N; ++i) {
			parent[i] = i;
		}

		st = new StringTokenizer(br.readLine());

		int plantParent = Integer.parseInt(st.nextToken());
		plantParent--;

		while (st.hasMoreTokens()) {
			int idx = Integer.parseInt(st.nextToken());
			unionGroup(plantParent, --idx);
		}

		for (int i = 0; i < M; ++i) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			u--;
			v--;

			pq.add(new info(u, v, w));
		}

		int connect = 0;
		int fullConnect = N - K;
		int answer = 0;
		for (int i = 0; i < M; ++i) {
			if (connect == fullConnect) break;
			info item = pq.poll();

			if (unionGroup(item.cur, item.next)) {
				//System.out.println("connect : "+(char)('A'+item.cur)+" | "+(char)('A'+item.next));
				answer += item.dis;
				connect++;
			}
		}

		System.out.println(answer);

	}
}
