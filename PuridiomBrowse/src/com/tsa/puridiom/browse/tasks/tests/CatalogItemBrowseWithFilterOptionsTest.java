package com.tsa.puridiom.browse.tasks.tests;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.browse.BrowseColumn;
import com.tsa.puridiom.browse.BrowseGroupFilter;
import com.tsa.puridiom.browse.BrowseObject;
import com.tsa.puridiom.browse.tasks.BrowseRetrieve;
import com.tsa.puridiom.browse.tasks.GenerateBrowseObject;
import com.tsa.puridiom.browse.tasks.GroupFilterSelectionRetrieve;
import com.tsagate.foundation.database.DBSession;

public class CatalogItemBrowseWithFilterOptionsTest
{
	public static void  main (String[] args) throws Exception
	{
		Object result = null;
		try {
			DBSession dbs = new DBSession("puridiom");
			GenerateBrowseObject setup = new GenerateBrowseObject();
			BrowseRetrieve test = new BrowseRetrieve();
			GroupFilterSelectionRetrieve groupTest = new GroupFilterSelectionRetrieve();
			Map incomingRequest = new HashMap();
			String	browseName = "catalogitem";

			incomingRequest.put("dbsession", dbs);
			incomingRequest.put("browseName", browseName);

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

			System.out.println("Database Status: " + dbs.getStatus());
			System.out.println("Now get filter selection options... ");

			List groupFilters = (List) groupTest.executeTask(incomingRequest);
			if (list != null) {
				for (int i=0; i < groupFilters.size(); i++) {
					BrowseGroupFilter groupFilter = (BrowseGroupFilter) groupFilters.get(i);
					List filterSelectionList = groupFilter.getSelectionValues();
					for (int ix=0; ix < filterSelectionList.size(); ix++) {
						Object obj = filterSelectionList.get(ix);
						if (obj instanceof Object[]) {
							Object objArray[] = (Object[]) obj;
							for (int iz=0; iz < objArray.length; iz++) {
								System.out.println(objArray[iz]);
							}
						} else {
							System.out.println(obj);
						}
					}
				}
			}
			System.out.println("Database Status: " + dbs.getStatus());
			System.out.println("COMPLETE");
		}
		catch(Exception exception) {
			System.out.println(exception.toString());
			throw exception;
		}
	}
}