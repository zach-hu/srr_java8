package com.tsa.puridiom.handlers.test.browse;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.handlers.*;
import java.util.*;

public class RequisitionBrowseTest
{
	public static void  main (String[] args) throws Exception
	{
		Object result = null;
		try {
			BrowseRetrieveHandler test = new BrowseRetrieveHandler();
			Map incomingRequest = new HashMap();
			String	colnames[] = {"RequisitionHeader_requisitionDate", "RequisitionHeader_requisitionType", "RequisitionHeader_requisitionNumber"};
			String	operators[] = {"=", "=", "="};	
			String	filters[] = {"2004-02-06", "P", "006518"};
			String	logicalOperators[] = {"AND", "AND", ""};
			String	sorts[] = {"N","N","ASC"};
			String	originalFilters[] = {"Y", "Y", "Y"};
			
			incomingRequest.put("organizationId", "PURIDIOM");
			incomingRequest.put("userId", "KELLI");
			incomingRequest.put("browseName", "requisitionheader");
			
			
			incomingRequest.put("colname",colnames);
			incomingRequest.put("operator", operators);
			incomingRequest.put("filter_txt", filters);
			incomingRequest.put("logicalOperator", logicalOperators);
			incomingRequest.put("sort", sorts);
			incomingRequest.put("originalFilter", originalFilters);
			
			incomingRequest.put("successPage", "SUCCESS");
			incomingRequest.put("failurePage", "FAILURE");
			incomingRequest.put("debug", "true");

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