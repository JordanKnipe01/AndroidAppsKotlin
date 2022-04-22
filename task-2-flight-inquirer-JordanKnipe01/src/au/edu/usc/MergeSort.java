package au.edu.usc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class MergeSort {
    public static void main(String[] args) {
        //Run Get Method
        //Finds 4997th element in the sorted array
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
        mergeSort(finalList, finalList.length);

        System.out.println("Your Requested Element in the list is: ");
        return finalList[rank];
    }
    public static void merge(int[] left_arr,int[] right_arr, int[] arr,int left_size, int right_size){

        int i=0,left=0,right = 0;
        //The while loops check the conditions for merging
        while(left<left_size && right<right_size){

            if(left_arr[left]<right_arr[right]){
                arr[i++] = left_arr[left++];
            }
            else{
                arr[i++] = right_arr[right++];
            }
        }
        while(left<left_size){
            arr[i++] = left_arr[left++];
        }
        while(left<right_size){
            arr[i++] = right_arr[right++];
        }
    }

    public static void mergeSort(int [] arr, int len){
        if (len < 2){return;}

        int mid = len / 2;
        int [] left_arr = new int[mid];
        int [] right_arr = new int[len-mid];

        //Divide array into two
        int k = 0;
        for(int i = 0;i<len;++i){
            if(i<mid){
                left_arr[i] = arr[i];
            }
            else{
                right_arr[k] = arr[i];
                k = k+1;
            }
        }
        // Recursively call the function to divide arrays even further
        mergeSort(left_arr,mid);
        mergeSort(right_arr,len-mid);

        //Merge every array
        merge(left_arr,right_arr,arr,mid,len-mid);
    }
}
