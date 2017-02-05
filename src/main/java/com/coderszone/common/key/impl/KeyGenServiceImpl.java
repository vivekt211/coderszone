package com.coderszone.common.key.impl;

import java.util.Random;

import org.springframework.stereotype.Service;
import com.coderszone.common.key.KeyGenService;


@Service
public class KeyGenServiceImpl implements KeyGenService{
	
	@Override
	public String generateNewKeys() {
		StringBuilder tmp = new StringBuilder();
	    for (char ch = '0'; ch <= '9'; ++ch)
	      tmp.append(ch);
	    for (char ch = 'a'; ch <= 'z'; ++ch)
	      tmp.append(ch);
	    char[] symbols = tmp.toString().toCharArray();
	    char[] buf = new char[6];
	    Random random = new Random();
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
