package com.fileprocess.worker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WorkerThread {

	   public static void main(String[] args) {

	        String csvFile = "D:/Ankush Zod/proceessed_folder/a.csv";
	        BufferedReader br = null;
	        String line = "";
	        String cvsSplitBy = ",";

	        try {

	            br = new BufferedReader(new FileReader(csvFile));
	            while ((line = br.readLine()) != null) {

	              
	                String[] country = line.split(cvsSplitBy);

	                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

	            }

	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            if (br != null) {
	                try {
	                    br.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }

	    }

	

}
