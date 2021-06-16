import java.io.*;
import java.util.*;

class Num{
	int a;
	int b;
	
	public Num(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

	@Override
	public String toString() {
		return "Num [a=" + a + ", b=" + b + "]";
	}
	
	
}

public class 백준_1111_IQTEST {
	/**
	  *@since 2021. 6. 16.
	  *@author skyworking
	  *@see
	  *@time 오후 3:13:21
	  *@caution
	  */
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int N;
	static int[] arr;
	static Num validNumber = null;
	public static void main(String[] args) throws Exception{	

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;++i) {
			arr[i] =  Integer.parseInt(st.nextToken());
		
		}				
		String answer ="";
		
		if(N==1) {
			answer  = "A";
		}
		else if(isValidNumber()) {
			if(validNumber==null) {
				answer = "A";
			}else {				
				int a = validNumber.a;
				int b = validNumber.b;
				answer = Integer.toString(arr[N-1]*a+b);
			}
		}else {
			answer = "B";
		}
		//System.out.println(validNumber.toString());
		System.out.println(answer);
	}

	private static boolean isValidNumber() {
		/*
		 * x1
		 * x2 = x1*a + b
		 * x3 = x2*a + b
		 * (x3-x2) / (x2-x1) = a;
		 */
		if(N==2) {
			if(arr[1]-arr[0]==0) {
				validNumber = new Num(1, 0);
			}
			return true;
		}
		
		
		if(arr[1]-arr[0]==0) {
			for(int i =2 ;i<N;++i) {
				if(arr[i]!=arr[0]) return false;
			}
			validNumber = new Num(1, 0);
			return true;
		}
		
		int a = (arr[2]-arr[1]) / (arr[1]-arr[0]);
		int b = arr[1]-(arr[0]*a);
		
		
		for(int i =1 ;i<N;++i) {
			int temp = arr[i-1]*a+b;
			if(temp!=arr[i]) return false;
		}
		
		validNumber = new Num(a, b);
		return true;
	}

}
