/*
 * Created on Aug 7, 2003
 */
package com.tsagate.foundation.utility;

import java.util.*;

/**
 * @author Administrator
 */
public class UniqueKeyGenerator {
	private Stack stack = null;

	private static UniqueKeyGenerator SINGLETON = new UniqueKeyGenerator();

	private UniqueKeyGenerator(){
		initStack();
	}

	public static UniqueKeyGenerator getInstance(){
		if(SINGLETON == null){
			SINGLETON = new UniqueKeyGenerator();
		}
		return SINGLETON;
	}
	private void initStack(){
		stack = new Stack();
		for(int i = 99; i > -1; i--){
			stack.push(new Long(i));
		}
	}
	public synchronized Long getUniqueKey(){
		Long key = null;
		key = new Long(getMillisecondsPortion());
		String keyString = key.toString();
		String stackNumber = getNextNumberFromStack().toString();
		if(stackNumber.length() == 1){
			StringBuffer sb = new StringBuffer();
			sb.append("0");
			sb.append(stackNumber);
			stackNumber = sb.toString();
		}
		keyString += stackNumber;
		return Long.valueOf(keyString);
	}
	private Long getNextNumberFromStack(){
		Long numberFromStack = null;
		try{
			if(stack.empty()){
				initStack();
			}
			numberFromStack = (Long)stack.pop();
		}
		catch(Exception e){
			//system.out.println(e.toString());
		}
		finally{
			return numberFromStack;
		}
	}
	private long getMillisecondsPortion(){
		long milliseconds = 0;
		try{
			Thread.sleep(10);
			Calendar cal = Calendar.getInstance();
			Calendar current = Calendar.getInstance();
			cal.set(2003, Calendar.SEPTEMBER, 05, 00, 00, 01);
			cal.set(cal.MILLISECOND, 0) ;
			long time = cal.getTimeInMillis();
			long currentTime = current.getTimeInMillis();
			milliseconds = currentTime - time;
		}
		catch(Exception e){
			//system.out.println(e.toString());
		}
		finally{
			return milliseconds;
		}
	}

}
