package com.fileprocess.threads;

import java.io.File;
import java.text.ParseException;

import com.fileprocess.helper.FileHelper;

public class FileProcessCheck extends FileHelper implements Runnable {

	@Override
	public void run() {
			File listOfFiles[] = getListOfFiles("D:/Ankush Zod/inputfiles");
			
			if(listOfFiles.length == 0){
				System.out.println("----Empty Directory----");
				return;
			}
			
			for(File f : listOfFiles){
				try {
					if(!isValid(f) || isDuplicate(f) || !isOnTime(f)){
						deleteFile(f);
						System.out.println(f.getName() + " is deleted.");
					}
					
					else{
						
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}
