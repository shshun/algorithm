?
1
2
3
4
5
6
7
8
9
10
11
12
13
14
15
16
17
18
19
20
21
22
23
24
25
26
27
28
29
30
31
32
33
34
35
36
37
38
39
40
41
42
43
44
45
46
47
48
49
50
51
52
53
54
55
56
57
58
59
60
/**************************************************************
    Problem: 1205
    User: crosshatch900
    Language: Java
    Result: Success
    Time:284 ms
    Memory:20676 kb
****************************************************************/
 
 
import java.awt.Point;
import java.io.*;
import java.util.*;
 
public class Main {
 
    static int joker,N,min,max,answer;
    static TreeSet<Integer> treeSet;
    static List<Integer> arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         
        joker = 0;
        min = Integer.MAX_VALUE;
        max = 0;
        N = Integer.parseInt(br.readLine());
        treeSet =new TreeSet<>();
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
         
        for(int i=0;i<N;i++) {
            int num = Integer.parseInt(st.nextToken());
            if(num==0) joker++;
            else {
                min = Math.min(num,min);
                max = Math.max(num,max);
                treeSet.add(num);
            }
        }
         
        if(treeSet.size()==0) {
            System.out.println(joker);
            return;
        }
         
        for(int a : treeSet) {
            dfs(a,joker,1);
        }
        System.out.println(answer);
    }
    private static void dfs(int num, int chance, int i) {
        answer = Math.max(i, answer);
        if(treeSet.contains(num+1)) dfs(num+1,chance,i+1);
        else if(chance>0) {
            dfs(num+1,chance-1,i+1);
        }
         
    }
 
 
}