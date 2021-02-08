import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * @since 2021. 2. 7.
 * @author skyworking
 * @see https://www.acmicpc.net/problem/1806
 * @time 
 * @caution
 */
public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[] arr= new int[100000];
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N=Integer.parseInt(st.nextToken());
		int S=Integer.parseInt(st.nextToken()); 
		
		st = new StringTokenizer(br.readLine()," ");
		
		for(int i=0;i<N;i++) {
			arr[i]= +Integer.parseInt(st.nextToken());
		}
		
		int answer = N+1;
		int left = 0;
		int right = 0;
		long sum = arr[0];
		
		while(left<=right && right < N) {
			   if (sum < S) {
		            sum += arr[++right];
		        }
		        else{
		            answer = Math.min(answer, right - left + 1);
		            sum -= arr[left++];
		        }
		}
		
		if(answer!=N+1) bw.write(""+answer);
		else bw.write("0");
		bw.newLine();
		bw.flush();
		
	}

	
}
