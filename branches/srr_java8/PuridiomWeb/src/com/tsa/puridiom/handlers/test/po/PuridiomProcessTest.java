/*
 * Created on Aug 1, 2003 
 */
package com.tsa.puridiom.handlers.test.po;

import java.util.*;

import com.tsagate.foundation.processengine.PuridiomProcess;
import com.tsagate.foundation.processengine.PuridiomProcessLoader;
//import com.tsagate.puridiom.datasource.*;
//import com.tsagate.puridiom.entity.*;

/**
 * @author Administrator 
 */
public class PuridiomProcessTest {

	public static void main(String[] args) {
		try{
			System.out.println(new Date());
			PuridiomProcessLoader processLoader = new PuridiomProcessLoader();
			PuridiomProcess process = processLoader.loadProcess("invitem-retrieve-all.xml");
			//PuridiomProcess process = processLoader.loadProcess("forward-requisition.xml");
			Map incomingRequest = new HashMap();
			incomingRequest.put("lookupStatus", "FOUND");
			/*incomingRequest.put("requisitionLine", "002Q09");
			incomingRequest.put("quantity", "80");
			incomingRequest.put("unitPrice", "0.80069");
			incomingRequest.put("requisitionNumber", "02005092");
			*/			
			/*Object returnObject =*/ 
			process.executeProcess(incomingRequest);
			//LineItem lineItem = (LineItem)returnObject;
			//System.out.println("");
			//process.executeProcess(incomingRequest);
			//System.out.println(process.toString());
			System.out.println("end of process test");
			//Database database = Database.getInstance();
			//database.finalize();
			System.out.println(new Date());
		}
		catch(Exception exception){
			System.out.println(exception.toString());
		}
	}
}
