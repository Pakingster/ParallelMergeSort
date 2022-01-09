/*
Student Alexey Vartanov
ID: 321641086
Maman 15 - Question 1 - Parallel MergeSort
 */
import java.util.ArrayList;

/**
 * Stock class - separating the numeric array to arrays with one value in each new array
 * from the original array, implemented as ArrayList
 */
public class Stock {

    private final ArrayList<int[]> arrayList;

    public int getStockSize() {
        return arrayList.size();
    }

    public Stock(int[] arr) {
        arrayList = new ArrayList<>();
        for (int a : arr) {
            arrayList.add(new int[]{a});
        }
    }

    /**
     * synchronized function to get a pair of the arrays from stock
     * getting both members at one time to take them as atomic methode
     * @return pair of arrays from the stock as array of arrays
     */
    public synchronized ArrayList<int[]> GetPairOfMembersFromStock() {
        ArrayList<int[]> members = null;
        if (arrayList.size() > 1) {
            members = new ArrayList<>();
            members.add(GetMemberFromStock());
            members.add(GetMemberFromStock());
        }
        return members;
    }

    /**
     * get member and remove it from the stock
     * @return member (as int array)
     */
    private int[] GetMemberFromStock(){
        int[] member = arrayList.get(0);
        arrayList.remove(0);
        return member;
    }

    public synchronized void AddMemberToStock(int[] member) {
        arrayList.add(arrayList.size(), member);
    }

    public void PrintStockMembers() {
        for (int[] arr : arrayList) {
            for (int j : arr) {
                System.out.print(j + " ");
            }
            System.out.println();
        }
    }
}
