package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;



public class Main {
	
	static int N,K;
	static Queue<String> q = new LinkedList<>();
    static Map<String,Boolean> map;
	
	public static String swapStr(String str,int i,int j) {

		char a = str.charAt(i);
		char b = str.charAt(j);

		char[] result = str.toCharArray();
		
		result[i]=b;
		result[j]=a;
		
		String r = String.valueOf(result);
		
		return r;
	}
	
	public static void splitStr(String str) {
		
	

		for(int i=0;i<str.length();i++) {
			for(int j=i+1;j<str.length();j++) {
					
				String temp =swapStr(str,i,j);
				
				if(temp.charAt(0)=='0') continue;
				
				if(!map.containsKey(temp)) {
					map.put(temp,true);
					q.add(temp);
				}
				
			}
		}
		
	}
	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String[] temp = bf.readLine().split(" ");
		N=Integer.parseInt(temp[0]);
		K=Integer.parseInt(temp[1]);
		
		q.add(temp[0]);
		
		int answer = -1;
		
		for(int i=0;i<K;i++) {
			int s = q.size();
			if(s==0) break;
			map = new HashMap<>();
			for(int j=0;j<s;j++) {
				String str = q.poll();
				splitStr(str);
				
			}
		}
	

		while(!q.isEmpty()) {
			answer = Math.max(answer, Integer.parseInt(q.poll()));
		}
		System.out.println(answer);
		
		
		
	}

}

