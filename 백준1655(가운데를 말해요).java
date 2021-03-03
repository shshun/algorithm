import java.io.*;
import java.util.*;

/*
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
}*/
public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	
	static int N;
	static PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
	static PriorityQueue<Integer> minHeap = new PriorityQueue<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		//StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(br.readLine());
		
		for(int i=0;i<N;++i) {
			int num = Integer.parseInt(br.readLine());
			if(maxHeap.size()==0 || minHeap.size()==maxHeap.size()) {
				maxHeap.add(num);
			}
			else {
				minHeap.add(num);
			}
			
			if(minHeap.size()>0 &&minHeap.peek()<maxHeap.peek()) {
				int minVal = minHeap.poll();
				int maxVal = maxHeap.poll();
				maxHeap.add(minVal);
				minHeap.add(maxVal);
			}
			
			sb.append(maxHeap.peek()).append("\n");
		}
		
		bw.write(sb.toString());
		bw.flush();

	}

}
