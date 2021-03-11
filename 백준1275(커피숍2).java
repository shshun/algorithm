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
	static int N,Q;
	static long[] tree;
	static long[] arr;
	
	static long init(int node, int start,int end) {
		if(start==end) {
			return tree[node]=arr[start];
		}
		
		int mid = (start+end)/2;
		
		return tree[node]=init(node*2,start,mid)+init(node*2+1,mid+1,end);
	}
	
	static void update(long diff,int idx, int node,int start,int end) {
		if(!(start<=idx && idx<=end)) return;
		
		tree[node]+=diff;
		
		if(start!=end) {
			int mid = (start+end)/2;
			
			update(diff,idx,node*2,start,mid);
			update(diff,idx,node*2+1,mid+1,end);
		}
	}
	
	static long query(int left,int right,int node,int start,int end) {
		if(right<start || end<left) return 0;
		
		if(left<=start && end<=right) {
			return tree[node];
		}
		
		int mid=(start+end)/2;
		
		return query(left,right,node*2,start,mid)+query(left,right,node*2+1,mid+1,end);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		N=Integer.parseInt(st.nextToken());
		Q= Integer.parseInt(st.nextToken());
		System.out.println(Math.pow(2,31));
		arr= new long[N+1];
		tree= new long[((N+1)*4)+1];
		st = new StringTokenizer(br.readLine()," ");
		for(int i=1;i<=N;++i) {
			arr[i]=Integer.parseInt(st.nextToken());
		}
		
		init(1,1,N);
		
	
		for(int i=0;i<Q;++i) {	
			st = new StringTokenizer(br.readLine()," ");
			int x=Integer.parseInt(st.nextToken());
			int y=Integer.parseInt(st.nextToken());
			int a=Integer.parseInt(st.nextToken());
			int b=Integer.parseInt(st.nextToken());
			
			if(x>y) {
				int tmp = x;
				x=y;
				y=tmp;
			}
			
			sb.append(query(x,y,1,1,N)).append("\n");
			long diff = b-arr[a];
			update(diff,a,1,1,N);
			arr[a]=b;
		}
		
		bw.write(sb.toString());
		bw.flush();
		
	}

}
