/*
 * Created on Aug 1, 2003
 */
package com.tsagate.foundation.processengine.test;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
import com.tsagate.foundation.database.*;
//import com.tsagate.puridiom.entity.*;

/**
 * @author Administrator
 */
public class DatabaseConnectionTest {

	public static void main(String[] args) {
		try{
			DBSession ses = new DBSession("p4test") ;
			ses.close() ;
		}
		catch(Exception exception){
			System.out.println(exception.toString());
		}
	}
}
