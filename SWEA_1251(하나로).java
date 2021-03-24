import java.io.*;
import java.util.*;

class Main {

	// 4:00
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static boolean find = false;
	public static void main(String[] args) throws IOException {
		//StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(br.readLine());
	
		dfs("1",1);
		
	}
	private static void dfs(String num,int leng) {
		
		if(find) return;		

		if(leng == N) {
			System.out.println(num);
			find = true;
			return;
		}
		
		for(int i = 1;i<=3;++i) {
			if(!isValid(num+i)) continue;
			dfs(num+i,leng+1);
		}
		
	}
	private static boolean isValid(String s) {
		
		int start = s.length()-1;
		int end = s.length();
		for(int i=1;i<=s.length()/2;++i) {
			
			if(s.substring(start,end).equals(s.substring(start-i, end-i))){
				return false;
			}
			
			start--;
		}
		
		return true;
	}

	
	
	
}
