/*
 * Created on Jun 3, 2004
 *
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invlocation.tasks.InvLocationChangeName.java
 * 
 */
package com.tsa.puridiom.invlocation.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.entity.InvLocation;
import com.tsa.puridiom.entity.InvLocationPK;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvLocationChangeName extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Object ret = null;
		
		try
		{
			Map incomingRequest = (Map)object;
			String newLocation = (String)incomingRequest.get("newItemLocation");
			String oldLocation = (String)incomingRequest.get("oldItemLocation");
			
			//InvLocation location = (InvLocation)incomingRequest.get("invLocation");
			List invLocationList = (List) incomingRequest.get("invLocation");
			List newLocationsList = new ArrayList();
			DBSession dbs = (DBSession)incomingRequest.get("dbsession");
			
			
			for (int i = 0; i < invLocationList.size(); i++)
			{
				InvLocation invLocation = (InvLocation) invLocationList.get(i);
				InvLocationPK pkNew = new InvLocationPK();
				pkNew.setItemLocation(newLocation);
				pkNew.setItemNumber(invLocation.getComp_id().getItemNumber());
				
				InvLocation newInvLocation = new InvLocation(invLocation);
				newInvLocation.setComp_id(pkNew);
				newLocationsList.add(newInvLocation);
			}
			incomingRequest.put("newLocationsList", newLocationsList);
			incomingRequest.put("invLocation", invLocationList);
			
			//String sql = "Update INV_LOCATION set item_location = '" + newLocation + "' where item_location = '" + 
			//oldLocation + "'";
			
			/*this.setStatus(dbs.sqlUpdate(sql));
			
			if(this.getStatus() == Status.FAILED)
			{
				throw new TsaException("An error occurred changing Location Name from: " + oldLocation + " to: " + newLocation);
			}
			*/
			
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}
}
