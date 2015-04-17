/**
 *
 * Created on Jan 23, 2004
 * @author renzo
 * project: HiltonPurchaseOrders
 * com.tsa.puridiom.po.tasks.PoReqMapUdfs.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.UserProfile;
import com.tsa.puridiom.po.exceptions.MappingNullException;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsa.puridiom.usermanager.UserManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class PoUserMapUdfs extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			UserProfile user = UserManager.getInstance().getUser((String)incomingRequest.get("organizationId"), (String)incomingRequest.get("userId"));
			Map poUdfs = PropertiesManager.getInstance((String)incomingRequest.get("organizationId")).getSection("PO NAME UDFS");
			Set udfs = poUdfs.keySet();
			for (Iterator iter = udfs.iterator(); iter.hasNext();)
			{
				String poUdf = (String) iter.next();
				String userUdf = (String)poUdfs.get(poUdf);
				try
                {
					incomingRequest.put( MappingUdfs.findPoUdfName(poUdf), MappingUdfs.getUserUdf(userUdf, user));
                }
                catch(MappingNullException mne)
                {
                    Log.warn(this.getName(), poUdf + " was not found") ;
                }

			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
			this.setStatus(Status.FAILED);
		}
		return null;
	}


}
