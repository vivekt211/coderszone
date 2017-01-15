package com.coderszone.common.key.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.coderszone.common.key.KeyGenService;


@Service
public class KeyGenServiceImpl implements KeyGenService{

	private static char[] symbols=null;
	private final Random random = new Random();
	private char[] buf;
	
	@Override
	public String generateNewKeys() {
		StringBuilder tmp = new StringBuilder();
	    for (char ch = '0'; ch <= '9'; ++ch)
	      tmp.append(ch);
	    for (char ch = 'a'; ch <= 'z'; ++ch)
	      tmp.append(ch);
	    symbols = tmp.toString().toCharArray();
		 buf = new char[6];
		 for(int idx = 0; idx < 6; ++idx) 
		   buf[idx] = symbols[random.nextInt(symbols.length)];
		// DateFormat df=new SimpleDateFormat("yyMMdd");
		 //String date=df.format(new Date());
		/* for(int idx = 0; idx < 6; ++idx) 
			 buf[idx] =date.charAt(idx);
		 */
		    return new String(buf);
	}
	
 public static void main(String[] args) {
	KeyGenService keygen=new KeyGenServiceImpl();
	System.out.println(keygen.generateNewKeys());
}

}
