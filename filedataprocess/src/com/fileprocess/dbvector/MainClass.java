package com.fileprocess.dbvector;

import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MainClass {
	public static void main(String[] arg){
		ApplicationContext.xmlParser();
		Polar polar = new Polar();
/*		Thread t1 =new Thread(polar);  
		t1.start();*/
		ExecutorService executor = Executors.newFixedThreadPool(1);
        Future f = executor.submit(polar);
		try {
			System.out.println(f.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		executor.shutdown();
		Vector<FileReaderDTO> validRecord = FileReaderClass.validRecord; 
		Vector<FileReaderDTO> invalidRecord = FileReaderClass.invalidRecord;
		System.out.println("Valid Record in Vector :: "+validRecord);
    	System.out.println("Invalid Record in Vector :: "+invalidRecord);
	}
}
	