/*
 * Created on Aug 27, 2004
 *
 * @author  * renzo
 * project: HiltonRequisitions
 * package com.tsa.puridiom.requisition.tasks;.RequisitionAssignBuyer.java
 *
 */
package com.tsa.puridiom.requisition.tasks;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.tsa.puridiom.commodity.tasks.CommodityRetrieveById;
import com.tsa.puridiom.common.documents.DocumentStatus;
import com.tsa.puridiom.entity.Commodity;
import com.tsa.puridiom.entity.RequisitionHeader;
import com.tsa.puridiom.entity.RequisitionLine;
import com.tsa.puridiom.property.PropertiesManager;
import com.tsagate.foundation.processengine.Status;
import com.tsagate.foundation.processengine.Task;
import com.tsagate.foundation.utility.Dates;
import com.tsagate.foundation.utility.Log;
import com.tsagate.foundation.utility.TsaException;
import com.tsagate.foundation.utility.Utility;

public class RequisitionAssignBuyer extends Task
{

    public Object executeTask(Object object) throws Exception
    {
        Log.debug(this, "Buyer Assignment");
        try
        {
            Map incomingRequest = (Map)object;
            PropertiesManager pm = PropertiesManager.getInstance((String)incomingRequest.get("companyId"));
            String bSugest = pm.getProperty("ASSIGNMENT", "AssignSuggested", "N");
            Log.debug(this.getName(), "Assign Suggested" + bSugest);
            String bCommodity = pm.getProperty("ASSIGNMENT", "AssignCommodity", "N");
            Log.debug(this.getName(), "Assign Commodity" + bCommodity);
            String bDept = pm.getProperty("ASSIGNMENT", "AssignDepartmentBuyer", "N");
            Log.debug(this.getName(), "AssignDepartmentBuyer" + bDept);
            String userTimeZone = (String) incomingRequest.get("userTimeZone");
            Date d_today = Dates.getCurrentDate(userTimeZone);

            RequisitionHeader header = (RequisitionHeader)incomingRequest.get("requisitionHeader");
            boolean ok = false;
            if(header.getStatus().equals(DocumentStatus.REQ_APPROVED) || header.getStatus().equals(DocumentStatus.REQ_FORWARDED))
            {
                ok = true;
            }

            if(bDept.equalsIgnoreCase("Y") && ok)
            {
                //buyer assignment by department!
            }

            List reqLineList = (List) incomingRequest.get("requisitionLineList") ;
            String headerBuyer = "";

            for (int i=0; i < reqLineList.size(); i++)
            {
                RequisitionLine rql = (RequisitionLine) reqLineList.get(i);
                Log.debug(this, "line: " + rql.getLineNumber().toString());
                if(ok)
                {
                    String sBuyer = "";
                    if(header.getRequisitionType().equals("N") && rql.getStatus().equals(DocumentStatus.REQ_FORWARDED))
                    {
                        String compare = "PURCHASING,UNASSIGNED";
                        if(compare.indexOf(rql.getAssignedBuyer()) < 0)
                        {
                            sBuyer = rql.getAssignedBuyer();
                        }
                    }
                    else if(header.getRequisitionType().equals("S") || header.getRequisitionType().equals("Z") || header.getRequisitionType().equals("T"))
                    {
                        sBuyer = "INVENTORY";
                    }
                    else if(bSugest.equals("Y"))
                    {
                        sBuyer = header.getBuyer();
                    }

                    if(Utility.isEmpty(sBuyer) && bCommodity.equals("Y"))
                    {
                        //Buyer Assigment by Commodity
                        String commodityCode = rql.getCommodityCode();
                        if(!Utility.isEmpty(commodityCode))
                        {
                            Log.debug(this, "Commodity Code: " + commodityCode);
                            incomingRequest.put("Commodity_commodity", commodityCode);
                            CommodityRetrieveById commodityTask = new CommodityRetrieveById();
                            Commodity commodity = (Commodity)commodityTask.executeTask(incomingRequest);
                            if(commodityTask.getStatus() == Status.SUCCEEDED)
                            {
                                sBuyer = commodity.getBuyerCode();
                            }
                            Log.debug(this, "buyer: " + sBuyer);
                        }
                    }

                    if(Utility.isEmpty(sBuyer) && bDept.equals("Y"))
                    {
                        //buyer from department assignment!
                        sBuyer = "";
                    }
                    if(Utility.isEmpty(sBuyer)){		sBuyer = "PURCHASING"; 	}
                    rql.setAssignedBuyer(sBuyer);
                    rql.setAssignedDate(d_today);

                    if(i == 0)
                    {
                        headerBuyer = sBuyer;
                    }
                    else if(!headerBuyer.equalsIgnoreCase(sBuyer))
                    {
                        headerBuyer = "";
                    }
                }
                reqLineList.set(i, rql);
            }

            header.setAssignedBuyer(headerBuyer);
            header.setAssignedDate(d_today);

            incomingRequest.put("requisitionLineList", reqLineList);
            incomingRequest.put("requisitionHeader", header);
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