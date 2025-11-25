import java.util.*;

public class BOJ_2872 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        // 우측부터 가장 큰수로 시작되는 연속된 내림차순 부분 수열을 찾으면 됨
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(sc.nextInt());
        }
        int expected = n;
        for (int i = n - 1; i >= 0; i--) {
            if( list.get(i) == expected) {
                expected--;
            }
        }
        System.out.println(expected);
    }
}