package com.fileprocess.service;

import com.fileprocess.helper.ApplicationContext;
import com.fileprocess.threads.FileProcessCheck;

public class App {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = null;
		
		try {
			applicationContext = ApplicationContext.loadfileProcessContext();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	
		Thread processingThread = new Thread(new FileProcessCheck());
		processingThread.run();
	}

}
