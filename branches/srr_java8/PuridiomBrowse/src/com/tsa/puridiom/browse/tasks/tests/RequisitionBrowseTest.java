package com.tsa.puridiom.browse.tasks.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.tasks.BrowseRetrieve;
import com.tsagate.foundation.database.DBSession;

public class RequisitionBrowseTest
{
	public static void  main (String[] args) throws Exception
	{
		Object result = null;
		try {
			DBSession dbs = new DBSession("puridiom");
			BrowseRetrieve test = new BrowseRetrieve();
			Map incomingRequest = new HashMap();
			String	browseName = "requisitionheader";

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("browseName", browseName);
			incomingRequest.put("RequisitionHeader_requisitionType_filter", "T");

			System.out.println("Database Status: " + dbs.getStatus());
			System.out.println(incomingRequest);

			List list = (List) test.executeTask(incomingRequest);
			int	rows = list.size();

			if (rows > 10) {
				rows = 10;
			}

			System.out.println("Showing records 1 - " + rows + " of "+ list.size());
			if (list != null) {
				String[] labels = (String[]) incomingRequest.get("browseLabels");

				for (int il = 0; il < rows; il++) {
					System.out.println("------ ROW " + il + " -----");
					Map map = (HashMap) list.get(il);
					List displayList = (List) map.get("displayValues");
					List hiddenList = (List) map.get("hiddenValues");
					for (int i=0; i < labels.length; i++) {
						BrowseColumn column = (BrowseColumn) displayList.get(i);
						if (column.getLink() != null && column.getLink().length() > 0) {
							System.out.println(labels[i] + " - " + column.getValue() + " - LINK: " + column.getLink());
						}
						else {
							System.out.println(labels[i] + " - " + column.getValue());
						}
					}
					for (int i=0; i < hiddenList.size(); i++) {
						BrowseColumn column = (BrowseColumn) hiddenList.get(i);
						System.out.println("HIDDEN - " + column.getValue());
					}
				}
			}
/*			System.out.println("Showing records 1 - " + rows + " of "+ list.size());
			if (list != null) {
				List labels = BrowsePropertiesUtility.getBrowseLabels(browseName);

				for (int il = 0; il < rows; il++) {
					Object object =list.get(il);

					System.out.println("------ ROW " + il + " -----");
					Map map = BrowsePropertiesUtility.getBrowseValues(object, browseName, "PURIDIOM");
					List displayList = (List) map.get("displayValuesList");
					List hiddenList = (List) map.get("hiddenValuesList");
					for (int i=0; i < labels.size(); i++) {
						System.out.println(labels.get(i) + " - " + displayList.get(i));
					}
					for (int i=0; i < hiddenList.size(); i++) {
						System.out.println("HIDDEN - " + hiddenList.get(i));
					}
				}
			}
*/
			System.out.println("COMPLETE");
		}
		catch(Exception exception) {
			System.out.println(exception.toString());
			throw exception;
		}
	}
}