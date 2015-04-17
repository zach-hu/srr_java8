package com.tsa.puridiom.browse.tasks.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.browse.tasks.BrowseRetrieve;
import com.tsa.puridiom.browse.tasks.GenerateBrowseObject;
import com.tsagate.foundation.database.DBSession;

public class CatalogItemBrowseTest
{
	public static void  main (String[] args) throws Exception
	{
		Object result = null;
		try {
			DBSession dbs = new DBSession("puridiom");
			GenerateBrowseObject setup = new GenerateBrowseObject();
			BrowseRetrieve test = new BrowseRetrieve();
			Map incomingRequest = new HashMap();
			String	browseName = "catalogitem";

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("browseName", browseName);
//			incomingRequest.put("RequisitionHeader_requisitionType_filter", "T");

			System.out.println("Database Status: " + dbs.getStatus());
			System.out.println(incomingRequest);

			incomingRequest.put("browseObject", setup.executeTask(incomingRequest) );
			List list = (List) test.executeTask(incomingRequest);
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