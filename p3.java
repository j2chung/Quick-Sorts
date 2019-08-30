package cs21as03;
import java.util.Scanner;
import java.util.ArrayList;

public class p3 {

    public static void main(String[] args) {
        Scanner stdin = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<Integer>();
        while(stdin.hasNextInt() && stdin.hasNextLine()) {
            arr.add(stdin.nextInt());
        }
        long startTime = System.nanoTime();
        if (args.length > 0 && args[0].equals("-h")) {
            hoareQuicksort(arr, 0, arr.size() - 1);
        } else {
            quicksort(arr, 0, arr.size() - 1);
        }
        for(int i = 0; i < arr.size(); i++) {
            System.out.println(arr.get(i));
        }
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }

    public static void quicksort(ArrayList<Integer> A, int p ,int r) {
        //p is left index, r is right index
        if (p < r) {
            int q = partition(A, p, r);
            quicksort(A, p, q - 1);
            quicksort(A, q + 1, r);
        }
    }

    public static void hoareQuicksort(ArrayList<Integer> A, int p, int r) {
        if (p < r) {
            int q = hoarePartition(A, p, r);
            hoareQuicksort(A, p, q);
            hoareQuicksort(A, q + 1, r);
        }
    }

    public static int partition(ArrayList<Integer> A, int p, int r) {
        medianOfThree(A, p, r); //calling median of three from first and last index
        int i = p - 1; //index of lower range
        for (int j = p; j < r; j++) {
            if (A.get(j) <= A.get(r)) {
                i++;
                swap(A, i, j);
            }
        }
        swap (A, i + 1, r);
        return i + 1;
    }

    public static int hoarePartition(ArrayList<Integer> A, int p, int r) {
        medianOfThreeHoare(A, p, r);
        int x = A.get(p);
        int i = p - 1;
        int j = r + 1;
        while (true) {
            do { i = i + 1; } while (A.get(i) < x);
            do { j = j - 1; } while (A.get(j) > x);
            if (i >= j) {
                return j;
            }
            swap(A, i, j);
        }
    }

    public static void swap(ArrayList<Integer> A, int i, int k) {
        int temp;
        temp = A.get(i);
        A.set(i, A.get(k));
        A.set(k, temp);
    }

    public static void medianOfThree(ArrayList<Integer> A, int p, int r) {
        int mid = p + (r - p) / 2; //index of median
        if (((A.get(p) - A.get(mid)) * (A.get(mid) - A.get(r))) > 0) {
            swap(A, mid, r);
        } else if (((A.get(r) - A.get(p)) * (A.get(p) - A.get(mid))) > 0) {
            swap (A, p, r);
        }
    }

    public static void medianOfThreeHoare(ArrayList<Integer> A, int p, int r) {
        int mid = p + (r - p) / 2; //index of median
        if (((A.get(p) - A.get(mid)) * (A.get(mid) - A.get(r))) > 0) {
            swap(A, mid, p);
        } else if (((A.get(p) - A.get(r)) * (A.get(r) - A.get(mid))) > 0) {
            swap (A, r, p);
        }
    }
}
