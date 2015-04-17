package com.tsa.puridiom.rfqline.tasks;

import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RfqLineRetrievePartialPoListByHeader extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		Object result = null;

		try
		{
			DBSession dbs = (DBSession) incomingRequest.get("dbsession");
			Object items = incomingRequest.get("poDataList");
			if(items == null)
			{
				items = incomingRequest.get("itemSelected");
			}
			String rfqHeader_icReqHeader = (String) incomingRequest.get("RfqHeader_icReqHeader");
			String poTypeFromRfq = (String) incomingRequest.get("poTypeFromRfq");
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
				if(itemval.length == 1){
					itemval = item.split("&gt;");
				}
				if ( poTypeFromRfq != null && !item.equals("NONE"))
				{
					itemval[1] = poTypeFromRfq;
				}
				itemsSelected.add(itemval);
			}

			if (Long.parseLong(rfqHeader_icReqHeader) > 0)
			{
				result = getPoList(itemsSelected);
			} else
			{
				result = getGroupPoList(getPoPrefixedList(itemsSelected));
			}

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

	public List getPoPrefixedList(List itemsSelected) throws Exception
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
					values.add(valitem[0]);
					// gets the document type
					values.add(valitem[1]);

					// gets all the lines for the same vendor to build one PO
					lines = Arrays.asList(valitem[2].split(":"));
					// adds all the lines for the same PO
					values.add(lines);
					// adds the prefix
					if(valitem.length > 3)
					{
						values.add(valitem[3]);
					}
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

	private List getGroupPoList(List itemsSelected) throws Exception
	{
		List poList = new ArrayList();
		try
		{
			Map itemsGroup = new HashMap();
			List<String> itemsOrder = new ArrayList<String>();

			for (int i = 0; i < itemsSelected.size(); i++)
			{
				List item = (List)itemsSelected.get(i);
				if (item.size() == 3)
				{
					String key = item.get(0).toString() + ">" + item.get(1).toString();

					if (itemsGroup.containsKey(key))
					{
						List lines = (List)itemsGroup.get(key);
						List ics = (List)item.get(2);
						for (int ix = 0; ix < ics.size(); ix++) {
							lines.add(ics.get(ix));
						}
						itemsGroup.put(key, lines);
					}
					else
					{
						List lines = new ArrayList();
						List ics = (List)item.get(2);
						for (int ix = 0; ix < ics.size(); ix++) {
							lines.add(ics.get(ix));
						}
						itemsGroup.put(key, lines);
	
						itemsOrder.add(key);
					}
				}
			}

			for (int j = 0; j < itemsOrder.size(); j++)
			{
				String key = itemsOrder.get(j);
				String[] keyArray = key.split(">");

				if (keyArray.length == 2)
				{
					List newLines = new ArrayList();
					newLines.add(keyArray[0]);
					newLines.add(keyArray[1]);
					if (itemsGroup.containsKey(key)) {
						newLines.add(itemsGroup.get(key));
					} else {
						newLines.add(new ArrayList());
					}
					poList.add(newLines);
				}
			}
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}
		return poList;
	}
}