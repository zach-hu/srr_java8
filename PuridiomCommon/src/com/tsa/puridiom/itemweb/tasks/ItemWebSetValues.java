package com.tsa.puridiom.itemweb.tasks;

import com.tsagate.foundation.processengine.Task;

import java.util.*;
import org.w3c.dom.*;

public class ItemWebSetValues extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		List result = new ArrayList();

		List responseWebSitesObject = (List)incomingRequest.get("webSitesResponse");
		for (int i=0; i<responseWebSitesObject.size(); i++)
		{
			HashMap  webSite      = (HashMap)responseWebSitesObject.get(i);
			NodeList itemResponse = (NodeList)webSite.get("itemsResponse");

			if(itemResponse != null)
			{
				for (int j=0; j<itemResponse.getLength();j++)
				{
					Node    item = itemResponse.item(j);
					Node    tag  = item.getFirstChild();
					HashMap map  = new HashMap();
					while (tag != null)
					{
						if (tag.getNodeType() == Node.ELEMENT_NODE)
						{
							if (tag.getNodeName().equalsIgnoreCase("title"))
								map.put("title", tag.getFirstChild().getNodeValue());
							else if (tag.getNodeName().equalsIgnoreCase("price"))
								map.put("price", tag.getFirstChild().getNodeValue());
							else if (tag.getNodeName().equalsIgnoreCase("url"))
								map.put("url", tag.getFirstChild().getNodeValue());
							else if (tag.getNodeName().equalsIgnoreCase("source"))
								map.put("source", tag.getFirstChild().getNodeValue());
						}
						tag = tag.getNextSibling();
					}
					result.add(map);
				}
			}
		}
		return result;
	}
}