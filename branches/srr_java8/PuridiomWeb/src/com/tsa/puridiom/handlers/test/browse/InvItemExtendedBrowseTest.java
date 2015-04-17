package com.tsa.puridiom.handlers.test.browse;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class InvItemExtendedBrowseTest
{
	public static void  main (String[] args) throws Exception
	{
		Object result = null;
		try 
		{
			BrowseRetrieveHandler test = new BrowseRetrieveHandler();
			Map incomingRequest = new HashMap();
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("browseName", "invitem-invlocation");
			incomingRequest.put("invbrowse", "true");
			incomingRequest.put("as_inv_state", "PA");
			incomingRequest.put("as_inv_product", "FIG-AGENT");
			incomingRequest.put("debug", "true");
			
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			
			test.handleRequest(incomingRequest);

			System.out.println(incomingRequest);
			System.out.println("BrowseRetrieve - " + (String) incomingRequest.get("viewPage"));
			
			List list = (List) incomingRequest.get("browseList");
			
			int	rows = list.size();
				
			if (rows > 10) {
				rows = 10;
			}
			
			System.out.println("Showing records 1 - " + rows + " of "+ list.size());
			if (list != null) {
				BrowseObject browseObject = (BrowseObject) incomingRequest.get("browseObject");
				BrowseColumn[] labels = browseObject.getLabels();

				for (int il = 0; il < rows; il++) {
					System.out.println("------ ROW " + il + " -----");
					List values[] = (List[]) list.get(il);
					List displayList = (List) values[0];
					List hiddenList = (List) values[1];
					for (int i=0; i < labels.length; i++) {
						BrowseColumn column = (BrowseColumn) displayList.get(i);
						if (column.getLink() != null && column.getLink().length() > 0) {
							System.out.println(labels[i].getLabel() + " - " + column.getValue() + " - LINK: " + column.getLink());
						}
						else {
							System.out.println(labels[i].getLabel() + " - " + column.getValue());
						}
					}
					for (int i=0; i < hiddenList.size(); i++) {
						BrowseColumn column = (BrowseColumn) hiddenList.get(i);
						System.out.println("HIDDEN - " + column.getValue());
					}
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