import java.io.*;
import java.util.*;

public class 백준_18870_좌표압축 {
	/**
	  *@since 2021. 6. 16.
	  *@author skyworking
	  *@see
	  *@time 오전 1:47:45
	  *@caution
	  */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static Map<Integer,Integer> map = new HashMap<Integer, Integer>();
	static int N;
	public static void main(String[] args) throws Exception{
		N= Integer.parseInt(br.readLine());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<N;++i) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num;
		}
		
		int[] copyArr = arr.clone();
		Arrays.sort(copyArr);
		
		int idx = 0;
		for(int n : copyArr) {
			if(!map.containsKey(n)) {				
				map.put(n, idx++);
			}
		}
		
		for(int n : arr) {
			sb.append(map.get(n)+" ");
		}
		System.out.println(sb);

	}

}
