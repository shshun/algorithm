import java.io.*;
import java.util.*;

class pair{
	int first;
	int second;
	
	public pair(int first, int second) {
		this.first = first;
		this.second = second;
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

	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		
		pair[] arr = new pair[N];
		for(int i=0;i<N;++i) {
			st = new StringTokenizer(br.readLine()," ");
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			arr[i]= new pair(a,b);
		}
		
		Arrays.sort(arr, (o1,o2)-> Integer.compare(o1.second,o2.second));
		
		int answer = 1;
		int curEnd = arr[0].second;
		for(int i=1;i<N;++i) {
			if(curEnd<=arr[i].first) {
				answer++;
				curEnd = arr[i].second;
			}
		}
		System.out.println(answer);

	}

}
