/**
 * Created on Apr 23, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvBinLocationUpdateLocationQtiesSetUp.java
 *
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/*
 * InvBinLocationUpdateLocationQtiesSetUp
 */
public class InvBinLocationUpdateLocationQtiesSetUp extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		try
		{
			Map incomingRequest = (Map)object;
			InvBinLocation bin = (InvBinLocation)incomingRequest.get("invBinLocation");
			if (bin != null) {
				incomingRequest.put("InvBinLocation_itemNumber", bin.getItemNumber());
				incomingRequest.put("InvBinLocation_itemLocation", bin.getItemLocation());
				incomingRequest.put("InvLocation_itemNumber", bin.getItemNumber());
				incomingRequest.put("InvLocation_itemLocation", bin.getItemLocation());
				incomingRequest.put("InvItem_itemNumber", bin.getItemNumber()) ;
			} else {
			    // Must be adding list
			    Object itemNumberObj = incomingRequest.get("InvBinLocation_itemNumber");
			    Object itemLocationObj = incomingRequest.get("InvBinLocation_itemLocation");
			    String	itemNumber = "";
			    String	itemLocation = "";

			    if (itemNumberObj instanceof String[]) {
			        String	itemNumbers[] = (String[]) itemNumberObj;
			        itemNumber = itemNumbers[0];
			    } else {
			        itemNumber = (String) itemNumberObj;
			    }
			    if (itemLocationObj instanceof String[]) {
			        String	itemLocations[] = (String[]) itemLocationObj;
			        itemLocation = itemLocations[0];
			    } else {
			        itemLocation = (String) itemLocationObj;
			    }

			    Set keySet = incomingRequest.keySet();
			    Iterator iterator = keySet.iterator();
			    List keysToRemove = new ArrayList();
				while (iterator.hasNext()) {
					String key = (String) iterator.next();
					if (key.indexOf("InvBinLocation_") == 0 || key.indexOf("InvLocation_") == 0) {
					    keysToRemove.add(key);
					}
				}

				for (int i=0; i < keysToRemove.size(); i++) {
				    String	key = (String) keysToRemove.get(i);
				    incomingRequest.remove(key);
				}

			    incomingRequest.put("InvBinLocation_itemNumber", itemNumber);
				incomingRequest.put("InvBinLocation_itemLocation", itemLocation);
				incomingRequest.put("InvLocation_itemNumber", itemNumber);
				incomingRequest.put("InvLocation_itemLocation", itemLocation);
				incomingRequest.put("InvItem_itemNumber", itemNumber) ;

			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			//throw new TsaException(this.getName(), e);
			throw e;
		}
		return super.executeTask(object);
	}

}
