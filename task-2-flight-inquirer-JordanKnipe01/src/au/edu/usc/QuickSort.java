package au.edu.usc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;

public class QuickSort {
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
       quickSort(finalList, 1, finalList.length - 1);
       System.out.println("Your Requested Element in the list is: ");
       return finalList[rank];
   }
    public static void quickSort(int array[], int low, int high) {
        if (low < high) {

            // find pivot element making elements less than pivot on left and greater on right
            int pivotElement = partition(array, low, high);

            // call recursive func on left element
            quickSort(array, low, pivotElement - 1);

            // call recursive func on right element
            quickSort(array, pivotElement + 1, high);
        }
    }

    public static int partition(int array[], int low, int high) {

        //Store the rightmost element
        int pivotElem = array[high];

        //greatest element
        int greatestElement = (low - 1);

        // iterate through elements and
        // compare each element with pivot
        for (int lowestElement = low; lowestElement < high; lowestElement++) {
            if (array[lowestElement] <= pivotElem) {

                // if element smaller than pivotElement is found
                // swap it with the greatest
                greatestElement++;

                // swapping element greatestElement with lowestElement
                int temp = array[greatestElement];
                array[greatestElement] = array[lowestElement];
                array[lowestElement] = temp;
            }

        }
        // swapt the pivot element position with the greatestElement
        int temp = array[greatestElement + 1];
        array[greatestElement + 1] = array[high];
        array[high] = temp;

        // return the position from where partition is done
        return (greatestElement + 1);
    }
}
