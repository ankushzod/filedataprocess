package com.fileprocess.dbvector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UtilityHelper {
	private static final String dateFormat = "dd/MM/yyyy";
	
	public static Date convertStringToDate(String strDate) throws ParseException{
		Date date = null;
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		if(strDate != null && strDate.length() > 0){
			date = sdf.parse(strDate);
		}
		return date;
	}
	
}
