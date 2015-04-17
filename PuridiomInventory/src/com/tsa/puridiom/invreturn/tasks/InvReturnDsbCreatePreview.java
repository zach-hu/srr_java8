/*
 * Created on Oct 20, 2004
 */
package com.tsa.puridiom.invreturn.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvReturn;
import com.tsa.puridiom.entity.DisbLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class InvReturnDsbCreatePreview extends Task
{
	public Object executeTask(Object object) throws Exception
	{
	    Object ret = null;
		try
		{
			Map incomingRequest = (Map)object;
			DisbLine disbLine = (DisbLine)incomingRequest.get("disbLine");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			if(disbLine == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException(this.getName() + ", Line Item was not found!");
			}
			this.setBin(incomingRequest, disbLine);
			incomingRequest.put("InvItem_itemNumber", disbLine.getItemNumber()) ;
			ret = this.setInvReturn(disbLine, userTimeZone);
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
    private InvReturn setInvReturn(DisbLine disbLine, String userTimeZone)
    {
        InvReturn invReturn = new InvReturn();
        invReturn.setItemNumber(disbLine.getItemNumber());
        invReturn.setLineNo(disbLine.getLineNumber());
        invReturn.setDisbNumber(disbLine.getDisbNumber());
		invReturn.setIcDsbLine(disbLine.getIcDsbLine());
		invReturn.setIcDsbHeader(disbLine.getIcDsbHeader());
		invReturn.setRecDate(Dates.getCurrentDate(userTimeZone));
		invReturn.setRecAmount(disbLine.getQuantity());
		invReturn.setDuomQty(new java.math.BigDecimal(0));

		return invReturn;
    }

    /**
     * @param incomingRequest
     */
    private void setBin(Map incomingRequest, DisbLine disbLine)
    {
        InvBinLocation bin = new InvBinLocation();
        bin.setCost(disbLine.getUnitPrice());
        bin.setItemLocation(disbLine.getItemLocation());
        bin.setItemNumber(disbLine.getItemNumber());
        bin.setVendorId(disbLine.getVendorId());
        bin.setManufactNo(disbLine.getManufactNo());
        bin.setQtyOnHand(disbLine.getQuantity());
        bin.setDuomQtyOnHand(new java.math.BigDecimal(0)) ;

        incomingRequest.put("invBinLocation", bin);
    }
}
