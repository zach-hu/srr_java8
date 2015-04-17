/*
 * Created on Dec 2, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.invbinlocationForwardUpdateQty.java
 */

package com.tsa.puridiom.invbinlocation.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;


public class ForwardUpdateBinQty extends Task
{
	/* (non-Javadoc)
	 * @see com.tsagate.common.processengine.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;
		String	oid = (String) incomingRequest.get("organizationId") ;
		PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
		String 	duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
		String  allowNegative = propertiesManager.getProperty("INVENTORY", "ALLOWNEGATIVEQUANTITIES", "N");

		BigDecimal qtyToDisburse = (BigDecimal)incomingRequest.get("qtytodisburse");
		BigDecimal duomQtyToDisburse = (BigDecimal)incomingRequest.get("duomqtytodisburse");
		InvBinLocation bin = (InvBinLocation)incomingRequest.get("invBinLocation");
		if (bin != null) {
			BigDecimal qtyOnHand = bin.getQtyOnHand();
			if (qtyOnHand == null) qtyOnHand = new BigDecimal(0) ;
			qtyOnHand = qtyOnHand.subtract(qtyToDisburse);
			if (allowNegative.equalsIgnoreCase("N") && qtyOnHand.compareTo(new BigDecimal(0)) < 0) {
				qtyOnHand = new BigDecimal(0) ;
			}
			bin.setQtyOnHand(qtyOnHand);

			BigDecimal qtyAlloc = bin.getQtyAlloc();
			if (qtyAlloc == null) qtyAlloc = new BigDecimal(0) ;
			qtyAlloc = qtyAlloc.subtract(qtyToDisburse);
			if (allowNegative.equalsIgnoreCase("N") && qtyAlloc.compareTo(new BigDecimal(0)) < 0) {
				qtyAlloc = new BigDecimal(0) ;
			}
			bin.setQtyAlloc(qtyAlloc);

			if (duomRequired.equalsIgnoreCase("Y")) {
				BigDecimal duomQtyOnHand = bin.getDuomQtyOnHand();
				if (duomQtyOnHand == null) duomQtyOnHand = new BigDecimal(0) ;
				duomQtyOnHand = duomQtyOnHand.subtract(duomQtyToDisburse);
				if (allowNegative.equalsIgnoreCase("N") && duomQtyOnHand.compareTo(new BigDecimal(0)) < 0) {
					duomQtyOnHand = new BigDecimal(0) ;
				}
				bin.setDuomQtyOnHand(duomQtyOnHand);

				BigDecimal duomQtyAlloc = bin.getDuomQtyAlloc();
				if (duomQtyAlloc == null) duomQtyAlloc = new BigDecimal(0) ;
				duomQtyAlloc = duomQtyAlloc.subtract(duomQtyToDisburse);
				if (allowNegative.equalsIgnoreCase("N") && duomQtyAlloc.compareTo(new BigDecimal(0)) < 0) {
					duomQtyAlloc = new BigDecimal(0) ;
				}
				bin.setDuomQtyAlloc(duomQtyAlloc);
			}
		}
		return bin;
	}

}
