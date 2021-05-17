import java.io.*;
import java.util.StringTokenizer;

public class 백준_14465_소가길을건너간이유5 {

	/**
	  *@since 2021. 5. 17.
	  *@author skyworking
	  *@see
	  *@time 오후 10:21:27
	  *@caution
	  */
	
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,K,B;
	static boolean[] traffic;
	
	public static void main(String[] args) throws Exception{
		StringTokenizer st= new StringTokenizer(br.readLine()," ");
		N= Integer.parseInt(st.nextToken());
		K= Integer.parseInt(st.nextToken());
		B= Integer.parseInt(st.nextToken());
		traffic = new boolean[N];
		
		int answer = K;
		for(int i=0;i<B;++i) {
			int idx = Integer.parseInt(br.readLine());
			traffic[--idx]=true;
		}
		
		

		int cnt = 0;
		for(int i=0;i<K;++i) {
			if(traffic[i]) cnt++;
		}
		answer = Math.min(answer, cnt);
		for(int i=K;i<N;++i) {			
			if(traffic[i-K]) cnt--;
			if(traffic[i]) cnt++;		
			answer = Math.min(answer, cnt);
			
		}
		System.out.println(answer);

	}

}
