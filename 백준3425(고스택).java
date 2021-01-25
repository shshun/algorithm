package algo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class Main {

	static ArrayList<String> arr = new ArrayList<>();
	static ArrayList<Integer> num = new ArrayList<>();
	static Stack<Integer> s = new Stack<>();
	static int MAX_ = 1000000000;
	static int numIdx, N;

	public static void init() {
		s.clear();
		arr.clear();
		num.clear();
		numIdx = 0;
	}

	public static boolean operation(String str) {

	//	if (s.empty()) return false;

		if (str.equals("NUM")) {
			s.push(num.get(numIdx++));
		} else if (str.equals("POP")) {
			if (s.empty()) return false;
			s.pop();
		} else if (str.equals("INV")) {
			if (s.empty()) return false;
			int n = s.peek();
			s.pop();
			n *= -1;
			s.push(n);
		} else if (str.equals("DUP")) {
			int n = s.peek();
			s.push(n);
		} else if (str.equals("SWP")) {
			if (s.size() < 2) return false;

			int n1 = s.peek();
			s.pop();
			int n2 = s.peek();
			s.pop();

			s.push(n1);
			s.push(n2);
		} else if (str.equals("ADD")) {
			if (s.size() < 2) return false;

			long n1 = s.peek();
			s.pop();
			long n2 = s.peek();
			s.pop();

			long result = n1 + n2;
			if (Math.abs(result) > MAX_) return false;

			s.push((int) result);
		} else if (str.equals("SUB")) {
			if (s.size() < 2) return false;

			long n1 = s.peek();
			s.pop();
			long n2 = s.peek();
			s.pop();

			long result = n2 - n1;
			if (Math.abs(result) > MAX_) return false;

			s.push((int) result);
		} else if (str.equals("MUL")) {
			if (s.size() < 2) return false;

			long n1 = s.peek();
			s.pop();
			long n2 = s.peek();
			s.pop();

			long result = n2 * n1;
			if (Math.abs(result) > MAX_) return false;

			s.push((int) result);

		} else if (str.equals("DIV")) {
			if (s.size() < 2)
				return false;

			long n1 = s.peek();
			s.pop();
			if (n1 == 0) return false;

			long n2 = s.peek();
			s.pop();
			
		
			long result = Math.abs(n2 / n1);
			
			if ((n1 < 0 && n2 > 0) || (n1 > 0) && n2 < 0) {
				//System.out.println("무야호!");
				s.push((int)(-result));
			} else {
			//	System.out.println("nO");
				s.push((int)result);
			}

		} else if (str.equals("MOD")) {
			if (s.size() < 2)
				return false;

			long n1 = s.peek();
			s.pop();
			if (n1 == 0)
				return false;

			long n2 = s.peek();
			s.pop();

			
			long result = Math.abs(n2 % n1);
			
			if(result>MAX_) return false;
			if (n2 < 0) {
				s.push((int)-result);
			} else {
				s.push((int)result);
			}
		} 
		
		return true;
	}

	public static long calculation() {

		for (int i = 0; i < arr.size(); i++) {
			if (!operation(arr.get(i)))
				return Long.MAX_VALUE;
		}

		if (s.size() != 1)
			return Long.MAX_VALUE;
		return s.peek();
	}

	public static void main(String[] args) throws IOException {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			String[] str = bf.readLine().split(" ");

			if (str[0].equals("QUIT")) {
				break;
			}

			if (str[0].equals("END")) {
				// 구현
				N = Integer.parseInt(bf.readLine());
				for (int i = 0; i < N; i++) {
					int temp = Integer.parseInt(bf.readLine());
					s.push(temp);
					long answer = calculation();
					
					if(answer == Long.MAX_VALUE) System.out.println("ERROR");
					else System.out.println(answer);
					
					numIdx = 0;
					s.clear();
				}
			} else if (str[0].equals("")) {
				init();
				System.out.println();
			} else {
				arr.add(str[0]);
				if (str[0].equals("NUM"))
					num.add(Integer.parseInt(str[1]));
			}

		}
	}

}
