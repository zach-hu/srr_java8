/*
 * Created on Apr 13, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.tsa.puridiom.requisition.buyerassignment.tasks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.common.utility.HiltonUtility;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

/**
 * @author renzo
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class BySuggestedLine extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            List lines = (List)incomingRequest.get("requisitionLineList");
            Log.debug(this, "By suggested Buyer");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            Date d_today = Dates.getCurrentDate(userTimeZone);

            for(int i = 0; i < lines.size(); i++)
            {
                RequisitionLine rql = (RequisitionLine)lines.get(i);
                Log.debug(this, "requisition line: " + rql.getLineNumber().toString());

                if(!rql.getAssigned())
                {
	                String lineBuyer = HiltonUtility.ckNull(this.checkTypes(rql, header));

	                if(Utility.isEmpty(lineBuyer))
	                {
	                    lineBuyer = HiltonUtility.ckNull(this.bySuggestedBuyer(rql, incomingRequest, header));
	                }
	                if( ("PURCHASING,UNASSIGNED").indexOf(lineBuyer) < 0  && !Utility.isEmpty(lineBuyer) )
	                {
	                	rql.setAssignedBuyer(lineBuyer);
	                    rql.setAssignedDate(d_today);
	                	rql.setAssigned(true);
	                }
	                lines.set(i, rql);
                }
            }

            ret = lines;
            this.setStatus(Status.SUCCEEDED);
            if(1 == 1)
            {
                String temp = "";
            }
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Assign By Suggested Buyer-line Failed!");
        }

        return ret;
    }

    private String checkTypes(RequisitionLine rql, RequisitionHeader header)
    {
        Log.debug(this, "checkTypes");
        String sBuyer = "";

        if(header.getRequisitionType().equals(RequisitionType.PRICING_REQUEST) && rql.getStatus().equals(DocumentStatus.REQ_FORWARDED))
        {
            String compare = "PURCHASING,UNASSIGNED";
            if(compare.indexOf(rql.getAssignedBuyer()) < 0)
            {
                sBuyer = HiltonUtility.ckNull(rql.getAssignedBuyer());
            }
        }
        else if(header.getRequisitionType().equals(RequisitionType.SUPPLY_REQUEST) || header.getRequisitionType().equals(RequisitionType.IMPRINT_REQUEST) || header.getRequisitionType().equals(RequisitionType.TRANSFER_REQUEST))
        {
            sBuyer = "INVENTORY";
        }
        Log.debug(this, "checkTypes buyer: " + sBuyer);

        return sBuyer;
    }

    private String bySuggestedBuyer(RequisitionLine rql, Map incomingRequest, RequisitionHeader header)
    {
        String sBuyer = HiltonUtility.ckNull(rql.getAssignedBuyer());
        //Buyer Assigment by Suggested Buyer
        Log.debug(this, "line buyer: " + sBuyer);

        if( Utility.isEmpty(sBuyer) || !((String)incomingRequest.get("byCommodity")).equals("Y") ||
        		sBuyer.equals("PURCHASING") || sBuyer.equals("UNASSIGNED") || sBuyer.equals("AUTORELEASE") )
        {
            sBuyer = HiltonUtility.ckNull(header.getBuyer());
            Log.debug(this, "suggested buyer: " + sBuyer);
        }

        if(Utility.isEmpty(sBuyer)){        sBuyer = "PURCHASING";  }

        return sBuyer;
    }
}
