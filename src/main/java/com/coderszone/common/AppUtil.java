package com.coderszone.common;

public class AppUtil {

	public static String ArrayToCSV(int[] array){
		if (array.length > 0) {
		    StringBuilder nameBuilder = new StringBuilder();

		    for (int n : array) {
		        nameBuilder.append(n).append(",");
		    }

		    nameBuilder.deleteCharAt(nameBuilder.length() - 1);

		    return nameBuilder.toString();
		} else {
		    return "";
		}
	}
	
	public static int[] CSVToArray(String array){
		String[] in=array.split(",");
		int[] ret=new int[in.length];
		for (int i=0;i<in.length;i++){
			ret[i] = Integer.parseInt(in[i]);
	      }
		return ret;
	}
}
