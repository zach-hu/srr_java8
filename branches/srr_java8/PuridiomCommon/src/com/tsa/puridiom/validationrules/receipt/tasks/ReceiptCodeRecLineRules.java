package com.tsa.puridiom.validationrules.receipt.tasks;

import java.math.BigDecimal;
import java.util.Map;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;

public class ReceiptCodeRecLineRules extends Task
{
	public Object  executeTask (Object object) throws Exception
	{
		Map incomingRequest = (Map) object;
		String withinqtyTolerance = "true";

		try
        {
            String organizationId = (String) incomingRequest.get("organizationId");
	        Object objQtyAccepted = (Object) incomingRequest.get("ReceiptLine_qtyAccepted");
	        Object objOriginalQtyReceived = (Object) incomingRequest.get("originalQtyReceived");
            Object objQtyOrdered = (Object) incomingRequest.get("PoLine_quantity");

	        if (objQtyAccepted != null && objOriginalQtyReceived != null && objQtyOrdered != null)
	        {
	        	if (objQtyAccepted instanceof String && objOriginalQtyReceived instanceof String && objQtyOrdered instanceof String)
	        	{
	        		String qtyAccepted = (String) objQtyAccepted;
	        		String originalQtyReceived    = (String) objOriginalQtyReceived;
                    String qtyOrdered = (String) objQtyOrdered;

                    if (!isWithinqtyTolerance(organizationId, qtyAccepted, originalQtyReceived, qtyOrdered))
                    {
                        withinqtyTolerance = "false";
                    }
	        	}
	        	else
	        	{
                    String[] qtyAcceptedValues = (String[]) objQtyAccepted;
                    String[] originalQtyReceivedValues    = (String[]) objOriginalQtyReceived;
                    String[] qtyOrderedValues = (String[]) objQtyOrdered;

	        		for (int i=0; i< qtyAcceptedValues.length; i++)
	        		{
                        if (!isWithinqtyTolerance(organizationId, qtyAcceptedValues[i], originalQtyReceivedValues[i], qtyOrderedValues[i]))
                        {
                            withinqtyTolerance = "false";
                        }
                    }
	        	}
	        }
	        incomingRequest.put("isWithinQtyReceiptTolerance", withinqtyTolerance);

	        this.setStatus(Status.SUCCEEDED);
        }
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			Log.error(this, "An Error occurred at ReceiptCodeRecLineRules" + e);
            throw new TsaException("An Error occurred at ReceiptCodeRecLineRules", e);
		}
		return withinqtyTolerance;
    }

    private boolean isWithinqtyTolerance(String organizationId, String qtyAccepted, String originalQtyReceived, String qtyOrdered)
    {
        boolean withinqtyTolerance = true;

        try
        {
            if (!HiltonUtility.isEmpty(qtyOrdered))
            {
	        	if (HiltonUtility.isEmpty(qtyAccepted))
	            {
	        		qtyAccepted = "0";
	            }
	            if (HiltonUtility.isEmpty(originalQtyReceived))
	            {
	                originalQtyReceived = "0";
	            }

	            BigDecimal bdQtyOrdered = new BigDecimal(qtyOrdered);
	            BigDecimal Tolerance =  bdQtyOrdered.add(bdQtyOrdered.multiply(new BigDecimal(10)).divide(new BigDecimal(100),BigDecimal.ROUND_HALF_UP));
	            BigDecimal bdQtyAccepted = new BigDecimal(qtyAccepted);
	            BigDecimal bdOriginalQtyReceived = new BigDecimal(originalQtyReceived);

	            if(Tolerance.compareTo(bdQtyAccepted) < 0)
	            {
	            	withinqtyTolerance = false;
	            }
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            Log.error(this, "An Error occurred at ReceiptCodeRecLineRules.isWithinqtyTolerance(" + organizationId + ", " + qtyAccepted  + ", " + originalQtyReceived  + ", " + e);

        }
        return withinqtyTolerance;
    }
}