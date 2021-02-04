import java.awt.Point;
import java.io.*;
import java.util.*;

public class Main {
	/**
	 * @since 2021. 2. 3.
	 * @author skyworking
	 * @see https://www.acmicpc.net/problem/2493
	 * @time 5:22
	 * @caution
	 */

	static BufferedWriter bw;
	static BufferedReader br;

	public static void main(String[] args) throws NumberFormatException, IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;//
		StringBuilder sb=new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine()," ");
		Stack<Point> stack = new Stack<>();
		int idx = 1;
		while(st.hasMoreTokens()) {
			int num = Integer.parseInt(st.nextToken());
			
			while(!stack.empty()) {
				if(stack.peek().y>num) {
					sb.append(stack.peek().x+" ");
					break;
				}
				stack.pop();
			}
			if(stack.isEmpty()) sb.append("0 ");
			stack.add(new Point(idx++,num));
		}
		
		
		
		bw.write(sb.toString());
		bw.newLine();
		

		bw.flush();
	}

}
