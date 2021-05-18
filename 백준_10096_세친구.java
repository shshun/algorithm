import java.io.*;
import java.util.*;

public class 백준_10096_세친구 {
	
	/**
	  *@since 2021. 5. 19.
	  *@author skyworking
	  *@see
	  *@time 오전 1:37:58
	  *@caution
	*/
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static char[] s;
	static Map<String,Boolean> map = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		s = br.readLine().toCharArray();
		
		if(N%2==0) {
			System.out.println("NOT POSSIBLE");
			return;
		}
		
		char[] shortStrA = Arrays.copyOfRange(s, 0, (N/2));
		char[] longStrA = Arrays.copyOfRange(s, N/2, N);
		
		char[] longStrB = Arrays.copyOfRange(s, 0, (N/2)+1);
		char[] shortStrB = Arrays.copyOfRange(s, (N/2)+1, N);
		
		int idxA = 0;
		int idxB = 0;
		boolean[] find = new boolean[2];
		boolean[] valid = new boolean[2];
		Arrays.fill(valid, true);
		
		for(int i=0;i<shortStrA.length;++i) {
			if(!find[0] && shortStrA[i]!=longStrA[idxA]) {
				find[0]=true;
				i--;
			}
			else if(find[0] && shortStrA[i]!=longStrA[idxA]) {
				valid[0]=false;
				break;
			}
			idxA++;		
		}
		

		for(int i=0;i<shortStrB.length;++i) {
			if(!find[1] && longStrB[idxB]!=shortStrB[i]) {
				find[1]=true;
				i--;
			}
			else if(find[1] && longStrB[idxB]!=shortStrB[i]) {
				valid[1]=false;
				break;
			}
			idxB++;
		}
		
		
		String A = String.valueOf(shortStrA);
		String B = String.valueOf(shortStrB);
		
		if(A.equals(B)) {
			System.out.println(A);
			return;
		}
		
		if(valid[0] && valid[1]) System.out.println("NOT UNIQUE");
		else if(valid[0] || valid[1]) {
			if(valid[0]) {
				System.out.println(A);
			}
			else {
				System.out.println(B);
			}
		}
		else System.out.println("NOT POSSIBLE");
	}
}
