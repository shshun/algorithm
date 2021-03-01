import java.io.*;
import java.util.*;

class pair{
	int first;
	int second;
	
	public pair(int first, int second) {
		super();
		this.first = first;
		this.second = second;
	}
	
}

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int N,M,K;
	static int[] arr;
	static pair[] dist;
	
//	static Map<Integer,ArrayList<Integer>> map = new HashMap<Integer, ArrayList<Integer>>();
	
	
	static int findParent(int idx) {
		if(dist[idx].first==idx) return idx;
		
		return dist[idx].first=findParent(dist[idx].first);
	}
	
	static void unionGroup(int a,int b) {
		int aP = findParent(a);
		int bP = findParent(b);
		
		if(aP!=bP) {
			
			int costA = dist[aP].second;
			int costB = dist[bP].second;
			
			if(costA<costB) {
				dist[bP].first = dist[aP].first; 
				dist[bP].second = dist[aP].second;
			}
			else if(costA>costB) {
				dist[aP].first = dist[bP].first; 
				dist[aP].second = dist[bP].second;
			}
			else {
				if(bP<aP) dist[aP].first = dist[bP].first;
				else dist[bP].first = dist[aP].first; 
			}
						
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		N=Integer.parseInt(st.nextToken());
		M=Integer.parseInt(st.nextToken());
		K=Integer.parseInt(st.nextToken());
		
		arr= new int[N];
		dist= new pair[N];
		
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<N;++i) {
			arr[i]=Integer.parseInt(st.nextToken());
			dist[i]=new pair(i,arr[i]);
		}
		
		
		for(int i=0;i<M;++i) {
			st = new StringTokenizer(br.readLine()," ");
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			unionGroup(--v, --w);
		}
		
		Set<Integer> set = new HashSet<>();
		long res=0;
		for(int i =0;i<N;++i) {
			int parent = findParent(dist[i].first);
			//System.out.println((i+1)+" parent : "+findParent(dist[i].first)+" | "+dist[i].second );
			if(!set.contains(parent)){
				res+=dist[parent].second;
				set.add(dist[parent].first);
			}
		}
		
		if(res<=K) System.out.println(res);
		else System.out.println("Oh no");
		
	}

}

