/*
 * Created on Nov 24, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.invbinlocationUpdateQtyInvBinLocation.java
 */

package com.tsa.puridiom.invreturn.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvLocation;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;


public class UpdateQtyInvLocation extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		InvLocation inventory = (InvLocation)incomingRequest.get("invLocation");
		String	oid = (String) incomingRequest.get("organizationId") ;
		PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
		String 	duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
		String  allowNegative = propertiesManager.getProperty("INVENTORY", "ALLOWNEGATIVEQUANTITIES", "N");

		try
		{
			BigDecimal qoh = inventory.getQtyOnHand();
			String qty = (String)incomingRequest.get("qty");
			BigDecimal add = new BigDecimal(qty);
			qoh = qoh.add(add);
			inventory.setQtyOnHand(qoh);

			BigDecimal duomqoh = inventory.getDuomQtyOnHand();
			String	duomQty = (String) incomingRequest.get("InvReturn_duomQty");
			if (duomQty != null) {
				BigDecimal duomdiff = new BigDecimal(duomQty) ;

				duomqoh = duomqoh.add(duomdiff);
				inventory.setDuomQtyOnHand(duomqoh) ;
			}

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return inventory;
	}

}
