package com.tsa.puridiom.handlers.test.browse;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RequisitionBrowseGetOptionsTest
{
	public static void  main (String[] args) throws Exception
	{
		Object result = null;
		try {
			BrowseGetOptionsHandler test = new BrowseGetOptionsHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("browseName", "requisitionheader");
			
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);

			System.out.println(incomingRequest);
			System.out.println("BrowseRetrieve - " + (String) incomingRequest.get("viewPage"));
			
			BrowseObject browseObject= (BrowseObject) incomingRequest.get("browseObject");
			BrowseColumn[] labels = browseObject.getLabels();

			for (int i=0; i < labels.length; i++) {
				BrowseColumn column = (BrowseColumn) labels[i];
				if (!column.isHidden()) {
					System.out.println(labels[i].getLabel() + " - " + column.getColumnName());
				}
			}

			System.out.println("COMPLETE");
		}
		catch(Exception exception) {
			System.out.println(exception.toString());
			throw exception;
		}
	}
}