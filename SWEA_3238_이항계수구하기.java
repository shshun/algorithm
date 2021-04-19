import java.io.*;
import java.util.*;

public class SWEA_3238_이항계수구하기 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int TC;
	static long N,R;
	static int P;
	static long[] fac;
	
	public static void main(String[] args) throws Exception {
		TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; ++tc) {

			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			N = Long.parseLong(st.nextToken());
			R = Long.parseLong(st.nextToken());
			P = Integer.parseInt(st.nextToken());
			
			fac = new long[(P+1)];		
			fac[0]=1;	
			
			for(int i=1;i<=P;++i) {
				fac[i]=(fac[i-1]*i)%(P);
			}
			
			long res = 1;
			
			while( N!= 0 && R != 0) {
				int n = (int) (N%P);
				int r = (int) (R%P);
				
				if(n<r) {
					res = 0;
					break;
				}
				
				res = (res * fac[n]) % P;
		        for(int i=0;i<P-2;++i)
		            res = ((res * fac[r])%P * fac[n-r])%P;
		        N/=P;
		        R/=P;
			}
			
			sb.append(String.format("#%d %d\n",tc,res));

		}
		System.out.println(sb);

	}
	
	public static long convertChild(long val,int pow) {
		if(pow==0) return 1;
		
		long num = convertChild(val,pow>>1);
		long ret = (num*num)%P;
		if(pow%2==0) return ret;
		else return (ret*val)%P;
	}

}
