package algo;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.*;

//9:55

public class Main {
	static final int MINUS = 0;
	static final int PLUS = 1;
	static int N,M;
	static int NEAR=Integer.MAX_VALUE;
	static ArrayList<Integer> working =new ArrayList<>();
	static boolean[] broekn =new boolean[10];
	static boolean[] btn =new boolean[2];
	static Set<Integer> set=new HashSet<>();
	
	public static void bfs() {
		Queue<Integer> q = new LinkedList<>();
		
		for(int i=0;i<working.size();i++) {
			int num = working.get(i);
			q.add(num);
			set.add(num);
		}
	
		int len = Integer.toString(N).length()+3;
		
		int cmp=1;
		for(int i=0;i<len;i++) {
			cmp = cmp*10;
		}
		while(!q.isEmpty()) {
			int num = q.poll();
		
		//	System.out.println(num);
			int curGap = Math.abs(N-num);
			int nGap = Math.abs(NEAR-N);
			if(curGap < nGap) {
				if(!btn[MINUS] && N<num) continue;
				if(!btn[PLUS] && N>num) continue;	
				//System.out.println(NEAR+ " || "+num);
				NEAR = num;
				//System.out.println(NEAR);
			
			}

			for(int i=0;i<working.size();i++) {
				int temp =num*10;
				temp+=working.get(i);
				
				if(!set.contains(temp) && temp<cmp) {
					set.add(temp);
					q.add(temp);
				}
			}
		}
		
	}
    public static void main(String[] args) throws IOException {
     BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
     BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    
      N = Integer.parseInt(br.readLine());
      M = Integer.parseInt(br.readLine());
      
      for(int i=0;i<2;i++) {
    	  btn[i]=true;
      }
      
      if(M!=0) {
		 String[] input= br.readLine().split(" ");
		 for(int i=0;i<M;i++) {	 
				 if(input[i]=="+" || input[i]=="-" ) {
				 if(input[i]=="+") btn[PLUS] = false;
				 else btn[MINUS]=false;
			 }
			 else {
				 int num = Integer.parseInt(input[i]);
				 broekn[num]=true;
			 }
		 }
      }
	 
	 for(int i=0;i<10;i++) {
		 if(!broekn[i])  working.add(i);
	 }
	// System.out.println(working);
  
     bfs();

  
   int len = (int)(Math.log10(NEAR)+1); 
   if(NEAR==0) len = 1;
   int cntNumber =Math.abs(NEAR-N) +len;
   if(cntNumber<0) cntNumber=Integer.MAX_VALUE;
 //  System.out.println(NEAR+"   |    "+N);
   
   int cntBtn = Math.abs(100-N);
  
   
   int answer = Math.min(cntNumber, cntBtn);
    bw.write(answer+"");
    bw.flush();
    }
}

