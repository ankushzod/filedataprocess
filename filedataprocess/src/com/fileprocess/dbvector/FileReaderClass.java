package com.fileprocess.dbvector;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.concurrent.Callable;

public class FileReaderClass implements Callable{
private File file;
public static Vector<FileReaderDTO> validRecord = new Vector<FileReaderDTO>();
public static Vector<FileReaderDTO> invalidRecord = new Vector<FileReaderDTO>();
    public FileReaderClass(File file){
        this.file=file;
    }

    public Object call() throws Exception{
    	System.out.println("job started by thread"+Thread.currentThread().getName());
    	
        String line = "";
        String SplitBy = ",";

        //try (BufferedReader br = new BufferedReader(new FileReader("D:/destination/"+file.getName()))) {
        BufferedReader br = new BufferedReader(new FileReader("D:/destination/"+file.getName()));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] fileDetails = line.split(SplitBy);

                System.out.println("fileDetailsLineByLine [srNo= " + fileDetails[0] + " , firstName=" + fileDetails[1] + 
                		", lastName=" + fileDetails[2] + ", birthDate= " + fileDetails[3] + ", deathDate= " + fileDetails[4] + 
                		", loanAmnt= " + fileDetails[5] + "]");
                
                FileReaderDTO fileReaderdto=new FileReaderDTO(Integer.parseInt(fileDetails[0]), fileDetails[1], fileDetails[2], fileDetails[3], 
                		fileDetails[4], Integer.parseInt(fileDetails[5]));
                
                //processCommand();
                //dateOfBirthCheck(fileReaderdto);
                //dateOfDeathCheck(fileReaderdto);
                if(dateOfBirthCheck(fileReaderdto) && !dateOfDeathCheck(fileReaderdto)){
                	validRecord.addElement(fileReaderdto);

                }else{
                    if(!dateOfBirthCheck(fileReaderdto) && !dateOfDeathCheck(fileReaderdto) 
                    		|| dateOfBirthCheck(fileReaderdto) && dateOfDeathCheck(fileReaderdto) 
                    		|| !dateOfBirthCheck(fileReaderdto) && dateOfDeathCheck(fileReaderdto)){
                    	invalidRecord.addElement(fileReaderdto);
                    }
                }
/*            	System.out.println("Valid Record in Vector :: "+validRecord);
            	System.out.println("Invalid Record in Vector :: "+invalidRecord);*/
            }

/*        } catch (IOException e) {
            e.printStackTrace();
        }*/
    	System.out.println("job completed by thread"+Thread.currentThread().getName());
		return validRecord;
    }

    private void processCommand() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    public boolean dateOfBirthCheck(FileReaderDTO fileReaderdto){
    	if (!(fileReaderdto.getBirthDate()).equals("")) {
    		//System.out.println("Date of Birth Present");
			return true;
		}
    	System.out.println("Date of Birth is mandatory");
    	return false;
    }
    
    public boolean dateOfDeathCheck(FileReaderDTO fileReaderdto){
		Date birthDate;
		Date deathDate;
		try {
	    	if (!(fileReaderdto.getDeathDate()).equals("")) {
				birthDate = UtilityHelper.convertStringToDate(fileReaderdto.getBirthDate());
				deathDate = UtilityHelper.convertStringToDate(fileReaderdto.getDeathDate());
	    		if(birthDate.after(deathDate)){
	    			System.out.println("Invalid Date of Death : "+fileReaderdto.toString());
					return true;
	    		}
/*	    		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
	    		Date journeyDate = df.parse(fileReaderdto.getBirthDate());
	    		System.out.println(journeyDate);
	    		
	    		DateFormat df1 = new SimpleDateFormat("dd/MM/yyyy");
	    		Date journeyDate1 = df1.parse(fileReaderdto.getDeathDate());
	    		System.out.println(journeyDate1);
	    		if(journeyDate.after(journeyDate1)){
	    			System.out.println("Invalid Record : "+fileReaderdto.toString());
					return true;
	    		}*/
	    		
			}
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
    	return false;
    }
}
