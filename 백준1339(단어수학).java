
import java.io.*;
import java.util.*;


public class Main {
	/**
	  *@since 2021. 2. 2.
	  *@author skyworking
	  *@see https://www.acmicpc.net/problem/1339
	  *@time
	  *@caution
	  */

	static int answer = 0;
	static int N,CNT;
	static int[] val;
	static Map<Character,Integer> alpha = new HashMap<Character, Integer>();
	static boolean[] visit;
	static String[] str;
	
	
	public static int calculation() {
		// TODO Auto-generated method stub
		
		int total = 0;
		for(int i=0;i<N;i++) {
			int leng = (int) Math.pow(10, str[i].length()-1);
			int num = 0;
			for(int j=0;j<str[i].length();j++) {
				int idx = alpha.get(str[i].charAt(j));
				num+=leng*val[idx];
				//System.out.println(num);
				leng=leng/10;
			}
			total+=num;
		}
		return total;
	}
	public static void setValue(int idx) {
		if(idx==CNT) {
			answer = Math.max(answer, calculation());
			return;
		}
	
		for(int i=0;i<val.length;i++) {
			if(!visit[9-i]) {
				val[idx]=9-i;
				visit[9-i]=true;
				setValue(idx+1);	
				visit[9-i]=false;
				val[idx]=0;
			}
			
		}
		
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb;
		
		N=Integer.parseInt(br.readLine());
		str = new String[N];
		
		Set<Character> set = new HashSet<>();
	
		for(int i=0;i<N;i++) {
			sb = new StringBuilder(br.readLine());
			str[i]=sb.toString();
			for(int j=0;j<str[i].length();j++) {
				set.add(str[i].charAt(j));
			}
		}
		
		CNT = set.size();
		val = new int[CNT];
	
		visit = new boolean[10];
		
		int idx = 0;
		
		for(char c : set) {
			alpha.put(c, idx++);
		}
	
		setValue(0);
		
		
		
		
		bw.write(""+answer);
		bw.flush();
	}

}
