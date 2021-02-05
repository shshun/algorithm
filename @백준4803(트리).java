import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	/**
	 * @since 2021. 2. 5.
	 * @author skyworking
	 * @see https://www.acmicpc.net/problem/4803
	 * @time 9:13
	 * @caution Cycle 체크 까지 해줄 것, tree 탐색 시 visit 체크★ 
	 */

	static BufferedWriter bw;
	static BufferedReader br;
	static int N,M;
	static int[] dist=new int[501];
	static int[] conn=new int[501];
	static int[] check=new int[501];;
	
	
	public static int findParent(int idx) {
		// TODO Auto-generated method stub
		  while(dist[idx]!=idx){
		        dist[idx]=dist[dist[idx]];
		        idx=dist[idx];
		    }
		    return idx;
		/*if(dist[idx]==idx) return idx;
		
		return dist[idx]=findParent(dist[idx]);*/
	}
	
	
	public static void unionGroup(int a, int b) {
		// TODO Auto-generated method stub
		int aP= findParent(a);
		int bP = findParent(b);
		
		if(aP==bP) {
			check[aP] = 0;
		}
		else {
			if(conn[aP]<conn[bP]){
	            dist[aP]=bP;
	            conn[bP]+=conn[aP];
	        }else{
	            dist[bP]=aP;
	            conn[aP]+=conn[bP];
	        }
		}
		
	}

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;//
		
		int TC = 1;
		while(true) {
			st = new StringTokenizer(br.readLine()," ");
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());	
			
			if(N==0 && M==0) break;	
			
			
			for(int i=1;i<=N;i++) {
				dist[i]=i;
				conn[i]=1;
				check[i]=1;
			}
			
					
			for(int i=0;i<M;i++) {	
				st = new StringTokenizer(br.readLine()," ");
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());	
				if(a!=b) unionGroup(a,b);
			}
			
			int answer =0;
			for(int i=1;i<=N;i++) {
				int idx =findParent(i); 
				if(check[idx]>0) {
					check[idx]=0;
					answer++;
				}
			}
			
			System.out.print(String.format("Case %d: ", TC++));
			if(answer==0) System.out.print("No trees.");
			else if(answer==1) System.out.print("There is one tree.");
			else System.out.print(String.format("A forest of %d trees.",answer));
			System.out.println();
		}
		
		//bw.flush();
	}

	
	

}
