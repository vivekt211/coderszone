package com.coderszone.common;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateUtil {
	
	private static DateFormat ft=new SimpleDateFormat("yyyy/MM/dd HH:mm");
	static{
		ft.setTimeZone(TimeZone.getTimeZone("Asia/Calcutta"));
	}
	public static java.util.Date sqlToUtil(java.sql.Date dt){
		if(dt!=null){
			java.util.Date newDate = new java.util.Date(dt.getTime());
			return newDate;
		}else{
			return null;
		}
		
	}
	public static Timestamp utilToSql(java.util.Date dt){
		java.sql.Timestamp newDate = new java.sql.Timestamp(dt.getTime());
		return newDate;
	}
	
	public static String sqlToString(java.sql.Date dt){
		if(dt!=null){
			java.util.Date newDate = new java.util.Date(dt.getTime());
			return ft.format(newDate);
		}else{
			return null;
		}
		
	}
	public static DateFormat getDefaultDateFormat(){
		return ft;
	}
	
	public static Timestamp stringToSql(String dt){
		if(dt!=null){
			java.util.Date dte=null;
			try {
				dte= ft.parse(dt);
				java.sql.Timestamp newDate = new java.sql.Timestamp(dte.getTime());
				return newDate;
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}else{
			return null;
		}
		
	}
	public static int compareTimes(Date d1, Date d2)
	{
	    int     t1;
	    int     t2;
	    t1 = (int) (d1.getTime() % (24*60*60*1000L));
	    t2 = (int) (d2.getTime() % (24*60*60*1000L));
	    return (t1 - t2);
	}
	public static int compareDates(Date date1, Date date) {
		System.out.println(getDefaultDateFormat().format(date1)+","+getDefaultDateFormat().format(date));
		System.out.println( date1.compareTo(date));
		return date1.compareTo(date);
	}
	public static String timeStampToString(Timestamp timestamp) {
		 long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
		 DateFormat dateFormat;
		if(timestamp!=null){
			return ft.format(new java.util.Date(milliseconds));
		}else{
			return null;
		}
	}
	public static String utilToString(Date date) {
		if(date!=null){
			return ft.format(date);
		}else{
			return null;
		}
	}
	
}
