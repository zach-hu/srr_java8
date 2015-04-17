/**
 * Created on Nov 24, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.invbinlocationUpdateQtyInvBinLocation.java
 */

package com.tsa.puridiom.invreturn.tasks;

import java.math.BigDecimal;
import java.util.Map;

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
			incomingRequest.put("InvLocation_itemLocation", bin.getItemLocation()) ;
			BigDecimal qoh = bin.getQtyOnHand();
			String qty = (String) incomingRequest.get("qty");
			BigDecimal diff = new BigDecimal(qty);
			qoh = qoh.add(diff);
			bin.setQtyOnHand(qoh);

			BigDecimal duomqoh = bin.getDuomQtyOnHand();
			String	duomQty = (String) incomingRequest.get("InvReturn_duomQty");
			if (duomQty != null) {
				BigDecimal duomdiff = new BigDecimal(duomQty) ;
				duomqoh = duomqoh.add(duomdiff);
				bin.setDuomQtyOnHand(duomqoh);
			}
		}
		catch (Exception e)
		{
			Log.error(this, e.toString());
		}
		return bin;
	}

}
