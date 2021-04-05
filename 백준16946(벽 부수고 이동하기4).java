import java.io.*;
import java.util.*;

//4:14

class casher implements Comparable<casher>{
   int idx;
   int time;
   public casher(int idx, int time) {
      super();
      this.idx = idx;
      this.time = time;
   }
   @Override
   public int compareTo(casher o) {
      
      if(time == o.time) {
         return Integer.compare(idx,o.idx);
      }
      return Integer.compare(time, o.time);
   }

   
   
}
class info implements Comparable<info>{
   int id;
   int w;
   int k;
   
   public info(int id, int w, int k) {
      super();
      this.id = id;
      this.w = w;
      this.k = k;
   }

   @Override
   public int compareTo(info o) {
      // TODO Auto-generated method stub
      if(w==o.w) {
         return Integer.compare(o.k,k);
      }
      return Integer.compare(w, o.w);
   }
   
   
}
public class Main {
   static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
   static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
   
   static int N,K;
   static PriorityQueue<info> pq = new PriorityQueue<>();
   static PriorityQueue<casher> cntPq=new PriorityQueue<>();
   public static void main(String[] args) throws Exception{
      StringTokenizer st = new StringTokenizer(br.readLine());
      N=Integer.parseInt(st.nextToken());
      K=Integer.parseInt(st.nextToken());
      
      long answer= 0;
      
      for(int i=1;i<=K;++i) cntPq.add(new casher(i,0));
      
      int cnt = 1;
      
      for(int i=0;i<N;++i) {
         st = new StringTokenizer(br.readLine());
         int id = Integer.parseInt(st.nextToken());
         int w = Integer.parseInt(st.nextToken());
               
         if(cntPq.isEmpty()) {
            info item = pq.poll();
            answer+=(long)(item.id)*(cnt++);
            cntPq.add(new casher(item.k, item.w));
            
            while(!pq.isEmpty() && item.w==pq.peek().w) {
               item = pq.poll();
               answer+=(long)(item.id)*(cnt++);
               cntPq.add(new casher(item.k, item.w));
            }
         }
         casher c=cntPq.poll();
         pq.add(new info(id,c.time+w,c.idx));
      }
      
      while(!pq.isEmpty()) {
         info item = pq.poll();
         answer+=(long)(item.id)*(cnt++);      
      }
      
      System.out.println(answer);
   }
}