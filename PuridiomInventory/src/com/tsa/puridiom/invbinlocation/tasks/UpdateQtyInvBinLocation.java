/**
 * Created on Nov 24, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.invbinlocationUpdateQtyInvBinLocation.java
 */

package com.tsa.puridiom.invbinlocation.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;


public class UpdateQtyInvBinLocation extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		InvBinLocation bin = (InvBinLocation)incomingRequest.get("invBinLocation");
		String	oid = (String) incomingRequest.get("organizationId") ;
		PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
		String 	duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
		String  allowNegative = propertiesManager.getProperty("INVENTORY", "ALLOWNEGATIVEQUANTITIES", "N");

		try
		{
			// Update bin only if the disbursement number has been assigned
			BigDecimal qoh = bin.getQtyOnHand();
			BigDecimal qal = bin.getQtyAlloc();
			BigDecimal diff = (BigDecimal) incomingRequest.get("diff");

			qoh = qoh.add(diff);
			qal = qal.subtract(diff);

			if (allowNegative.equalsIgnoreCase("N")) {
				if(qal.compareTo(new BigDecimal(0)) < 0)
				{
				    qal = new BigDecimal(0);
				}
			}
			bin.setQtyAlloc(qal);

			BigDecimal duomqoh = bin.getDuomQtyOnHand();
			BigDecimal duomqal = bin.getDuomQtyAlloc();
			BigDecimal duomdiff = (BigDecimal) incomingRequest.get("duomdiff");

			duomqoh = duomqoh.add(duomdiff);
			duomqal = duomqal.subtract(duomdiff);
			if (allowNegative.equalsIgnoreCase("N")) {
				if(duomqal.compareTo(new BigDecimal(0)) < 0)
				{
				    duomqal = new BigDecimal(0);
				}
			}
			bin.setDuomQtyAlloc(duomqal);
			//bin.setQtyOnHand(qoh);
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
		}
		return bin;
	}

}
