package au.edu.usc;

import javax.swing .*;
import java.io .*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ImportExportData {

    public static void main(String[] args) {
        System.out.println("Starter code!");

        ArrayList<Integer> passengerLoadFactor = new ArrayList<>();


        String path = "dataset/dom_citypairs_web.csv";
        String line = "";
        BufferedReader br;
        List<String[]> resultList = new ArrayList<String[]>();
        int iterationCount = 0;
        {
            try {
                br = new BufferedReader(new FileReader(path));
                System.out.println("FoundDataset");

                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    resultList.add(values);
                    System.out.println(values[10] + " " + values[11]);
                    try {
                        FileWriter fw = new FileWriter("dataset/itenerary202012.csv", true);
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter pw = new PrintWriter(bw);
                        StringBuilder sb = new StringBuilder();
                        //System.out.println(values[10].getClass());

                        if (values[10].equals("2020") && values[11].equals("12")) {
                            pw.println(values[0] + "," +
                                    values[1] + "," + values[2] + "," +
                                    values[3] + "," + values[4] + "," +
                                    values[5] + "," + values[6] + "," +
                                    values[7] + "," + values[8] + "," +
                                    values[9] + "," + values[10] + "," +
                                    values[11]);


                        }
                        pw.write(sb.toString());
                        pw.flush();
                        pw.close();


                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, "Record Not Saved");
                    }

                }
            } catch (FileNotFoundException e) {
                System.out.println("NotFound");
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

