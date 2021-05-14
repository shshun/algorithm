import java.io.*;
import java.util.*;

class Loop implements Comparable<Loop>{
	int idx;
	int height;
	
	
	public Loop(int idx, int height) {
		super();
		this.idx = idx;
		this.height = height;
	}


	@Override
	public int compareTo(Loop o) {
		if(height==o.height) return Integer.compare(idx,o.idx);
		return Integer.compare(idx, o.idx);
	}
	
	
	
	
}

public class 백준_2304_창고다각형 {

	/**
	  *@since 2021. 5. 15.
	  *@author skyworking
	  *@see
	  *@time 오전 2:08:47
	  *@caution
	  */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N,L,H;
	static Loop[] arr;
	public static void main(String[] args) throws Exception{
		N = Integer.parseInt(br.readLine());
		arr = new Loop[N];
		for(int i=0;i<N;++i) {			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			L = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			arr[i]= new Loop(L, H);
		}
		Arrays.sort(arr);
		int answer = 0;
		Stack<Loop> stack = new Stack<>();
		stack.add(arr[0]);
		int lastRightIdx = 0;
		for(int i=1;i<N;++i) {
			int curH = arr[i].height;
			int curIdx = arr[i].idx;
				
			if(stack.peek().height<=curH) {
				answer+=stack.peek().height*(curIdx-stack.peek().idx);
				stack.pop();
				stack.add(arr[i]);
				lastRightIdx = i;
			}
		}		
		//8 24 20
		//52
		//30
		
		
		// 42 + 56 = > 98
		// 52 + 48 => 92?
		while(!stack.isEmpty()) answer+=stack.pop().height;
		//System.out.println(answer + " | "+lastRightIdx);
		stack.add(arr[N-1]);
		for(int i=N-2;i>=lastRightIdx;--i) {
			int curH = arr[i].height;
			int curIdx = arr[i].idx;
			
			if(stack.peek().height<=curH) {
				answer+=stack.peek().height*(stack.peek().idx-curIdx);
				stack.pop();
				stack.add(arr[i]);
			}
		}
		

		System.out.println(answer);

	}

}
