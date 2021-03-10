import java.io.*;
import java.util.*;

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
	static int N,A,B,C;
	static int[] segTree= new int[400004];

	static void update(int node, int start, int end, int index, int var)
	{
		if (index < start || index > end) return;
	 
	    segTree[node] += var;
	 
	    if (start != end)
	    {
	        int mid = (start + end) / 2;
	        update(node * 2, start, mid, index, var);
	        update(node * 2 + 1, mid + 1, end, index, var);
	    }
	}


	public static int query(int node, int start, int end,int rank)
	{
		if (start == end) return start;
		
		int mid = (start + end) / 2;
		if (segTree[node * 2] >= rank) {  
			return query(node * 2, start, mid, rank);
		}
		else { 
			return query(node * 2 + 1, mid+1, end, rank - segTree[node * 2]);
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		
		for(int i=0;i<N;++i) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
						
			A = Integer.parseInt(st.nextToken());
			
			if(A==1) {
				B = Integer.parseInt(st.nextToken());
				int idx = query(1, 1,100000,B);
				sb.append(idx).append("\n");
				update(1,1,100000,idx,-1);
			}
			else {
				B = Integer.parseInt(st.nextToken());
				C = Integer.parseInt(st.nextToken());
				update(1,1,100000,B,C);
			}
			
		}
		bw.write(sb.toString());
		bw.flush();
	}

}
