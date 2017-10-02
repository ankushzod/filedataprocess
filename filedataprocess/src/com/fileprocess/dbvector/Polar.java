package com.fileprocess.dbvector;

import java.io.File;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Polar implements Callable{
	public Object call()throws Exception{  
		File source = new File("D:/source");
		File[] listOfFilesForSourceFolder = source.listFiles();
		java.util.concurrent.Future f = null;
		for (File file : listOfFilesForSourceFolder) {
		    if (file.isFile()) {
		        if (!isFileValid(file.getName()) || isFileDuplicate(file.getName()) || !isFileOnTime(file)) {
		        	file.delete();
				}
		        else{
		    	    try {
		    	    	File movedFile=new File("D:/destination/"+file.getName());
		    	    	file.renameTo(movedFile);
		    	    	ExecutorService executor = Executors.newFixedThreadPool(3);
		    			File destination = new File("D:/destination");
		    			File[] listOfFilesForDestinationFolder = destination.listFiles();
		    			for (File destinationFile : listOfFilesForDestinationFolder) {
		    	            //Callable workerThreadForReadingFile = new FileReaderClass(destinationFile);
		    	            f = executor.submit(new FileReaderClass(destinationFile));
		    	            System.out.println(f.get());
		    			}  
		    	        executor.shutdown();
/*		    	        while (!executor.isTerminated()) {
		    	        }
		    	        System.out.println("Finished all threads");*/
		    	    }catch(Exception ex) {
		    	    	System.out.println("Unable to copy file:"+ex.getMessage());
		    	    }
		        }
	     }
	}
		return f;
}	
	public boolean isFileValid(String filename){
		Map<String, String> map=ApplicationContext.map;
		  Iterator it = map.entrySet().iterator();
		  	   while (it.hasNext()) {
		  	    	Map.Entry pair = (Map.Entry)it.next();
		  	    	if(filename.equals(pair.getKey())){
		  	    		System.out.println("File "+filename+" is a valid file");
		  	    		return true;
		  			}
		  	   }
		System.out.println("File "+filename+" is a invalid file");
		return false;	
	}
	
	public boolean isFileDuplicate(String filename){
		File destination = new File("D:/destination");
		File[] listOfFiles = destination.listFiles();
		Map<String,String> processedFilesMap = new HashMap<String,String>();
		for (File file1 : listOfFiles) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			processedFilesMap.put(file1.getName(), sdf.format(file1.lastModified()));
		}
		
		if(processedFilesMap.containsKey(filename)){
			String processedFileDate=processedFilesMap.get(filename);
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date date = new Date();
			String fileCurrentDate= sdf.format(date);
			if(processedFileDate.equals(fileCurrentDate)){
				System.out.println("File "+filename+" is a duplicate file");
				return true;
			}
		}
		System.out.println("File "+filename+" is not a duplicate file");
		return false;	
	}
	
	public boolean isFileOnTime(File file){
		if(isFileValid(file.getName())){
			Map<String, String> map=ApplicationContext.map;
			Date date;
			try {
				String expectedTime=map.get(file.getName());
				SimpleDateFormat simpleDateFormatForExpectedTime = new SimpleDateFormat("HH:mm:ss");
				date = simpleDateFormatForExpectedTime.parse(expectedTime);
				String expectedDate = simpleDateFormatForExpectedTime.format(date);
				String[] tokens = expectedDate.split(":");
				int secondsToMs = Integer.parseInt(tokens[2]) * 1000;
				int minutesToMs = Integer.parseInt(tokens[1]) * 60000;
				int hoursToMs = Integer.parseInt(tokens[0]) * 3600000;
				long expectedDateInMilli = secondsToMs + minutesToMs + hoursToMs;
				
/*				Date receivedTime = new Date(file.lastModified()); 
				System.out.println(file.lastModified());*/
				SimpleDateFormat simpleDateFormatForReceivedTime = new SimpleDateFormat("HH:mm:ss");  
				String ReceivedDateString = simpleDateFormatForReceivedTime.format(file.lastModified()); 
				System.out.println(ReceivedDateString);
				String[] tokens1 = ReceivedDateString.split(":");
				int secondsToMs1 = Integer.parseInt(tokens1[2]) * 1000;
				int minutesToMs1 = Integer.parseInt(tokens1[1]) * 60000;
				int hoursToMs1 = Integer.parseInt(tokens1[0]) * 3600000;
				long receivedDateInMilli = secondsToMs1 + minutesToMs1 + hoursToMs1;
				System.out.println(receivedDateInMilli);
				
				Long timeDifference=expectedDateInMilli-receivedDateInMilli;
				if(timeDifference > 0){
		        	System.out.println(file.getName() + " is received on time.");
		        	return true;
		        }else{
		        	System.out.println(file.getName() + " is not received on time.");
		        }
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return false;	
	}
}
