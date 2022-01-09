/*
Student Alexey Vartanov
ID: 321641086
Maman 15 - Question 1 - Parallel MergeSort
 */
import java.util.ArrayList;

/**
 * Thread for Merge Sort
 * get stock of the arrays,
 * pull out two members from stock, join them into one array
 * and run typical merge sort algorithm
 * return merged and sorted array back into the stock
 * til the stock will have only one array
 */
public class SortThread extends Thread{

    private Stock s;
    public SortThread(Stock s){
        this.s = s;
    }

    @Override
    public void run() {
        ArrayList<int[]> pairOfArrays;
        try {
            while (s.getStockSize() > 1) {
                pairOfArrays = s.GetPairOfMembersFromStock();
                if (pairOfArrays != null) {
                    int[] newSortedSubArray = SortAndMergeArrays(pairOfArrays);
                    s.AddMemberToStock(newSortedSubArray);
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Join two arrays into one
     * @param a: first array
     * @param b: second array
     * @return joined array
     */
    private int[] JoinArrays(int[] a, int[] b){
        int[] joined = new int[a.length+b.length];
        int i;
        for (i=0; i<a.length; i++){
            joined[i] = a[i];
        }
        for (i=0; i<b.length; i++){
            joined[a.length+i] = b[i];
        }
        return joined;
    }

    /**
     * Join the Arrays we got from stock and sort and merge it by typical
     * MergeSort algo (from JAVA book)
     * @param pairOfArrays: array list we got from stock
     * @return one sorted array, created from two sub arrays
     */
    private int[] SortAndMergeArrays(ArrayList<int[]> pairOfArrays) {
        int[] ar = JoinArrays(pairOfArrays.get(0), pairOfArrays.get(1));
        mergeSort(ar, 0, ar.length - 1);
        return ar;
    }

    private void mergeSort(int[] a, int beg, int end)
    {
        if (beg < end) {
            int mid = (beg + end) / 2;
            mergeSort(a, beg, mid);
            mergeSort(a, mid + 1, end);
            merge(a, beg, mid, end);
        }
    }
    private void merge(int[] a, int beg, int mid, int end)
    {
        int i, j, k;
        int n1 = mid - beg + 1;
        int n2 = end - mid;

        /* temporary Arrays */
        int[] LeftArray = new int[n1];
        int[] RightArray = new int[n2];

        /* copy data to temp arrays */
        for (i = 0; i < n1; i++)
            LeftArray[i] = a[beg + i];
        for (j = 0; j < n2; j++)
            RightArray[j] = a[mid + 1 + j];

        i = 0; /* initial index of first sub-array */
        j = 0; /* initial index of second sub-array */
        k = beg;  /* initial index of merged sub-array */

        while (i < n1 && j < n2)
        {
            if(LeftArray[i] <= RightArray[j])
            {
                a[k] = LeftArray[i];
                i++;
            }
            else
            {
                a[k] = RightArray[j];
                j++;
            }
            k++;
        }
        while (i<n1)
        {
            a[k] = LeftArray[i];
            i++;
            k++;
        }

        while (j<n2)
        {
            a[k] = RightArray[j];
            j++;
            k++;
        }
    }
}
