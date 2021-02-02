package algo;

import java.io.*;
import java.util.*;


public class Main {
	/**
	  *@since 2021. 2. 2.
	  *@author skyworking
	  *@see https://www.acmicpc.net/problem/1759
	  *@time
	  *@caution
	  */
	static int L,C;
	static char[] alpha;
	//static ArrayList<String> answer= new ArrayList<>();
	
	static Set<Character> moum = new HashSet<>();
	
	static BufferedReader br;
	static BufferedWriter bw;
	
	
	public static boolean isValid(StringBuilder s) {
		
		int jaumCnt= 0;
		int moumCnt= 0;
		for(int i=0;i<s.length();i++) {
			if(moum.contains(s.charAt(i))) moumCnt++;
			else jaumCnt++;
		}
		
		return jaumCnt>=2 && moumCnt>=1 ? true : false;
	}
	//최소 한개 모음, 최소 2개 자음
	public static void dfs(int idx,int cnt,StringBuilder s) throws IOException {
	//	System.out.println(cnt +" "+s.toString());
		if(cnt == L ) {
			if(isValid(s)) {
				//answer.add(s.toString());
				bw.write(s.toString());
				bw.newLine();
			}
			return;
		}
		
		for(int i=idx;i<C;i++) {
			s.append(alpha[i]);
			dfs(i+1,cnt+1,s);
			s.setLength(s.length()-1);
		}
	}
	public static void main(String[] args) throws Exception{
		br = new BufferedReader(new InputStreamReader(System.in));
		bw= new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		
		st = new StringTokenizer(br.readLine()," ");
		L=Integer.parseInt(st.nextToken());
		C=Integer.parseInt(st.nextToken());
		alpha = new char[C];
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0;i<C;i++) {
			char c = st.nextToken().charAt(0);
			//alpha.add(c);
			alpha[i]=c;
		}
		moum.add('a');
		moum.add('e');
		moum.add('i');
		moum.add('o');
		moum.add('u');
		Arrays.sort(alpha); 

		dfs(0,0,new StringBuilder());
		
		bw.flush();
	}

}
