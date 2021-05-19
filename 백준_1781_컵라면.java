import java.io.*;
import java.util.*;

class Node {
	int deadLine;
	int cup;

	public Node(int deadLine, int cup) {
		super();
		this.deadLine = deadLine;
		this.cup = cup;
	}

	@Override
	public String toString() {
		return "Node [deadLine=" + deadLine + ", cup=" + cup + "]";
	}
	
	
}


public class 백준_1781_컵라면 {
		
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		int N = Integer.parseInt(br.readLine());
		
		long answer = 0;
		int lastDay = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>(N,(o1,o2)-> {
				if(o1.deadLine == o2.deadLine) {
					return Integer.compare(o2.cup, o1.cup);
				}
				return Integer.compare(o1.deadLine,o2.deadLine);
		});
		
		for(int i=0;i<N;++i) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int deadline = Integer.parseInt(st.nextToken());
			int cup = Integer.parseInt(st.nextToken());
			
			lastDay= Math.max(lastDay, deadline);
			pq.add(new Node(deadline,cup));
			
		}

		PriorityQueue<Integer> pool = new PriorityQueue<>();
		while(!pq.isEmpty()) {
			Node item = pq.poll();

			answer+=item.cup;
			pool.add(item.cup);
	
			while(item.deadLine<pool.size()) answer-=pool.poll();
		}

		System.out.println(answer);
	}

}
