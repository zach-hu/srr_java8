/*
 * Created on Dec 2, 2003
 * @author renzo
 * project: hilton
 * com.tsa.puridiom.tasks.invlocationForwardUpdateInventoryQty.java
 */

package com.tsa.puridiom.invlocation.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.entity.InvLocation;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Task;

public class ForwardUpdateInventoryQty extends Task
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
		if (qtyToDisburse == null) qtyToDisburse = new BigDecimal(0);
		if (duomQtyToDisburse == null) duomQtyToDisburse = new BigDecimal(0);

		String 	dsb_type = (String) incomingRequest.get("DisbHeader_disbType") ;
		if (dsb_type == null) dsb_type = "" ;

		InvLocation inventory = (InvLocation)incomingRequest.get("invLocation");
		BigDecimal qtyOnHand = inventory.getQtyOnHand();
		qtyOnHand = qtyOnHand.subtract(qtyToDisburse);
		if (allowNegative.equalsIgnoreCase("N")) {
			if(qtyOnHand.compareTo(new BigDecimal(0)) < 0) qtyOnHand = new BigDecimal(0) ;
		}
		inventory.setQtyOnHand(qtyOnHand);

		BigDecimal duomQtyOnHand = inventory.getDuomQtyOnHand();
		duomQtyOnHand = duomQtyOnHand.subtract(duomQtyToDisburse);

		if (allowNegative.equalsIgnoreCase("N")) {
			if(duomQtyOnHand.compareTo(new BigDecimal(0)) < 0) duomQtyOnHand = new BigDecimal(0) ;
		}
		inventory.setDuomQtyOnHand(duomQtyOnHand);

		BigDecimal qtyAlloc = inventory.getQtyAlloc();
		qtyAlloc = qtyAlloc.subtract(qtyToDisburse);
		if (allowNegative.equalsIgnoreCase("N")) {
			if(qtyAlloc.compareTo(new BigDecimal(0)) < 0) qtyAlloc = new BigDecimal(0) ;
		}
		inventory.setQtyAlloc(qtyAlloc);

		BigDecimal duomQtyAlloc = inventory.getDuomQtyAlloc();
		duomQtyAlloc = duomQtyAlloc.subtract(duomQtyToDisburse);
		if (allowNegative.equalsIgnoreCase("N")) {
			if(duomQtyAlloc.compareTo(new BigDecimal(0)) < 0) duomQtyAlloc = new BigDecimal(0) ;
		}
		inventory.setDuomQtyAlloc(duomQtyAlloc);

		return inventory;
	}
}
