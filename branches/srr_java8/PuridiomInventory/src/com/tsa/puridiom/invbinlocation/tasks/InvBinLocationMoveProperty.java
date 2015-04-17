/**
 *
 * Created on Feb 11, 2004
 * @author renzo
 * project: HiltonInventory
 * com.tsa.puridiom.invbinlocation.tasks.InvLocationMove.java
 *
 */
package com.tsa.puridiom.invbinlocation.tasks;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvProperty;
import com.tsa.puridiom.invproperty.tasks.InvPropertyRetrieveById;
import com.tsagate.foundation.database.DBSession;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvBinLocationMoveProperty extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		try
		{
			DBSession dbs = (DBSession)incomingRequest.get("dbsession") ;
			InvBinLocation toBin = (InvBinLocation)incomingRequest.get("toBin");
			//key is bin location icrc
			//value is serial number
			Map selectedPropertyMap = (Map) incomingRequest.get("selectedProperty");

			Set keys = selectedPropertyMap.keySet();

			InvPropertyRetrieveById invPropertyRetrieveById = new InvPropertyRetrieveById();

			for (Object key: keys) {
				List ids = (List) selectedPropertyMap.get(key);
				for (Object invPropIdObj: ids) {
					//retrieve the property record
					Map request = new HashMap();
					request.put("dbsession", dbs);
					request.put("InvProperty_icProperty", invPropIdObj);

					InvProperty invProperty = (InvProperty) invPropertyRetrieveById.executeTask(request);

					//move the property
					invProperty.setIcRc(toBin.getIcRc());

					//update the serial number
					dbs.update(invProperty);
				}
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return null;
	}
}
