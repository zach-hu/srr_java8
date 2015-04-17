/*
 * Created on Oct 20, 2004
 */
package com.tsa.puridiom.invreturn.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvReturn;
import com.tsa.puridiom.entity.InvItem;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class InvReturnOtcCreatePreview extends Task
{
	public Object executeTask(Object object) throws Exception
	{
	    Object ret = null;
		try
		{
			Map incomingRequest = (Map)object;
			InvItem invItem = (InvItem)incomingRequest.get("invItem");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			if (invItem == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException(this.getName() + ", Inv Item was not found!");
			}
			this.setBin(incomingRequest, invItem);
			//incomingRequest.put("InvItem_itemNumber", invItem.getItemNumber()) ;
			ret = this.setInvReturn(invItem, userTimeZone);
			incomingRequest.put("viewAction", "save");
			this.setStatus(Status.SUCCEEDED);
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw new TsaException(this.getName(), e);
		}
		return ret;
	}

    /**
     *
     */
    private InvReturn setInvReturn(InvItem invItem, String userTimeZone)
    {
        InvReturn invReturn = new InvReturn();
        invReturn.setItemNumber(invItem.getItemNumber());
        //invReturn.setLineNo(invItem.getLineNumber());
        //invReturn.setDisbNumber(invItem.getDisbNumber());
		//invReturn.setIcDsbLine(invItem.getIcDsbLine());
		//invReturn.setIcDsbHeader(invItem.getIcDsbHeader());
		invReturn.setRecDate(Dates.getCurrentDate(userTimeZone));
		//invReturn.setRecAmount(invItem.getQuantity());
		invReturn.setDuomQty(new java.math.BigDecimal(0));

		return invReturn;
    }

    /**
     * @param incomingRequest
     */
    private void setBin(Map incomingRequest, InvItem invItem)
    {
        InvBinLocation bin = new InvBinLocation();
        bin.setCost(invItem.getCost());
        //bin.setItemLocation(invItem.getItemLocation());
        bin.setItemNumber(invItem.getItemNumber());
        //bin.setVendorId(invItem.getVendorId());
        //bin.setManufactNo(invItem.getManufactNo());
        //bin.setQtyOnHand(invItem.getQuantity());
        bin.setDuomQtyOnHand(new java.math.BigDecimal(0));

        incomingRequest.put("invBinLocation", bin);
    }
}
