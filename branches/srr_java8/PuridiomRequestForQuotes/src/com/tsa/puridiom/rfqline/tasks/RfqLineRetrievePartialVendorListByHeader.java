package com.tsa.puridiom.rfqline.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RfqLineRetrievePartialVendorListByHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			Object items = incomingRequest.get("itemSelected");
			String itemsList[];
			List itemsSelected = new ArrayList();

			if (items instanceof String[])
			{
				itemsList = (String[]) items;
			} else
			{
				itemsList = new String[1];
				itemsList[0] = (String) items;
			}

			for (int i = 0; i < itemsList.length; i++)
			{
				String item = itemsList[i];
				String itemval[] = item.split(">");
				itemsSelected.add(itemval);
			}

			result = getPoList(itemsSelected);

			this.setStatus(dbs.getStatus());
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return result;
	}

	public List getPoList(List itemsSelected) throws Exception
	{
		List poList = new ArrayList();
		try
		{
			for (int i = 0; i < itemsSelected.size(); i++)
			{
				List values = new ArrayList();
				List lines = new ArrayList();

				String valitem[] = (String[]) itemsSelected.get(i);

				if (valitem[0].compareTo("NONE") != 0)
				{
					// gets the vendor
					if(HiltonUtility.isEmpty(valitem[0]))
						// if there is no vendor, continue with the next iteration
						continue;
					else
						values.add(valitem[0]);
					// gets the document type
					values.add(valitem[1]);

					// adds the first line for the current PO
					lines.add(valitem[2]);
					// gets all the lines for the same vendor to build one PO
					for (int j = i + 1; j < itemsSelected.size(); j++)
					{
						String valitem2[] = (String[]) itemsSelected.get(j);
						if (valitem[0].compareTo(valitem2[0]) == 0 && valitem[1].compareTo(valitem2[1]) == 0)
						{
							lines.add(valitem2[2]);
							itemsSelected.remove(j);
							j--;
						}
					}
					// adds all the lines for the same PO
					values.add(lines);
					poList.add(values);
				}
			}
		} catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return poList;
	}
}