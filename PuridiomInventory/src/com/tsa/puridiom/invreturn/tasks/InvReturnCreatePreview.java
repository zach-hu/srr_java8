/*
 * Created on Oct 20, 2004
 */
package com.tsa.puridiom.invreturn.tasks;

import java.util.Map;

import com.tsa.puridiom.entity.InvBinLocation;
import com.tsa.puridiom.entity.InvReturn;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 */
public class InvReturnCreatePreview extends Task
{
	public Object executeTask(Object object) throws Exception
	{
	    Object ret = null;
		try
		{
			Map incomingRequest = (Map)object;
			RequisitionLine requisitionLine = (RequisitionLine)incomingRequest.get("requisitionLine");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");

			if(requisitionLine == null)
			{
				this.setStatus(Status.FAILED);
				throw new TsaException(this.getName() + ", Line Item was not found!");
			}
			this.setBin(incomingRequest, requisitionLine);
			incomingRequest.put("InvItem_itemNumber", requisitionLine.getItemNumber()) ;
			ret = this.setInvReturn(requisitionLine, userTimeZone);
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
    private InvReturn setInvReturn(RequisitionLine requisitionLine, String userTimeZone)
    {
        InvReturn invReturn = new InvReturn();
        invReturn.setItemNumber(requisitionLine.getItemNumber());
        invReturn.setLineNo(requisitionLine.getLineNumber());
		invReturn.setRequisitionNumber(requisitionLine.getRequisitionNumber());
		invReturn.setIcReqLine(requisitionLine.getIcReqLine());
		invReturn.setIcReqHeader(requisitionLine.getIcReqHeader());
		invReturn.setRecDate(Dates.getCurrentDate(userTimeZone));
		invReturn.setRecAmount(requisitionLine.getQuantity());
		invReturn.setDuomQty(new java.math.BigDecimal(0));

		return invReturn;
    }

    /**
     * @param incomingRequest
     */
    private void setBin(Map incomingRequest, RequisitionLine requisitionLine)
    {
        InvBinLocation bin = new InvBinLocation();
        bin.setCost(requisitionLine.getUnitPrice());
        bin.setItemLocation(requisitionLine.getItemLocation());
        bin.setItemNumber(requisitionLine.getItemNumber());
        bin.setVendorId(requisitionLine.getVendorId());
        bin.setManufactNo(requisitionLine.getMfgName());
        bin.setQtyOnHand(requisitionLine.getQuantity());
        bin.setDuomQtyOnHand(new java.math.BigDecimal(0)) ;

        incomingRequest.put("invBinLocation", bin);

    }
}
