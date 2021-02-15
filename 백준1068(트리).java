import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * @since 2021. 2. 15.
 * @author skyworking
 * @see https://www.acmicpc.net/problem/2143
 * @time 11:16
 * @caution 아이디어 잘 볼 것, 모든 부분합 구한후 upper bound lower bound 활용할 것
 */

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	static int N,ROOT;
	static ArrayList<Integer>[] arr;
	public static int lower_bound(List<Integer> data, int n) {
		int left = 0;
		int right = data.size();

		while (left < right) {
			int mid = (left + right) / 2;

			if (data.get(mid) >= n)
				right = mid;
			else
				left = mid + 1;
		}

		return right;
	}

	public static int upper_bound(List<Integer> data, int n) {
		int left = 0;
		int right = data.size();

		while (left < right) {
			int mid = (left + right) / 2;

			if (data.get(mid) <= n)
				left = mid + 1;
			else
				right = mid;
		}

		return right;
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		N= Integer.parseInt(br.readLine());
		//arr =new int[N];
		arr =new ArrayList[50];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<N;i++) {
			int node =Integer.parseInt(st.nextToken());
			if(arr[i]==null) arr[i] = new ArrayList<>();
			
			if(node<0) {
				ROOT = i;
				continue;
			}
			
			
			if(arr[node]==null) arr[node]= new ArrayList<>();
			arr[node].add(i);
		}
		
		int target = Integer.parseInt(br.readLine());
		for(int i=0;i<N;i++) {
			if(arr[i].contains(target)) {
				arr[i].remove(arr[i].indexOf(target));
			}
		}
		deleteNode(target);
		
		int answer =0;
		for(int i=0;i<N;i++) {
			if(arr[i]!=null && arr[i].size()==0) answer++;
		}
		if(answer == 0  && target!=ROOT) answer++;
		System.out.println(answer);
	}

	private static void deleteNode(int target) {
		// TODO Auto-generated method stub
		if(arr[target]==null) {
			return;
		}
		
		for(int i = 0;i<arr[target].size();i++) {
			deleteNode(arr[target].get(i));
		}
		arr[target]=null;
	}

}
