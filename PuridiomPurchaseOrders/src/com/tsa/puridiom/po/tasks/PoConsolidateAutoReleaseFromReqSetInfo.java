/*
 * Created on Aug 25, 2004
 *
 * @author  * renzo
 * project: HiltonPurchaseOrders
 * package com.tsa.puridiom.po.tasks.PoReleaseCreateSetup.java
 *
 */
package com.tsa.puridiom.po.tasks;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.autorelease.RequisitionLineAutoReleaseObject;
import com.tsa.puridiom.entity.PoHeader;
import com.tsa.puridiom.entity.PoLine;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.entity.ShipTo;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.TsaException;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class PoConsolidateAutoReleaseFromReqSetInfo extends Task
{
	/**
	 * Returns first RequisitionHeader
	 * @param autoLines
	 * @param icPoLine
	 * @return
	 */
	public RequisitionHeader getRequisitionHeader(List autoLines, BigDecimal icPoLine)
	{
		Object ret = null;
		for(int i = 0; i < autoLines.size(); i++)
		{

			RequisitionLineAutoReleaseObject autoReleaseObject = (RequisitionLineAutoReleaseObject)autoLines.get(i);
			if(icPoLine.compareTo(autoReleaseObject.getPoLine().getIcPoLine()) == 0)
			{
				autoReleaseObject.getShipTo();
				ret = autoReleaseObject.getRequisitionHeader();
				i = autoLines.size();
			}
		}
		return (RequisitionHeader)ret;
	}

	/**
	 * Returns first RequisitionLine
	 * @param autoLines
	 * @param icPoLine
	 * @return
	 */
	public RequisitionLine getRequisitionLine(List autoLines, BigDecimal icPoLine)
	{
		Object ret = null;
		for(int i = 0; i < autoLines.size(); i++)
		{

			RequisitionLineAutoReleaseObject autoReleaseObject = (RequisitionLineAutoReleaseObject)autoLines.get(i);
			if(icPoLine.compareTo(autoReleaseObject.getPoLine().getIcPoLine()) == 0)
			{
				autoReleaseObject.getShipTo();
				ret = autoReleaseObject.getRequistionLine();
				i = autoLines.size();
			}
		}
		return (RequisitionLine)ret;
	}

	public String getShipToFromLine(RequisitionLine requisitionLine)
	{
		String shipToCode = "";
		List shipToList = requisitionLine.getShipToList();
		if(shipToList.size() >0)
		{
			shipToCode = ((ShipTo)shipToList.get(0)).getShipToCode();
		}

		return shipToCode;
	}


	/**
	 * Return fist icPoLine
	 * @param poLineList
	 * @return
	 */
	public BigDecimal getIcPoLine(List poLineList)
	{
		BigDecimal icPoLine = new BigDecimal(0);
		if(poLineList.size() > 0){		icPoLine = ((PoLine)poLineList.get(0)).getIcPoLine();		}

		return icPoLine;
	}
    public Object executeTask(Object object) throws Exception
    {
    	 Map incomingRequest = (Map)object;
        try
        {
            PoHeader newPoHeader = (PoHeader)incomingRequest.get("poHeader");
            if(newPoHeader == null)
            {
                this.setStatus(Status.FAILED);
                throw new TsaException("Release Order was not Found.");
            }

            List autoLines = (List)incomingRequest.get("autoLines");
            List poLineList = (List)incomingRequest.get("poLineList");
            BigDecimal icPoLine = this.getIcPoLine(poLineList);
            RequisitionHeader requisitionHeader = this.getRequisitionHeader(autoLines, icPoLine);
            RequisitionLine requisitionLine = this.getRequisitionLine(autoLines, icPoLine);
            incomingRequest.put("requisitionHeader", requisitionHeader);

            if(requisitionHeader == null)
            {
                this.setStatus(Status.SUCCEEDED);
                return super.executeTask(object);
            }
            else
            {
            	newPoHeader.setIcReqHeader(requisitionHeader.getIcReqHeader());
                newPoHeader.setRequisitionNumber(requisitionHeader.getRequisitionNumber());
                newPoHeader.setFiscalYear(requisitionHeader.getFiscalYear());
                newPoHeader.setIcHeaderHistory(requisitionHeader.getIcHeaderHistory());
                newPoHeader.setRequisitionerCode(requisitionHeader.getRequisitionerCode());
                newPoHeader.setRequiredDate(requisitionHeader.getRequiredDate());
                newPoHeader.setRouting(requisitionHeader.getRouting());
                newPoHeader.setShipToContact(requisitionHeader.getShipAttn());
                newPoHeader.setCurrencyCode(requisitionHeader.getCurrencyCode());
            }
            if(requisitionLine != null)
            {
            	newPoHeader.setShipToCode(this.getShipToFromLine(requisitionLine));
            }
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException(this.getName(), e);
        }
        return super.executeTask(object);
    }
}
