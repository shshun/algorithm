import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception{
		//List<Long> primeList = new ArrayList<>();
		int N = Integer.parseInt(br.readLine());
		boolean[] prime = new boolean[1003002];
		
		int num=2;
		while(true) {
			if(!prime[num])
			{
				if(num>=N && isPalidrom(num)) {
					System.out.println(num);
					System.exit(0);
				}
				for(int j=num+num;j<=1003001;j+=num) {
					prime[j]=true;
				}
			}
			num++;
		}
		

	}

	private static boolean isPalidrom(int num) {
		String str = Integer.toString(num);
		
		int end = str.length()-1;
		for(int i=0;i<str.length()/2;++i) {
			if(str.charAt(i)!=str.charAt(end--)) return false;
		}
		
		return true;
	}

}
