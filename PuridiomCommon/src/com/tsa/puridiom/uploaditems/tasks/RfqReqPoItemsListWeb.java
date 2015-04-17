package com.tsa.puridiom.uploaditems.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Utility;

public class RfqReqPoItemsListWeb extends Task
{
	public Object executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		String listAddItems = (String)incomingRequest.get("listAddItems");

		String[] tempString1 = listAddItems.split("\\[\\[#");
		String[] tempString2 = tempString1[1].split("#]]");
		String[] listItem    = tempString2[0].split("#], \\[#");

		List result = new ArrayList();
		for(int i=0; i<listItem.length; i++)
		{
			String quantity = (String)incomingRequest.get("inputQuantity" + String.valueOf(i));
			if(!Utility.isEmpty(quantity))
			{
				String[] columns = listItem[i].split("#, #");
				List item = new ArrayList();
				for(int j=0; j<columns.length; j++)
				{
					if(j == 2) //quantity column
						item.add(quantity);
					else
						item.add(columns[j]);
				}
				result.add(item);
			}
		}
		return result;
	}
}