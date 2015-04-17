package com.tsa.puridiom.validationrules.receipt.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.commodity.CommodityManager;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.Commodity;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class CommodityCodeRecLineRules extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String withinTolerance = "true";

		try
        {
            String organizationId = (String) incomingRequest.get("organizationId");
	        Object objQtyAccepted = (Object) incomingRequest.get("ReceiptLine_qtyAccepted");
	        Object objOriginalQtyReceived = (Object) incomingRequest.get("originalQtyReceived");
            Object objQtyOrdered = (Object) incomingRequest.get("PoLine_quantity");
            Object objCommodity = (Object) incomingRequest.get("PoLine_commodity");

	        if (objQtyAccepted != null && objOriginalQtyReceived != null && objCommodity != null && objQtyOrdered != null)
	        {
	        	if (objQtyAccepted instanceof String && objOriginalQtyReceived instanceof String && objCommodity instanceof String && objQtyOrdered instanceof String)
	        	{
	        		String qtyAccepted = (String) objQtyAccepted;
	        		String originalQtyReceived    = (String) objOriginalQtyReceived;
                    String qtyOrdered = (String) objQtyOrdered;
                    String commodityCode = (String) objCommodity;

                    if (!isWithinTolerance(organizationId, qtyAccepted, originalQtyReceived, qtyOrdered, commodityCode))
                    {
                        withinTolerance = "false";
                    }
	        	}
	        	else
	        	{
                    String[] qtyAcceptedValues = (String[]) objQtyAccepted;
                    String[] originalQtyReceivedValues    = (String[]) objOriginalQtyReceived;
                    String[] qtyOrderedValues = (String[]) objQtyOrdered;
                    String[] commodityCodeValues = (String[]) objCommodity;

	        		for (int i=0; i< qtyAcceptedValues.length; i++)
	        		{
                        if (!isWithinTolerance(organizationId, qtyAcceptedValues[i], originalQtyReceivedValues[i], qtyOrderedValues[i], commodityCodeValues[i]))
                        {
                            withinTolerance = "false";
                        }
                    }
	        	}
	        }
	        incomingRequest.put("isWithinCommodityReceiptTolerance", withinTolerance);

	        this.setStatus(Status.SUCCEEDED);
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "An Error occurred at CommodityCodeRecLineRules" + e);
            throw new TsaException("An Error occurred at CommodityCodeRecLineRules", e);
		}
		return withinTolerance;
    }

    private boolean isWithinTolerance(String organizationId, String qtyAccepted, String originalQtyReceived, String qtyOrdered, String commodityCode)
    {
        boolean withinTolerance = true;

        try
        {
            if (!HiltonUtility.isEmpty(commodityCode))
            {
                if (HiltonUtility.isEmpty(qtyAccepted))
                {
                    qtyAccepted = "0";
                }
                if (HiltonUtility.isEmpty(originalQtyReceived))
                {
                    originalQtyReceived = "0";
                }
                if (HiltonUtility.isEmpty(qtyOrdered))
                {
                    qtyOrdered = "0";
                }

                BigDecimal bdQtyAccepted = new BigDecimal(qtyAccepted);
                BigDecimal bdOriginalQtyReceived = new BigDecimal(originalQtyReceived);
                BigDecimal bdQtyOrdered = new BigDecimal(qtyOrdered);
                BigDecimal bdTotalQtyRecieved = bdQtyAccepted.add(bdOriginalQtyReceived);

                if (bdTotalQtyRecieved.compareTo(new BigDecimal(0)) > 0)
                {
                    // Get Commodity Tolerance
                    Commodity commodity = CommodityManager.getInstance().getCommodity(organizationId, commodityCode);
                    if (commodity != null && commodity.getFailsafe().equals("Y"))
                    {
                        BigDecimal bdVariance = commodity.getVariance();
                        bdVariance = bdVariance.multiply(new BigDecimal(".01")).add(new BigDecimal(1));

                        BigDecimal bdTolerance = bdVariance.multiply(bdQtyOrdered);

                        if (bdTotalQtyRecieved.compareTo(bdTolerance) > 0)
                        {
                            withinTolerance = false;
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            Log.error(this, "An Error occurred at CommodityCodeRecLineRules.isWithinTolerance(" + organizationId + ", " + qtyAccepted  + ", " + originalQtyReceived  + ", " + commodityCode + e);

        }
        return withinTolerance;
    }
}