/*
 * Created on February 11, 2008
 */
package com.tsa.puridiom.invoice.approvals.tasks;

import com.tsa.puridiom.apppool.tasks.AppPoolRetrieveById;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.AppPool;
import com.tsa.puridiom.entity.InvoiceLine;
import com.tsa.puridiom.entity.PoLine;
import com.tsagate.foundation.processengine.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author kathleen
 */
public class InvoiceAddApprovers extends Task {
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
			AppPool appPool = null;
			String poolDesc = "";
			String approverObj[] = null;
			boolean bRequisitioner = false;
        	boolean bApprover = false;

        	//first add all the requisitioners
	        for (Iterator it = invoiceLineList.iterator(); it.hasNext(); )
	        {
	        	InvoiceLine invoiceLine = (InvoiceLine) it.next();
	        	PoLine poLine = invoiceLine.getPoLine();

	        	if (poLine != null)
	        	{
	        		BigDecimal icReqLine = HiltonUtility.ckNull(poLine.getIcReqLine());
	        		String requisitioner = poLine.getRequisitionerCode();

	        		if (icReqLine.compareTo(new BigDecimal(0)) > 0)
	        		{
	        			if ( !HiltonUtility.isEmpty(requisitioner) && !approverList.contains(requisitioner) )
		        		{
	        				approverObj = new String[3];
	        				approverObj[0] = requisitioner;
	        				approverObj[1] = "R";
	        				approverObj[1] = "";
	        				approverList.add(approverObj);
		        			bRequisitioner = true;
		        		}
	        		}
	        	}
	        }

	        //next add the requisition approver
	        for (Iterator it = invoiceLineList.iterator(); it.hasNext(); )
	        {
	        	InvoiceLine invoiceLine = (InvoiceLine) it.next();
	        	PoLine poLine = invoiceLine.getPoLine();

	        	if (poLine != null)
	        	{
	        		BigDecimal icReqLine = HiltonUtility.ckNull(poLine.getIcReqLine());
	        		String appBy = poLine.getAppBy();

	        		if (icReqLine.compareTo(new BigDecimal(0)) > 0)
	        		{
	        			if ( !HiltonUtility.isEmpty(appBy) && !approverList.contains(appBy) )
	        			{
	        				approverObj = new String[3];

	        				//check to see if there is a pool for that user
	        				incomingRequest.put("AppPool_poolid", appBy);
	        	        	AppPoolRetrieveById  pool = new AppPoolRetrieveById() ;
	        	        	appPool = (AppPool) pool.executeTask(incomingRequest);
	        	        	if (appPool != null)
	        	        	{
	        	        		approverObj[0] = "@" + appBy;
	        	        		approverObj[1] = "A";
	        	        		poolDesc = appPool.getPooldesc();
	        	        		approverObj[2] = poolDesc;
	        	        	}
	        	        	else
	        	        	{
	        	        		approverObj[0] = appBy;
	        	        		approverObj[1] = "A";
	        	        		approverObj[2] = "";
	        	        	}


	        				approverList.add(approverObj);
	        				incomingRequest.put("reqApprover", appBy);
	        				bApprover = true;
	        			}
	        		}
	        	}
	        }

	        //if no requisitioners are found
	        if (!bRequisitioner)
        	{
	        	approverObj = new String[3];
	        	approverObj[0] = "#Requisitioner";
				approverObj[1] = "N";
				approverObj[2] = "";
				approverList.add(approverObj);
        	}
	        //if no requisition approvers are found
        	if (!bApprover)
        	{
        		approverObj = new String[3];
        		approverObj[0] = "#Approver";
				approverObj[1] = "N";
				approverObj[2] = "";
				approverList.add(approverObj);
        	}

        	//  pool @PAYMENT
        	incomingRequest.put("AppPool_poolid", "PAYMENT");
        	AppPoolRetrieveById  pool = new AppPoolRetrieveById() ;
        	if(pool.executeTask(incomingRequest) != null)
        	{
	        	appPool = (AppPool) pool.executeTask(incomingRequest);
	        	poolDesc = appPool.getPooldesc();
	        	approverObj = new String[3];
	        	approverObj[0] = "@PAYMENT";
				approverObj[1] = "P";
				approverObj[2] = poolDesc;
				approverList.add(approverObj);
        	}


	        //	 pool @TREASURY
        	incomingRequest.put("AppPool_poolid", "TREASURY");
        	pool = new AppPoolRetrieveById() ;
        	if(pool.executeTask(incomingRequest) != null)
        	{
	        	appPool = (AppPool) pool.executeTask(incomingRequest);
	        	poolDesc = appPool.getPooldesc();
	        	approverObj = new String[3];
	        	approverObj[0] = "@TREASURY";
				approverObj[1] = "P";
				approverObj[2] = poolDesc;
				approverList.add(approverObj);
        	}

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
