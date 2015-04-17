/*
 * Created on February 11, 2008
 */
package com.tsa.puridiom.invoiceline.tasks;

import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author kathleen
 */
public class InvoiceLineRetrieveReqInfo extends Task {
	/* (non-Javadoc)
	 * @see com.tsagate.puridiom.process.ITask#executeTask(java.lang.Object)
	 */
	public Object executeTask(Object object) throws Exception {

		Map incomingRequest = (Map)object;
		Object result = null;

		try
		{
			List invoiceLineList = (List) incomingRequest.get("invoiceLineList");
			List approverList = new ArrayList();
			boolean bRequisitioner = false;
        	boolean bApprover = false;
	        for (Iterator it = invoiceLineList.iterator(); it.hasNext(); ) {
	        	InvoiceLine invoiceLine = (InvoiceLine) it.next();
	        	PoLine poLine = invoiceLine.getPoLine();

	        	if (poLine != null)
	        	{
	        		BigDecimal icReqLine = HiltonUtility.ckNull(poLine.getIcReqLine());
	        		String requisitioner = poLine.getRequisitionerCode();
	        		String appBy = poLine.getAppBy();

	        		if (icReqLine.compareTo(new BigDecimal(0)) > 0)
	        		{
	        			if ( !HiltonUtility.isEmpty(requisitioner) && !approverList.contains(requisitioner) )
		        		{
		        			approverList.add(requisitioner);
		        			bRequisitioner = true;
		        		}
	        			if ( !HiltonUtility.isEmpty(appBy) && !approverList.contains(appBy) )
	        			{
	        				approverList.add(appBy);
	        				bApprover = true;
	        			}
	        		}
	        	}
	        }

	        if (!bRequisitioner)
        	{
        		approverList.add("#Requisitioner");
        	}
        	if (!bApprover)
        	{
        		approverList.add("#Approver");
        	}
	        approverList.add("@PAYMENT");
        	approverList.add("@TREASURY");

        	incomingRequest.put("routingList", approverList);

			this.setStatus(Status.SUCCEEDED) ;
		}
		catch (Exception e)
		{
			this.setStatus(Status.FAILED);
			throw e;
		}

		return result;
	}

}
