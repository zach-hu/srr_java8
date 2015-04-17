/*
 * Created on Nov 24, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.invbinlocationUpdateQtyInvBinLocation.java
 */

package com.tsa.puridiom.invlocation.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvLocation;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


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
			BigDecimal qal = inventory.getQtyAlloc();
			BigDecimal diff = (BigDecimal) incomingRequest.get("diff");

			qoh = qoh.add(diff);

			qal = qal.subtract(diff);
			if (allowNegative.equalsIgnoreCase("N")) {
				if(qal.compareTo(new BigDecimal(0)) < 0)
				{
				    qal = new BigDecimal(0);
				}
			}
			inventory.setQtyAlloc(qal);
			//inventory.setQtyOnHand(qoh);

			BigDecimal duomqoh = inventory.getDuomQtyOnHand();
			BigDecimal duomqal = inventory.getDuomQtyAlloc();
			BigDecimal duomdiff = (BigDecimal) incomingRequest.get("duomdiff");

			duomqoh = duomqoh.add(duomdiff);
			duomqal = duomqal.subtract(duomdiff);
			if (allowNegative.equalsIgnoreCase("N")) {
				if(duomqal.compareTo(new BigDecimal(0)) < 0)
				{
				    duomqal = new BigDecimal(0);
				}
			}
			inventory.setDuomQtyAlloc(duomqal);
			//bin.setQtyOnHand(qoh);

			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, e.toString());
		}
		return inventory;
	}

}
