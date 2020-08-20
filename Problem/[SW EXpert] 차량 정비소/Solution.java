import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
 
public class Solution {
    private static int N,M,K,KA,KB,number,count;
    private static int[] receipt, repair;
    private static boolean[] receiptSelect, repairSelect;
    private static List<Customer> arrivalList, receiptList, repairList, endList;
     
    public static void main(String[] args) throws NumberFormatException, IOException {
         BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
         int TC = Integer.parseInt(br.readLine());
              
         for(int t=1;t<=TC;++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            KA = Integer.parseInt(st.nextToken());
            KB = Integer.parseInt(st.nextToken());
              
            number =1;
            receipt = new int[N+1]; repair = new int[M+1];
            receiptSelect = new boolean[N+1]; repairSelect = new boolean[M+1];
            arrivalList = new LinkedList<>(); receiptList = new LinkedList<>(); 
            repairList = new LinkedList<>(); endList = new LinkedList<>();
             
            st = new StringTokenizer(br.readLine());    // 접수 창구 입력받기
            for (int i = 1; i <= N; i++) {
                receipt[i] = Integer.parseInt(st.nextToken());
            }
 
            st = new StringTokenizer(br.readLine());    // 정비 창구 입력받기
            for (int i = 1; i <= M; i++) {
                repair[i] = Integer.parseInt(st.nextToken());
            }
             
            st = new StringTokenizer(br.readLine());    // 고객 입력 받기
            for (int i = 0; i < K; i++) {
                int time = Integer.parseInt(st.nextToken());
                arrivalList.add(new Customer(time)); // 오고 있는 손님 list에 저장
            }
             
            count =0;
            while(endList.size()<K) {
                arrive();
                repairProcess();
                Collections.sort(repairList, new ComparatorCustomer());
                inRepair();
                Collections.sort(receiptList, new ComparatorCustomer2());
                inReceipt();
                receiptProcess();
                count++;
            }
             
            boolean result = false;
            int sum =0;
            for (int i = 0; i < endList.size(); i++) {
                Customer ct = endList.get(i);
                if (ct.A == KA) {
                    if(ct.B == KB) {
                        sum += ct.cusNum;
                        result = true;
                    }
                }
            }
             
            StringBuilder sb = new StringBuilder();
            sb.append("#").append(t).append(" ");
             
            if (!result) sb.append(-1); 
            else sb.append(sum);
             
            System.out.println(sb.toString());
         }
    }
 
    private static void repairProcess() {
        for (int i = 0; i < repairList.size(); i++) {
            Customer ct = repairList.get(i);
            ct.time -= 1;
            if (ct.time == 0) {
                ct.check = false;
                repairSelect[ct.B] = false;
                endList.add(ct);
                repairList.remove(i);
                i--;
            }
        }
    }
 
    private static void inRepair() {
        for (int i = 0; i < repairList.size(); i++) {
            Customer ct = repairList.get(i);
            if (!ct.check) {
                for (int j = 1; j <= M; j++) {
                    if (repairSelect[j]) continue;
 
                    ct.B = j;
                    ct.check = true;
                    repairSelect[j] = true;
                    ct.time = repair[j];
                    break;
                }
            }
        }
    }
 
    private static void receiptProcess() {
        for (int i = 0; i < receiptList.size(); i++) {
            Customer ct = receiptList.get(i);
            ct.time -= 1;
            if (ct.time == 0) {
                ct.check = false;
                receiptSelect[ct.A] = false;
                repairList.add(ct);
                receiptList.remove(i);
                i--;
            }
        }
    }
 
    private static void inReceipt() {
        for (int i = 0; i < receiptList.size(); i++) {
            Customer ct = receiptList.get(i);
            if (!ct.check) {
                for(int j =1; j<=N; j++) {
                    if (receiptSelect[j]) continue;
                     
                    ct.A = j;
                    ct.check = true;
                    receiptSelect[j] = true;
                    ct.time = receipt[j];
                    break;
                }
            }
        }
    }
 
    private static void arrive() {
        for (int i = 0; i < arrivalList.size(); i++) {
            Customer ct = arrivalList.get(i);
            ct.time -= 1;
            if (ct.time < 0) {
                ct.cusNum = number++;
                receiptList.add(ct);
                arrivalList.remove(i);
                i--;
            }
        }
    }
 
}
 
class ComparatorCustomer implements Comparator<Customer>{
 
    @Override
    public int compare(Customer arg0, Customer arg1) {
        if (arg0.time == arg1.time) return arg0.A - arg1.A;
        return arg0.time - arg1.time;
    }
}
 
class ComparatorCustomer2 implements Comparator<Customer>{
 
    @Override
    public int compare(Customer arg0, Customer arg1) {
        return arg0.cusNum - arg1.cusNum;
    }
}
 
class Customer {
    int A;
    int B;
    int time;
    int cusNum;
    boolean check;
     
    public Customer (int time) {
        this.time = time;
    }
}