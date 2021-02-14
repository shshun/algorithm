import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * @since 2021. 2. 13.
 * @author skyworking
 * @see
 * @time
 * @caution
 */


public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();
	static int ROOT;
	static ArrayList<Integer> arr=new ArrayList<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
			
		String str;
		while(true) {
			str=br.readLine();
			if(str==null|| str.isEmpty()) break;
			arr.add(Integer.parseInt(str));
		}

		preorder(0,arr.size());


	}
	private static void preorder(int s, int e) {
		// TODO Auto-generated method stub
		if(s==e) return;
		
		int idx = s+1;
		
		while(true) {
			if(idx==e) break;
			
			if(arr.get(idx)>arr.get(s)) break;
				
			idx++;
		}
		
		preorder(s+1, idx);
		preorder(idx, e);
		System.out.println(arr.get(s));
	}


}
