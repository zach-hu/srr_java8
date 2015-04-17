package com.tsa.puridiom.itemweb.tasks;

import com.tsagate.foundation.processengine.Task;
import java.util.*;
import org.w3c.dom.NodeList;

public class ItemWebSetup extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		String webSites      = (String)incomingRequest.get("as_catalog_multiple");
		String numberOfItems = (String)incomingRequest.get("pageSize");
		String resultformat  = "xml";
		String searchText    = (String)incomingRequest.get("as_keywords");

		String[] arrayWebSites = webSites.split("#");

		HashMap webSiteParser = new HashMap();
		webSiteParser.put("numberOfItems", numberOfItems);
		webSiteParser.put("resultformat" , resultformat);
		webSiteParser.put("searchText"   , searchText);

		List result = new ArrayList();
		for (int i=0; i<arrayWebSites.length; i++)
		{
			webSiteParser.put("webSite", arrayWebSites[i]);

			IResponseParser parser   = ReponseFactory.getParser("OpenKapow");
			NodeList        response = (NodeList)parser.parse(webSiteParser);

			int numberOfItemsFound;
			if(response == null)
				numberOfItemsFound = 0;
			else
				numberOfItemsFound = response.getLength();

			HashMap webSiteResult = new HashMap();
			webSiteResult.put("webSite"      , arrayWebSites[i]);
			webSiteResult.put("numberOfItems", String.valueOf(numberOfItemsFound));
			webSiteResult.put("itemsResponse", response);

			result.add(webSiteResult);
		}
		return result;
	}
}