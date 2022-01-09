/*
Student Alexey Vartanov
ID: 321641086
Maman 15 - Question 1 - Parallel MergeSort
 */
import java.util.Random;
import java.util.Scanner;

public class ParallelMergeSort {

    public static void main(String [] args) throws InterruptedException {
        System.out.println("Please enter the number of the members in expected array\narray will contain values 1..100" +
                " (values can be repeated)");
        Scanner sc = new Scanner(System.in);
        int numberOfMembers = sc.nextInt();
        System.out.println("Please enter the number of threads to sort the array");
        int numberOfThreads = sc.nextInt();

        int[]arr = CreateArrWithRandomValues(numberOfMembers);
        Stock stock = new Stock(arr);

        System.out.printf("Array will be sorted by %d threads\n", numberOfThreads);
        System.out.println("Array with random values before sorting:");
        printArray(arr);

        SortThread[] threads = new SortThread[numberOfThreads];
        for(int i=0; i<numberOfThreads; i++){
            threads[i] = new SortThread(stock);
            threads[i].start();
        }
        for (SortThread thread : threads) thread.join();
        System.out.printf("Array after was sorted by %d threads:\n", numberOfThreads);
        stock.PrintStockMembers();
    }

    private static int[] CreateArrWithRandomValues(int length){
        int[]arr = new int[length];
        Random rand = new Random();
        for (int i=0; i<arr.length; i++){
            arr[i] = rand.nextInt(100);
        }
        return arr;
    }

    private static void printArray(int[]arr){
        for(int a: arr){
            System.out.print(a + " ");
        }
        System.out.println();
    }
}
