package au.edu.usc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class InsertionSort {
    public static void main(String[] args) {


        System.out.println(get(4997));
    }

    public static int get(int rank){
        //Variables
        ArrayList<Integer> passengerLoadFactor = new ArrayList<>();
        String dataPath = "dataset/dom_citypairs_web.csv";
        String splitChar = "";
        BufferedReader bufferReader;
        int iterationCount = 0;
        try {
            bufferReader = new BufferedReader(new FileReader(dataPath));
            System.out.println("Found Useable Dataset");

            while ((splitChar = bufferReader.readLine()) != null) {
                String[] values = splitChar.split(",");
                if (iterationCount != 0) {
                    float newItem = Float.parseFloat(values[5]) * 10f;
                    int intNewItem = Math.round(newItem);
                    passengerLoadFactor.add(intNewItem);
                }
                iterationCount++;
            }
        }
        catch (IOException e) {
            System.out.println("Element Not Found");
            e.printStackTrace();
        }

        int finalList[] = new int[passengerLoadFactor.size()];
        System.out.println(finalList.length + " Elements in dataset");
        Iterator<Integer> itr = passengerLoadFactor.iterator();

        for (int element = 0; itr.hasNext(); element++) {
            finalList[element] = itr.next();
        }
        //Run Sorting Algorithm
        insertionsort(finalList);
        System.out.println("Your Requested Element in the list is: ");
        return finalList[rank];
    }
    public static void insertionsort(int arr[])
    {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }
}
