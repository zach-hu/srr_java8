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

import com.tsa.puridiom.commodity.tasks.CommodityRetrieveById;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.common.documents.RequisitionType;
import com.tsa.puridiom.entity.Commodity;
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
public class ByCommodityNotByLine extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Object ret = null;
        try
        {
            Map incomingRequest = (Map)object;
            RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            List lines = (List)incomingRequest.get("requisitionLineList");
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            Date d_today = Dates.getCurrentDate(userTimeZone);
            String lineBuyer = "";

            for(int i = 0; i < lines.size(); i++)
            {
                RequisitionLine rql = (RequisitionLine)lines.get(i);
                Log.debug(this, "requisition line: " + rql.getLineNumber().toString());
                lineBuyer = this.checkTypes(rql, header);
                if(Utility.isEmpty(lineBuyer))
                {
                    lineBuyer = this.byCommodity(rql, incomingRequest);
                }
                rql.setAssignedBuyer(lineBuyer);
                rql.setAssignedDate(d_today);
                lines.set(i, rql);

                if(!Utility.isEmpty(lineBuyer))
                {
                    incomingRequest.put("headerBuyer", lineBuyer);
                    i = lines.size();
                }
            }

            for(int i = 0; i < lines.size(); i++)
            {
                RequisitionLine rql = (RequisitionLine)lines.get(i);
                Log.debug(this, "requisition line: " + rql.getLineNumber().toString());
                rql.setAssignedBuyer(lineBuyer);
                rql.setAssignedDate(d_today);
                lines.set(i, rql);
            }

            ret = lines;
            this.setStatus(Status.SUCCEEDED);
        }
        catch (Exception e)
        {
            this.setStatus(Status.FAILED);
            throw new TsaException("Assign By Commodity-line Failed!");
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
                sBuyer = rql.getAssignedBuyer();
            }
        }
        else if(header.getRequisitionType().equals(RequisitionType.SUPPLY_REQUEST) || header.getRequisitionType().equals(RequisitionType.IMPRINT_REQUEST) || header.getRequisitionType().equals(RequisitionType.TRANSFER_REQUEST))
        {
            sBuyer = "INVENTORY";
        }
        Log.debug(this, "checkTypes buyer: " + sBuyer);

        return sBuyer;
    }

    private String byCommodity(RequisitionLine rql, Map incomingRequest)
    {
        String sBuyer = "";
        //Buyer Assigment by Commodity
        String commodityCode = rql.getCommodityCode();
        if(!Utility.isEmpty(commodityCode))
        {
            Log.debug(this, "Commodity Code: " + commodityCode);
            incomingRequest.put("Commodity_commodity", commodityCode);
            CommodityRetrieveById commodityTask = new CommodityRetrieveById();
            Commodity commodity;
            try
            {
                commodity = (Commodity)commodityTask.executeTask(incomingRequest);
                if(commodityTask.getStatus() == Status.SUCCEEDED)
                {
                    sBuyer = commodity.getBuyerCode();
                }
            }
            catch (Exception e)
            {
                sBuyer = "";
                //forcing status of the process. if the commodity is not found.
                this.setStatus(Status.SUCCEEDED);
                Log.error(this, "Commodity " + commodityCode + " does not have a Buyer Associated!");
            }

            Log.debug(this, "buyer: " + sBuyer);
        }

        return sBuyer;
    }
}
