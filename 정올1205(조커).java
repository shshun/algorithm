import java.io.*;
import java.util.*;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static StringBuilder sb = new StringBuilder();

	public static int lower_bound(long[] data, long n) {
		int left = 0;
		int right = data.length;

		while (left < right) {
			int mid = (left + right) / 2;

			if (data[mid] >= n)
				right = mid;
			else
				left = mid + 1;
		}

		return right;
	}

	public static int upper_bound(long[] data, long n) {
		int left = 0;
		int right = data.length;

		while (left < right) {
			int mid = (left + right) / 2;

			if (data[mid] <= n)
				left = mid + 1;
			else
				right = mid;
		}

		return right;
	}
	static Map<String,Integer> map = new HashMap<>();
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		String input;
		int cnt = 0;
		while((input=br.readLine())!=null && input.length()!=0) {
			cnt++;
			if(map.containsKey(input)) {
				map.put(input,map.get(input)+1);
			}else {
				map.put(input,1);
			}
		}
		
		List<String> keySetList = new ArrayList<>(map.keySet());
        Collections.sort(keySetList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        for(int i=0;i<keySetList.size();i++) {
        	float per =  (map.get(keySetList.get(i))/(float)cnt)*100;
        	sb.append(keySetList.get(i)).append(String.format(" %.4f\n", per));
        }
		bw.write(sb.toString());
		bw.flush();

	}

}
