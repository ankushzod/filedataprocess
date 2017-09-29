package com.fileprocess.helper;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Map;

public class FileHelper {
	Map<String, String> tempMap = null;
	
	public static File[] getListOfFiles(String path){
		File[] listOfFiles = null;
		
		File folder = new File(path);
		listOfFiles = folder.listFiles();
		
		return listOfFiles;
	}
	
	public boolean deleteFile(File f){
		return f.delete();
	}
	
	public boolean isValid(File f){
		tempMap = ApplicationContext.filePropertyMap;
		if(tempMap != null && tempMap.containsKey(f.getName())){
			return true;
		}else{
			System.out.println(f.getName() + " is invalid file.");
		}
		return false;
	}
	
	public boolean isDuplicate(File f) throws ParseException{
		tempMap = ApplicationContext.processedFileMap;
		if(tempMap != null && tempMap.containsKey(f.getName())){
			String processeddate = tempMap.get(f.getName());
			String currentDate = new Date().toString();
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
			if((sdf.parse(processeddate)).equals(currentDate)){
				System.out.println(f.getName() + " has been already processed.");
				return true;
			}
		}
		return false;
	}
	
	public boolean isOnTime(File f) throws ParseException{
		tempMap = ApplicationContext.filePropertyMap;
		
		if(isValid(f)){
			String expectedTime = tempMap.get(f.getName());
			Long receivedTime = f.lastModified();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm:ss");
	        Date expectedTime1 = dateFormat.parse(expectedTime);
			
	        
	        Calendar expectedCalTime = new GregorianCalendar();
	        Calendar receivedCalTime = new GregorianCalendar();
	        expectedCalTime.setTime(expectedTime1);
	        receivedCalTime.setTimeInMillis(receivedTime);
	        
	        Long timeDiff = expectedCalTime.getTimeInMillis() - receivedCalTime.getTimeInMillis();
	        
	        if(timeDiff > 0){
	        	System.out.println(f.getName() + " is received on time.");
	        	return true;
	        }else{
	        	System.out.println(f.getName() + " is not received on time.");
	        }
		}
		return false;
	}
}
