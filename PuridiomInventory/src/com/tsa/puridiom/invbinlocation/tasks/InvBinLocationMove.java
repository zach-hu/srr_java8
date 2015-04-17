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
import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

public class InvBinLocationMove extends Task
{
	public Object executeTask(Object object) throws Exception
	{
		Map incomingRequest = (Map)object;

		String	oid = (String) incomingRequest.get("organizationId") ;
		PropertiesManager propertiesManager = PropertiesManager.getInstance(oid);
		String 	duomRequired = propertiesManager.getProperty("INVENTORY", "DUALUOMREQUIRED", "N");
		String  allowNegative = propertiesManager.getProperty("INVENTORY", "ALLOWNEGATIVEQUANTITIES", "N");

		try
		{
			InvBinLocation fromBin = (InvBinLocation)incomingRequest.get("fromBin");
			InvBinLocation toBin = (InvBinLocation)incomingRequest.get("toBin");
			String qtyToMove = (String)incomingRequest.get("qtyToMove");
			String duomQtyToMove = (String)incomingRequest.get("duomQtyToMove");

			if (qtyToMove != null) {
				BigDecimal qty = new BigDecimal(qtyToMove);
				BigDecimal tempQty = fromBin.getQtyOnHand();
				if (tempQty == null) tempQty = new BigDecimal(0) ;
				tempQty = tempQty.subtract(qty);
				if (allowNegative.equalsIgnoreCase("N")) {
					if (tempQty.compareTo(new BigDecimal(0)) < 0) tempQty = new BigDecimal(0) ;
				}
				fromBin.setQtyOnHand(tempQty);
				tempQty = toBin.getQtyOnHand();
				tempQty = tempQty.add(qty);
				toBin.setSource("MOV") ;
				if (allowNegative.equalsIgnoreCase("N")) {
					if (tempQty.compareTo(new BigDecimal(0)) < 0) tempQty = new BigDecimal(0) ;
				}
				toBin.setQtyOnHand(tempQty);
			}
			if (duomQtyToMove != null) {
				BigDecimal qty = new BigDecimal(duomQtyToMove);
				BigDecimal tempQty = fromBin.getDuomQtyOnHand();
				if (tempQty == null) tempQty = new BigDecimal(0) ;
				tempQty = tempQty.subtract(qty);
				if (allowNegative.equalsIgnoreCase("N")) {
					if (tempQty.compareTo(new BigDecimal(0)) < 0) tempQty = new BigDecimal(0) ;
				}
				fromBin.setDuomQtyOnHand(tempQty);
				tempQty = toBin.getDuomQtyOnHand();
				tempQty = tempQty.add(qty);
				if (allowNegative.equalsIgnoreCase("N")) {
					if (tempQty.compareTo(new BigDecimal(0)) < 0) tempQty = new BigDecimal(0) ;
				}
				toBin.setDuomQtyOnHand(tempQty);
			}
			// Copy other fields to new bin
			toBin.setCost(fromBin.getCost());
			toBin.setManufactNo(fromBin.getManufactNo()) ;
			toBin.setVendorId(fromBin.getVendorId()) ;
			toBin.setUdf1Code(fromBin.getUdf1Code());
			toBin.setUdf2Code(fromBin.getUdf2Code());
			toBin.setUdf3Code(fromBin.getUdf3Code());
			toBin.setUdf4Code(fromBin.getUdf4Code());
			toBin.setUdf5Code(fromBin.getUdf5Code());
			toBin.setSource("MOV") ;

			incomingRequest.put("fromBin", fromBin);
			incomingRequest.put("toBin", toBin);
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
